package io;

import core.Board;
import core.Coordinates;
import org.junit.Test;
import utils.StubIO;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameDisplayTest {
    private static final String DEAD = GameDisplay.DEAD_CELL;
    private static final String ALIVE = GameDisplay.ALIVE_CELL;

    @Test
    public void DisplaysBoardInCorrectFormat() {
        List<Coordinates> livingCellPositions = Arrays.asList(
                new Coordinates(1,1),new Coordinates(2,1),new Coordinates(3,1),
                new Coordinates(1,2),                           new Coordinates(3,2),
                new Coordinates(1,3),new Coordinates(2,3),new Coordinates(3,3));
        Board board = new Board(5,5,livingCellPositions);
        StubIO stubIO = new StubIO("");
        GameDisplay display = new GameDisplay(stubIO);

        display.displayBoard(board);
        String actual = stubIO.getLastOutput();

        String expected =
                DEAD + DEAD + DEAD + DEAD + DEAD + "\n" +
                DEAD + ALIVE + ALIVE + ALIVE + DEAD + "\n" +
                DEAD + ALIVE + DEAD + ALIVE + DEAD + "\n" +
                DEAD + ALIVE + ALIVE + ALIVE + DEAD + "\n" +
                DEAD + DEAD + DEAD + DEAD + DEAD + "\n";
        assertEquals(expected, actual);
    }

}
