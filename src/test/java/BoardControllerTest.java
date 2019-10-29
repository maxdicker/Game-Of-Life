import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BoardControllerTest {
    private int boardWidth = 5;
    private int boardHeight = 5;
    private List<Coordinates> positionsOfLivingCellsInBlinkerA =
            Arrays.asList(new Coordinates(2, 1), new Coordinates(2, 2), new Coordinates(2, 3));
    private List<Coordinates> positionsOfLivingCellsInBlinkerB =
            Arrays.asList(new Coordinates(1, 2), new Coordinates(2, 2), new Coordinates(3, 2));

    @Test
    public void givenBoardWithBlinkerAPattern_WhenManipulatingBoard_BoardShouldChangeToBlinkerB() {
        BoardController controller = new BoardController();
        Board testBoard = new Board(boardWidth, boardHeight, positionsOfLivingCellsInBlinkerA);

        testBoard = controller.nextGeneration(testBoard);

        Board expectedBoard = new Board(boardWidth, boardHeight, positionsOfLivingCellsInBlinkerB);
        assertEquals(expectedBoard, testBoard);
    }
}
