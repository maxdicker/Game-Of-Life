package core;

import io.GameDisplay;
import io.IO;
import io.UserInputReader;
import org.junit.Test;
import utils.StubIO;
import utils.TestHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class GameTest {

    @Test
    public void Given_InputToSetup_When_RunningGame_Then_ReturnCorrectlyTransformedBoard() {
        String boardWidth = "5";
        String boardHeight = "5";
        String livingCellPositions = "1,2|2,3|3,3|3,2|3,1";
        String numberOfEvolutions = "2";
        List<String> stubInput = Arrays.asList(boardWidth, boardHeight, livingCellPositions, numberOfEvolutions);
        IO stubIO = new StubIO(stubInput);
        UserInputReader reader = new UserInputReader(stubIO);
        GameDisplay display = new GameDisplay(stubIO);
        Game game = new Game(reader, display);

        Board actual = game.run();

        Board expected = new Board(5, 5, Collections.emptyList());
        TestHelper.reviveCells(expected, Arrays.asList(new Coordinates(3,1), new Coordinates(4,2),
                new Coordinates(2,3), new Coordinates(3,3), new Coordinates(4,3)));

        assertTrue(TestHelper.validateBoardsAreEqual(actual, expected));
    }

}
