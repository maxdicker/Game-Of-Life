import core.Board;
import core.BoardModifier;
import core.Cell;
import core.Coordinates;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class BoardModificationTest {
    private int boardWidth = 5;
    private int boardHeight = 5;

    private Coordinates testCellPosition = new Coordinates(0, 0);
    private List<Coordinates> positionsOfTestCellAndOneNeighbour =
            Arrays.asList(new Coordinates(0, 0), new Coordinates(0, 1));

    private List<Coordinates> positionsOfTestCellAndTwoNeighbours =
            Arrays.asList(new Coordinates(0, 0), new Coordinates(0, 1), new Coordinates(1, 1));

    private List<Coordinates> positionsOfTestCellAndThreeNeighbours =
            Arrays.asList(new Coordinates(0, 0), new Coordinates(0, 1), new Coordinates(1, 1),
                    new Coordinates(1, 0));

    private List<Coordinates> positionsOfThreeTestCellNeighbours =
            Arrays.asList(new Coordinates(0, 1), new Coordinates(1, 1), new Coordinates(1, 0));

    private List<Coordinates> positionsOfTestCellAndFourNeighbours =
            Arrays.asList(new Coordinates(0, 0), new Coordinates(0, 1), new Coordinates(1, 1),
                    new Coordinates(1, 0), new Coordinates(4, 4));

    private List<Coordinates> OscillatorPatternA =
            Arrays.asList(new Coordinates(2, 1), new Coordinates(2, 2), new Coordinates(2, 3));
    private List<Coordinates> OscillatorPatternB =
            Arrays.asList(new Coordinates(1, 2), new Coordinates(2, 2), new Coordinates(3, 2));

    @Test
    public void Given_BoardWithLivingCellThatHasLessThanTwoLivingNeighbours_When_BoardIsModified_Then_CellDies() {
        BoardModifier modifier = new BoardModifier();
        Board board = new Board(boardWidth, boardHeight, Collections.emptyList());
        TestHelper.reviveCells(board, positionsOfTestCellAndOneNeighbour);

        modifier.nextGeneration(board);

        Cell testCell = board.getCellByPosition(testCellPosition);
        assertFalse(testCell.isAlive());
    }

    @Test
    public void Given_BoardWithLivingCellThatHasMoreThanThreeLivingNeighbours_When_BoardIsModified_Then_CellDies() {
        BoardModifier modifier = new BoardModifier();
        Board board = new Board(boardWidth, boardHeight, Collections.emptyList());
        TestHelper.reviveCells(board, positionsOfTestCellAndFourNeighbours);

        modifier.nextGeneration(board);

        Cell testCell = board.getCellByPosition(testCellPosition);
        assertFalse(testCell.isAlive());
    }

    @Test
    public void Given_BoardWithLivingCellThatHasTwoLivingNeighbours_When_BoardIsModified_Then_CellIsStillAlive() {
        BoardModifier modifier = new BoardModifier();
        Board board = new Board(boardWidth, boardHeight, Collections.emptyList());
        TestHelper.reviveCells(board, positionsOfTestCellAndTwoNeighbours);

        modifier.nextGeneration(board);

        Cell testCell = board.getCellByPosition(testCellPosition);
        assertTrue(testCell.isAlive());
    }

    @Test
    public void Given_BoardWithLivingCellThatHasThreeLivingNeighbours_When_BoardIsModified_Then_CellIsStillAlive() {
        BoardModifier modifier = new BoardModifier();
        Board board = new Board(boardWidth, boardHeight, Collections.emptyList());
        TestHelper.reviveCells(board, positionsOfTestCellAndThreeNeighbours);

        modifier.nextGeneration(board);

        Cell testCell = board.getCellByPosition(testCellPosition);
        assertTrue(testCell.isAlive());
    }

    @Test
    public void Given_BoardWithDeadCellThatHasThreeLivingNeighbours_When_BoardIsModified_Then_CellIsRevived() {
        BoardModifier modifier = new BoardModifier();
        Board board = new Board(boardWidth, boardHeight, Collections.emptyList());
        TestHelper.reviveCells(board, positionsOfThreeTestCellNeighbours);

        modifier.nextGeneration(board);

        Cell testCell = board.getCellByPosition(testCellPosition);
        assertTrue(testCell.isAlive());
    }

    @Test
    public void Given_BoardThatRequiresMultipleCellBirthsAndDeaths_When_BoardIsManipulated_Then_AllRequiredBirthsAndDeathsAreExecuted() {
        BoardModifier modifier = new BoardModifier();
        Board actualBoard = new Board(boardWidth, boardHeight, Collections.emptyList());
        TestHelper.reviveCells(actualBoard, OscillatorPatternA);

        modifier.nextGeneration(actualBoard);

        Board expectedBoard = new Board(boardWidth, boardHeight, OscillatorPatternB);
        assertTrue(TestHelper.validateBoardsAreEqual(actualBoard, expectedBoard));
    }

}
