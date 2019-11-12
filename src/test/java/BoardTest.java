import core.Board;
import core.Cell;
import core.Coordinates;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {
    private int boardWidth = 5;
    private int boardHeight = 5;

    private Coordinates testCellPosition = new Coordinates(0, 0);
    private Coordinates positionOfATestCellNeighbour = new Coordinates(0, 1);
    private List<Coordinates> positionsOfAllTestCellsNeighbours =
            Arrays.asList(new Coordinates(4, 4), new Coordinates(0, 4), new Coordinates(1, 4),
                    new Coordinates(1, 0), new Coordinates(1, 1), new Coordinates(0, 1),
                    new Coordinates(4, 1), new Coordinates(4, 0));

    @Test
    public void NewBoardContainsNumberOfCellsEqualToBoardWidthMultipliedByBoardHeight() {
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
    public void Given_ASpecificPosition_Then_PositionOnNewBoardIsOccupiedByLivingCell() {
        Board board = new Board(boardWidth, boardHeight, Collections.singletonList(testCellPosition));

        assertTrue(board.getCell(testCellPosition).isAlive());
    }

    @Test
    public void When_RetrievingNeighboursOfACellFromBoard_Then_BoardReturnsCellsInTheEightNeighbouringPositions() {
        Board board = new Board(boardWidth, boardHeight, Collections.emptyList());
        Cell testCell = board.getCell(testCellPosition);

        Cell[] actual = board.getNeighbours(testCell);

        Cell[] expected = TestHelper.getCells(board, positionsOfAllTestCellsNeighbours);
        assertArrayEquals(actual, expected);
    }

    @Test
    public void Given_ACellsNeighboursStateHasChanged_When_RetrievingTheCellsNeighbours_Then_BoardReturnsUpdatedNeighbour() {
        Board board = new Board(boardWidth, boardHeight, Collections.emptyList());
        Cell testCell = board.getCell(testCellPosition);

        board.getCell(positionOfATestCellNeighbour).revive();
        Cell[] neighbours = board.getNeighbours(testCell);

        int actual = TestHelper.countLivingCells(Arrays.asList(neighbours));
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void When_QueryingNumberOfLivingNeighboursFromBoard_Then_BoardReturnsNumberOfLivingNeighbours() {
        Board board = new Board(boardWidth, boardHeight, Collections.emptyList());
        Cell testCell = board.getCell(testCellPosition);

        TestHelper.reviveCells(board, positionsOfAllTestCellsNeighbours);
        int actual = board.countLivingNeighbours(testCell);

        int expected = positionsOfAllTestCellsNeighbours.size();
        assertEquals(expected, actual);
    }

}
