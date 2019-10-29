import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BoardControllerTest {
    private int boardWidth = 5;
    private int boardHeight = 5;

    private Coordinates testCellPosition = new Coordinates(0, 0);
    private List<Coordinates> positionOfTestCellAndOneNeighbour =
            Arrays.asList(testCellPosition, new Coordinates(0, 1));

    private List<Coordinates> positionsOfTestCellAndTwoNeighbours =
            Arrays.asList(testCellPosition, new Coordinates(0, 1), new Coordinates(1, 1));

    private List<Coordinates> positionsOfTestCellAndThreeNeighbours =
            Arrays.asList(testCellPosition, new Coordinates(0, 1), new Coordinates(1, 1),
                    new Coordinates(1, 0));

    private List<Coordinates> positionsOfThreeTestCellNeighbours =
            Arrays.asList(new Coordinates(0, 1), new Coordinates(1, 1), new Coordinates(1, 0));

    private List<Coordinates> positionsOfTestCellAndFourNeighbours =
            Arrays.asList(testCellPosition, new Coordinates(0, 1), new Coordinates(1, 1),
                    new Coordinates(1, 0), new Coordinates(4, 4));

    private List<Coordinates> OscillatorPatternA =
            Arrays.asList(new Coordinates(2, 1), new Coordinates(2, 2), new Coordinates(2, 3));
    private List<Coordinates> OscillatorPatternB =
            Arrays.asList(new Coordinates(1, 2), new Coordinates(2, 2), new Coordinates(3, 2));

    @Test
    public void givenABoardWithOscillatingPattern_WhenBoardIsManipulated_BoardShouldResembleOscillator() {
        BoardController controller = new BoardController();
        Board testBoard = new Board(boardWidth, boardHeight, OscillatorPatternA);

        testBoard = controller.nextGeneration(testBoard);

        Board expectedBoard = new Board(boardWidth, boardHeight, OscillatorPatternB);
        assertEquals(expectedBoard, testBoard);
    }

    @Test
    public void givenLivingCellWithLessThanTwoLivingNeighbours_WhenBoardIsManipulated_CellShouldDie() {
        BoardController controller = new BoardController();
        Board board = new Board(boardWidth, boardHeight, positionOfTestCellAndOneNeighbour);

        board = controller.nextGeneration(board);
        Cell cell = board.getCell(testCellPosition);

        assertFalse(cell.isAlive());
    }

    @Test
    public void givenLivingCellWithMoreThanThreeLivingNeighbours_WhenBoardIsManipulated_CellShouldDie() {
        BoardController controller = new BoardController();
        Board board = new Board(boardWidth, boardHeight, positionsOfTestCellAndFourNeighbours);

        board = controller.nextGeneration(board);
        Cell cell = board.getCell(testCellPosition);

        assertFalse(cell.isAlive());
    }

    @Test
    public void givenLivingCellWithTwoLivingNeighbours_WhenBoardIsManipulated_CellShouldStillBeAlive() {
        BoardController controller = new BoardController();
        Board board = new Board(boardWidth, boardHeight, positionsOfTestCellAndTwoNeighbours);

        board = controller.nextGeneration(board);
        Cell cell = board.getCell(testCellPosition);

        assertTrue(cell.isAlive());
    }

    @Test
    public void givenLivingCellWithThreeLivingNeighbours_WhenBoardIsManipulated_CellShouldStillBeAlive() {
        BoardController controller = new BoardController();
        Board board = new Board(boardWidth, boardHeight, positionsOfTestCellAndThreeNeighbours);

        board = controller.nextGeneration(board);
        Cell cell = board.getCell(testCellPosition);

        assertTrue(cell.isAlive());
    }

    @Test
    public void givenDeadCellWithExactlyThreeLivingNeighbours_WhenBoardIsManipulated_CellShouldBeRevived() {
        BoardController controller = new BoardController();
        Board board = new Board(boardWidth, boardHeight, positionsOfThreeTestCellNeighbours);

        board = controller.nextGeneration(board);
        Cell cell = board.getCell(testCellPosition);

        assertTrue(cell.isAlive());
    }
}
