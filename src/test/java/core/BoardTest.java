package core;

import org.junit.Test;
import utils.TestHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {
    private static final int boardWidth = 5;
    private static final int boardHeight = 5;

    private static final Coordinates testCellPosition = new Coordinates(0, 0);
    private static final Coordinates positionOfATestCellNeighbour = new Coordinates(0, 1);
    private static final List<Coordinates> positionsOfAllTestCellsNeighbours = Arrays.asList(
            new Coordinates(4, 4), new Coordinates(0, 4), new Coordinates(1, 4),
            new Coordinates(1, 0), new Coordinates(1, 1), new Coordinates(0, 1),
            new Coordinates(4, 1), new Coordinates(4, 0));

    @Test
    public void ContainsNumberOfCellsEqualToWidthMultipliedByHeight() {
        Board board = new Board(boardWidth, boardHeight, Collections.emptyList());

        int actual = board.getCells().size();

        int expected = boardWidth * boardHeight;
        assertEquals(expected, actual);
    }

    @Test
    public void Given_NoPositions_Then_NewBoardContainsOnlyDeadCells() {
        Board board = new Board(boardWidth, boardHeight, Collections.emptyList());

        int actual = TestHelper.countLivingCells(board.getCells());

        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void Given_ASpecificPosition_Then_ThatPositionOnNewBoardIsOccupiedByLivingCell() {
        Board board = new Board(boardWidth, boardHeight, Collections.singletonList(testCellPosition));

        assertTrue(board.getCellAtPosition(testCellPosition).isAlive());
    }

    @Test
    public void When_RetrievingNeighboursOfACell_Then_ReturnCellsInTheEightNeighbouringPositions() {
        Board board = new Board(boardWidth, boardHeight, Collections.emptyList());
        Cell testCell = board.getCellAtPosition(testCellPosition);

        Cell[] actual = board.getNeighbours(testCell);

        List<Cell> expected = TestHelper.getCells(board, positionsOfAllTestCellsNeighbours);
        assertArrayEquals(expected.toArray(), actual);
    }

    @Test
    public void Given_ANeighboursStateHasChanged_When_RetrievingNeighboursOfACell_Then_ReturnUpdatedNeighbour() {
        Board board = new Board(boardWidth, boardHeight, Collections.emptyList());
        Cell testCell = board.getCellAtPosition(testCellPosition);
        board.getCellAtPosition(positionOfATestCellNeighbour).revive();

        Cell[] neighbours = board.getNeighbours(testCell);
        int actual = TestHelper.countLivingCells(Arrays.asList(neighbours));

        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void DeterminesNumberOfLivingNeighbours() {
        Board board = new Board(boardWidth, boardHeight, Collections.emptyList());
        Cell testCell = board.getCellAtPosition(testCellPosition);

        TestHelper.reviveCells(board, positionsOfAllTestCellsNeighbours);
        int actual = board.countLivingNeighbours(testCell);

        int expected = positionsOfAllTestCellsNeighbours.size();
        assertEquals(expected, actual);
    }

}
