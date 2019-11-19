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

    @Before
    public void init() {
        analyser = new BoardAnalyser();
        board = new Board(5, 5, Collections.emptyList());
        testCell = board.getCellAtPosition(testCellPosition);
    }

    @Test
    public void Given_LivingCellWithLessThanTwoLivingNeighbours_Then_AnalyserReturnsInstructionToKillTheCell() {
        TestHelper.reviveCells(board, positionsOfTestCellAndOneNeighbour);

        BoardInstructions instructions = analyser.determineBoardChanges(board);

        assertTrue(instructions.getCellsToKill().contains(testCell));
    }

    @Test
    public void Given_LivingCellWithMoreThanThreeLivingNeighbours_Then_AnalyserReturnsInstructionToKillTheCell() {
        TestHelper.reviveCells(board, positionsOfTestCellAndFourNeighbours);

        BoardInstructions instructions = analyser.determineBoardChanges(board);

        assertTrue(instructions.getCellsToKill().contains(testCell));
    }

    @Test
    public void Given_LivingCellWithTwoLivingNeighbours_Then_AnalyserDoesNotReturnInstructionToKillTheCell() {
        TestHelper.reviveCells(board, positionsOfTestCellAndTwoNeighbours);

        BoardInstructions instructions = analyser.determineBoardChanges(board);

        assertFalse(instructions.getCellsToKill().contains(testCell));
    }

    @Test
    public void Given_LivingCellWithThreeLivingNeighbours_Then_AnalyserDoesNotReturnInstructionToKillTheCell() {
        TestHelper.reviveCells(board, positionsOfTestCellAndThreeNeighbours);

        BoardInstructions instructions = analyser.determineBoardChanges(board);

        assertFalse(instructions.getCellsToKill().contains(testCell));
    }

    @Test
    public void Given_DeadCellWithThreeLivingNeighbours_Then_AnalyserReturnsInstructionToReviveTheCell() {
        TestHelper.reviveCells(board, positionsOfThreeTestCellNeighbours);

        BoardInstructions instructions = analyser.determineBoardChanges(board);

        assertTrue(instructions.getCellsToRevive().contains(testCell));
    }

    @Test
    public void Given_BoardThatRequiresMultipleCellsToBeRevived_Then_AnalyserReturnsInstructionsToReviveThoseCells() {
        TestHelper.reviveCells(board, Arrays.asList(new Coordinates(2, 1), new Coordinates(2, 2), new Coordinates(2, 3)));

        List<Cell> actualReviveInstructions = analyser.determineBoardChanges(board).getCellsToRevive();

        List<Cell> cellsToRevive = TestHelper.getCells(board, Arrays.asList(new Coordinates(1, 2), new Coordinates(3, 2)));
        assertTrue(actualReviveInstructions.containsAll(cellsToRevive));
    }

    @Test
    public void Given_BoardThatRequiresMultipleCellsToBeKilled_Then_AnalyserReturnsInstructionsToKillThoseCells() {
        TestHelper.reviveCells(board, Arrays.asList(new Coordinates(2, 1), new Coordinates(2, 2), new Coordinates(2, 3)));

        List<Cell> actualKillInstructions = analyser.determineBoardChanges(board).getCellsToKill();

        List<Cell> cellsToKill = TestHelper.getCells(board, Arrays.asList(new Coordinates(2, 1), new Coordinates(2, 3)));
        assertTrue(actualKillInstructions.containsAll(cellsToKill));
    }

}
