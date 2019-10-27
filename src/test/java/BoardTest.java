import org.junit.Test;

import java.util.Collections;

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
    public void newBoardCreatesLiveCells() {
        int boardWidth = 5;
        int boardHeight = 5;
        Coordinates positionOfAliveCell = new Coordinates(4, 4);
        Board board = new Board(boardWidth, boardHeight, Collections.singletonList(positionOfAliveCell));

        Cell aliveCell = board.getCell(positionOfAliveCell);
        assertTrue(aliveCell.isAlive());
    }
}
