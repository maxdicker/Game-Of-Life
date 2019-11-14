import core.Board;
import core.Coordinates;
import core.Game;
import io.GameDisplay;
import io.IO;
import io.UserInputReader;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    private final String ALIVE_CELL = "\u25FC";
    private final String DEAD_CELL = "\u25FB";

    @Test
    public void Given_InputToGenerateBoard_When_RunningGame_Then_GameReturnsCorrectBoard() {
        String boardWidth = "5";
        String boardHeight = "5";
        String livingCellPositions = "2,1|2,2|2,3";
        String numberOfEvolutions = "5";
        List<String> stubInput = Arrays.asList(boardWidth, boardHeight, livingCellPositions, numberOfEvolutions);
        IO stubIO = new StubIO(stubInput);
        UserInputReader reader = new UserInputReader(stubIO);
        GameDisplay display = new GameDisplay(stubIO);
        Game game = new Game(reader, display);

        Board actualBoard = game.run();

        Board expectedBoard = new Board(5, 5, Arrays.asList(new Coordinates(1,2), new Coordinates(2,2), new Coordinates(3,2)));
        assertTrue(TestHelper.validateBoardsAreEqual(actualBoard, expectedBoard));
    }

    @Test
    public void Given_InputToGenerateBoard_When_RunningGame_Then_GameDisplaysCorrectBoard() {
        String boardWidth = "5";
        String boardHeight = "5";
        String livingCellPositions = "2,1|2,2|2,3";
        String numberOfEvolutions = "1";
        List<String> stubInput = Arrays.asList(boardWidth, boardHeight, livingCellPositions, numberOfEvolutions);
        IO stubIO = new StubIO(stubInput);
        UserInputReader reader = new UserInputReader(stubIO);
        GameDisplay display = new GameDisplay(stubIO);
        Game game = new Game(reader, display);

        game.run();

        List<String> output = ((StubIO) stubIO).getOutput();

        assertTrue(output.get(output.size() - 1).equals(DEAD_CELL + DEAD_CELL + DEAD_CELL + DEAD_CELL + DEAD_CELL + "\n" +
                DEAD_CELL + DEAD_CELL + DEAD_CELL + DEAD_CELL + DEAD_CELL + "\n" +
                DEAD_CELL + ALIVE_CELL + ALIVE_CELL + ALIVE_CELL + DEAD_CELL + "\n" +
                DEAD_CELL + DEAD_CELL + DEAD_CELL + DEAD_CELL + DEAD_CELL + "\n" +
                DEAD_CELL + DEAD_CELL + DEAD_CELL + DEAD_CELL + DEAD_CELL + "\n"));
    }

}
