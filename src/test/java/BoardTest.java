import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {
    private int boardWidth = 5;
    private int boardHeight = 5;

    private Coordinates testCellPosition = new Coordinates(0, 0);
    private Coordinates positionOfTestCellNeighbour = new Coordinates(0, 1);
    private List<Coordinates> positionsOfAllTestCellsNeighbours =
            Arrays.asList(new Coordinates(4, 4), new Coordinates(0, 4), new Coordinates(1, 4),
                    new Coordinates(1, 0), new Coordinates(1, 1), new Coordinates(0, 1),
                    new Coordinates(4, 1), new Coordinates(4, 0));

    @Test
    public void When_CreatingNewBoard_Then_NumberOfCellsIsEqualToBoardWidthMultipliedByBoardHeight() {
        Board board = new Board(boardWidth, boardHeight, Collections.emptyList());

        int expected = boardWidth * boardHeight;
        int actual = board.getCells().size();
        assertEquals(expected, actual);
    }

    @Test
    public void Given_ASpecificPosition_When_CreatingNewBoard_Then_PositionIsOccupiedByLivingCell() {
        Board board = new Board(boardWidth, boardHeight, Collections.singletonList(testCellPosition));

        assertTrue(board.getCell(testCellPosition).isAlive());
    }

    @Test
    public void Given_NewBoard_When_FindingNeighboursOfACell_Then_ReturnsCellsInTheEightNeighbouringPositions() {
        Board board = new Board(boardWidth, boardHeight, Collections.emptyList());
        Cell testCell = board.getCell(testCellPosition);

        Cell[] neighbours = board.getNeighbours(testCell);
        List<Coordinates> positionsOfNeighbours = getCellPositions(board, neighbours);

        assertEquals(positionsOfAllTestCellsNeighbours, positionsOfNeighbours);
    }

    @Test
    public void Given_ANeighboursStateHasChanged_When_FindingACellsNeighbours_Then_ReturnsUpdatedNeighbours() {
        Board board = new Board(boardWidth, boardHeight, Collections.emptyList());
        Cell testCell = board.getCell(testCellPosition);
        board.getCell(positionOfTestCellNeighbour).revive();

        Cell[] neighbours = board.getNeighbours(testCell);
        int numberOfLivingNeighbours = countLivingCells(neighbours);

        assertEquals(1, numberOfLivingNeighbours);
    }

    private List<Coordinates> getCellPositions(Board board, Cell[] cells) {
        List<Coordinates> positions = new ArrayList<>();
        for (Cell cell : cells) {
            positions.add(board.getCellPosition(cell));
        }
        return positions;
    }

    private int countLivingCells(Cell[] cells) {
        int total = 0;
        for (Cell cell : cells) {
            if (cell.isAlive()) {
                total++;
            }
        }
        return total;
    }

}
