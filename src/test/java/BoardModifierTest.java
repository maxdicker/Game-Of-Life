import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BoardModifierTest {
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
        BoardModifier modifier = new BoardModifier();
        Board testBoard = new Board(boardWidth, boardHeight, OscillatorPatternA);

        testBoard = modifier.nextState(testBoard);

        Board expectedBoard = new Board(boardWidth, boardHeight, OscillatorPatternB);
        assertEquals(expectedBoard, testBoard);
    }

    @Test
    public void Given_LivingCellWithLessThanTwoLivingNeighbours_When_BoardIsModified_Then_CellDies() {
        BoardModifier modifier = new BoardModifier();
        Board board = new Board(boardWidth, boardHeight, positionOfTestCellAndOneNeighbour);

        board = modifier.nextState(board);
        Cell testCell = board.getCell(testCellPosition);

        assertFalse(testCell.isAlive());
    }

    @Test
    public void Given_LivingCellWithMoreThanThreeLivingNeighbours_When_BoardIsModified_Then_CellDies() {
        BoardModifier modifier = new BoardModifier();
        Board board = new Board(boardWidth, boardHeight, positionsOfTestCellAndFourNeighbours);

        board = modifier.nextState(board);
        Cell testCell = board.getCell(testCellPosition);

        assertFalse(testCell.isAlive());
    }

    @Test
    public void Given_LivingCellWithTwoLivingNeighbours_When_BoardIsModified_Then_CellIsStillAlive() {
        BoardModifier modifier = new BoardModifier();
        Board board = new Board(boardWidth, boardHeight, positionsOfTestCellAndTwoNeighbours);

        board = modifier.nextState(board);
        Cell testCell = board.getCell(testCellPosition);

        assertTrue(testCell.isAlive());
    }

    @Test
    public void Given_LivingCellWithThreeLivingNeighbours_When_BoardIsModified_Then_CellIsStillAlive() {
        BoardModifier modifier = new BoardModifier();
        Board board = new Board(boardWidth, boardHeight, positionsOfTestCellAndThreeNeighbours);

        board = modifier.nextState(board);
        Cell testCell = board.getCell(testCellPosition);

        assertTrue(testCell.isAlive());
    }

    @Test
    public void Given_DeadCellWithExactlyThreeLivingNeighbours_When_BoardIsModified_Then_CellIsRevived() {
        BoardModifier modifier = new BoardModifier();
        Board board = new Board(boardWidth, boardHeight, positionsOfThreeTestCellNeighbours);

        board = modifier.nextState(board);
        Cell testCell = board.getCell(testCellPosition);

        assertTrue(testCell.isAlive());
    }
}
