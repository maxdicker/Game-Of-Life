package core;

import io.GameDisplay;
import io.IO;
import io.UserInputReader;
import org.junit.Test;
import utils.StubIO;
import utils.TestHelper;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class GameTest {

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

}
