import core.Board;
import core.Coordinates;
import core.Game;
import io.GameDisplay;
import io.IO;
import io.UserInputReader;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void Given_InputToGenerateBoard_When_RunningGame_Then_GameReturnsCorrectBoard() {
        UserInputReader mockReader = mock(UserInputReader.class);
        when(mockReader.getBoardWidthFromUser(2,100)).thenReturn(5);
        when(mockReader.getBoardHeightFromUser(2,100)).thenReturn(5);
        when(mockReader.getLivingCellPositionsFromUser(new Coordinates(0,0), new Coordinates(4,4))).thenReturn(Arrays.asList(new Coordinates(2,1), new Coordinates(2,2), new Coordinates(2,3)));
        when(mockReader.getNumberOfBoardEvolutionsFromUser(0,100)).thenReturn(5);
        IO stubIO = new StubIO("");
        GameDisplay display = new GameDisplay(stubIO);
        Game game = new Game(mockReader, display);

        Board actualBoard = game.run();

        Board expectedBoard = new Board(5, 5, Arrays.asList(new Coordinates(1,2), new Coordinates(2,2), new Coordinates(3,2)));
        assertTrue(TestHelper.validateBoardsAreEqual(actualBoard, expectedBoard));
    }

}
