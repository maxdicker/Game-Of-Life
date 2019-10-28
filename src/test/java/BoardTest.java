import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void newBoardInstantiatesCorrectNumberOfCells() {
        int boardWidth = 5;
        int boardHeight = 5;
        int expectedTotalCells = boardWidth * boardHeight;
        Board board = new Board(boardWidth, boardHeight, Collections.emptyList());

        assertEquals(expectedTotalCells, board.getCells().size());
    }

    @Test
    public void newBoardInstantiatesLivingCellAtSpecifiedPosition() {
        int boardWidth = 5;
        int boardHeight = 5;
        Coordinates positionOfLiveCell = new Coordinates(4, 4);
        Board board = new Board(boardWidth, boardHeight, Collections.singletonList(positionOfLiveCell));

        Cell aliveCell = board.getCell(positionOfLiveCell);
        assertTrue(aliveCell.isAlive());
    }

    @Test
    public void boardCorrectlyIdentifiesNeighbours() {
        int boardWidth = 5;
        int boardHeight = 5;
        Coordinates referenceCellPosition = new Coordinates(0, 0);
        List<Coordinates> positionsOfNeighbours = Arrays.asList(new Coordinates(1, 0), new Coordinates(1, 1),
                                                        new Coordinates(0, 1), new Coordinates(4, 1),
                                                        new Coordinates(4, 0), new Coordinates(4, 4),
                                                        new Coordinates(0, 4), new Coordinates(1, 4));
        Board board = new Board(boardWidth, boardHeight, positionsOfNeighbours);

        Cell referenceCell = board.getCell(referenceCellPosition);

        boolean neighboursAllAlive = true;
        for (Cell cell : board.getNeighbours(referenceCell)) {
            if (!cell.isAlive()) {
                neighboursAllAlive = false;
                break;
            }
        }

        assertTrue(neighboursAllAlive);
    }
}
