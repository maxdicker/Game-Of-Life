import core.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class BoardModificationTest {
    private BoardAnalyser analyser;
    private BoardModifier modifier;
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

    private static final List<Coordinates> positionsPatternA = Arrays.asList(
            new Coordinates(2, 1), new Coordinates(2, 2), new Coordinates(2, 3));

    private static final List<Coordinates> positionsPatternB = Arrays.asList(
            new Coordinates(1, 2), new Coordinates(2, 2), new Coordinates(3, 2));

    @Before
    public void init() {
        analyser = new BoardAnalyser();
        modifier = new BoardModifier();
        board = new Board(5, 5, Collections.emptyList());
        testCell = board.getCellByPosition(testCellPosition);
    }

    @Test
    public void Given_BoardWithLivingCellThatHasLessThanTwoLivingNeighbours_When_BoardIsModified_Then_CellDies() {
        TestHelper.reviveCells(board, positionsOfTestCellAndOneNeighbour);

        BoardInstructions instructions = analyser.determineBoardChanges(board);
        modifier.modifyBoard(instructions);

        assertFalse(testCell.isAlive());
    }

    @Test
    public void Given_BoardWithLivingCellThatHasMoreThanThreeLivingNeighbours_When_BoardIsModified_Then_CellDies() {
        TestHelper.reviveCells(board, positionsOfTestCellAndFourNeighbours);

        BoardInstructions instructions = analyser.determineBoardChanges(board);
        modifier.modifyBoard(instructions);

        assertFalse(testCell.isAlive());
    }

    @Test
    public void Given_BoardWithLivingCellThatHasTwoLivingNeighbours_When_BoardIsModified_Then_CellIsStillAlive() {
        TestHelper.reviveCells(board, positionsOfTestCellAndTwoNeighbours);

        BoardInstructions instructions = analyser.determineBoardChanges(board);
        modifier.modifyBoard(instructions);

        assertTrue(testCell.isAlive());
    }

    @Test
    public void Given_BoardWithLivingCellThatHasThreeLivingNeighbours_When_BoardIsModified_Then_CellIsStillAlive() {
        TestHelper.reviveCells(board, positionsOfTestCellAndThreeNeighbours);

        BoardInstructions instructions = analyser.determineBoardChanges(board);
        modifier.modifyBoard(instructions);

        assertTrue(testCell.isAlive());
    }

    @Test
    public void Given_BoardWithDeadCellThatHasThreeLivingNeighbours_When_BoardIsModified_Then_CellIsRevived() {
        TestHelper.reviveCells(board, positionsOfThreeTestCellNeighbours);

        BoardInstructions instructions = analyser.determineBoardChanges(board);
        modifier.modifyBoard(instructions);

        assertTrue(testCell.isAlive());
    }

    @Test
    public void Given_BoardThatRequiresMultipleCellBirthsAndDeaths_When_BoardIsManipulated_Then_AllBirthsAndDeathsAreExecuted() {
        TestHelper.reviveCells(board, positionsPatternA);

        BoardInstructions instructions = analyser.determineBoardChanges(board);
        modifier.modifyBoard(instructions);

        Board expectedBoard = new Board(board.getWidth(), board.getHeight(), Collections.emptyList());
        TestHelper.reviveCells(expectedBoard, positionsPatternB);
        assertTrue(TestHelper.validateBoardsAreEqual(board, expectedBoard));
    }

}
