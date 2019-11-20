package core;

import org.junit.Before;
import org.junit.Test;
import utils.TestHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BoardAnalyserTest {
    private BoardAnalyser analyser;
    private Board board;
    private Cell testCell;

    private static final Coordinates testCellPosition = new Coordinates(0, 0);
    private static final List<Coordinates> positionsOfTestCellAndOneNeighbour = Arrays.asList(
            new Coordinates(0, 0), new Coordinates(0, 1));

    private static final List<Coordinates> positionsOfTestCellAndTwoNeighbours = Arrays.asList(
            new Coordinates(0, 0), new Coordinates(0, 1), new Coordinates(1, 1));

    private static final List<Coordinates> positionsOfThreeTestCellNeighbours = Arrays.asList(
            new Coordinates(0, 1), new Coordinates(1, 1), new Coordinates(1, 0));

    private static final List<Coordinates> positionsOfTestCellAndThreeNeighbours = Arrays.asList(
            new Coordinates(0, 0), new Coordinates(0, 1), new Coordinates(1, 1),
            new Coordinates(1, 0));

    private static final List<Coordinates> positionsOfTestCellAndFourNeighbours = Arrays.asList(
            new Coordinates(0, 0), new Coordinates(0, 1), new Coordinates(1, 1),
            new Coordinates(1, 0), new Coordinates(4, 4));

    private static final List<Coordinates> testPattern = Arrays.asList(
            new Coordinates(2, 1), new Coordinates(2, 2), new Coordinates(2, 3));

    private static final List<Coordinates> positionsToReviveOnTestPattern = Arrays.asList(
            new Coordinates(1, 2), new Coordinates(3, 2));

    private static final List<Coordinates> positionsToKillOnTestPattern = Arrays.asList(
            new Coordinates(2, 1), new Coordinates(2, 3));

    // These are good tests. I'm OK with the test names now that I'm used to them, but I've
    // added some suggestions for shorter names which are less specific, but I think convey
    // the important information.

    @Before
    public void init() {
        analyser = new BoardAnalyser();
        board = new Board(5, 5, Collections.emptyList());
        testCell = board.getCellAtPosition(testCellPosition);
    }

    @Test
    // suggested shorter name: liveCellWithOneLivingNeighbour_shouldDie
    public void Given_ALivingCellWithLessThanTwoLivingNeighbours_Then_ReturnInstructionToKillTheCell() {
        TestHelper.reviveCells(board, positionsOfTestCellAndOneNeighbour);

        BoardInstructions instructions = analyser.determineBoardChanges(board);

        assertTrue(instructions.getCellsToKill().contains(testCell));
    }

    @Test
    // suggested shorter name: liveCellWithFourLivingNeighbours_shouldDie
    public void Given_ALivingCellWithMoreThanThreeLivingNeighbours_Then_ReturnInstructionToKillTheCell() {
        TestHelper.reviveCells(board, positionsOfTestCellAndFourNeighbours);

        BoardInstructions instructions = analyser.determineBoardChanges(board);

        assertTrue(instructions.getCellsToKill().contains(testCell));
    }

    @Test
    // suggested shorter name: liveCellWithTwoLivingNeighbours_shouldLive
    public void Given_ALivingCellWithTwoLivingNeighbours_Then_DoesNotReturnInstructionToKillTheCell() {
        TestHelper.reviveCells(board, positionsOfTestCellAndTwoNeighbours);

        BoardInstructions instructions = analyser.determineBoardChanges(board);

        assertFalse(instructions.getCellsToKill().contains(testCell));
    }

    @Test
    public void Given_ALivingCellWithThreeLivingNeighbours_Then_DoesNotReturnInstructionToKillTheCell() {
        TestHelper.reviveCells(board, positionsOfTestCellAndThreeNeighbours);

        BoardInstructions instructions = analyser.determineBoardChanges(board);

        assertFalse(instructions.getCellsToKill().contains(testCell));
    }

    @Test
    // suggested shorter name: deadCellWithThreeLivingNeighbours_shouldRevive
    public void Given_ADeadCellWithThreeLivingNeighbours_Then_ReturnInstructionToReviveTheCell() {
        TestHelper.reviveCells(board, positionsOfThreeTestCellNeighbours);

        BoardInstructions instructions = analyser.determineBoardChanges(board);

        assertTrue(instructions.getCellsToRevive().contains(testCell));
    }

    @Test
    public void Given_BoardThatRequiresMultipleCellsToBeRevived_Then_ReturnInstructionsToReviveThoseCells() {
        TestHelper.reviveCells(board, testPattern);

        List<Cell> cellsToRevive = analyser.determineBoardChanges(board).getCellsToRevive();

        List<Cell> requiredCells = TestHelper.getCells(board, positionsToReviveOnTestPattern);
        assertTrue(cellsToRevive.containsAll(requiredCells));
    }

    @Test
    public void Given_BoardThatRequiresMultipleCellsToBeKilled_Then_ReturnInstructionsToKillThoseCells() {
        TestHelper.reviveCells(board, testPattern);

        List<Cell> cellsToKill = analyser.determineBoardChanges(board).getCellsToKill();

        List<Cell> requiredCells = TestHelper.getCells(board, positionsToKillOnTestPattern);
        assertTrue(cellsToKill.containsAll(requiredCells));
    }

}
