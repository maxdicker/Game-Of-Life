package core;

import io.GameDisplay;
import io.UserInputReader;

import java.util.List;

public class Game {
    private UserInputReader reader;
    private GameDisplay display;
    private BoardModifier modifier;

    private final int MIN_BOARD_WIDTH = 2;
    private final int MAX_BOARD_WIDTH = 100;
    private final int MIN_BOARD_HEIGHT = 2;
    private final int MAX_BOARD_HEIGHT = 100;
    private final Coordinates MIN_BOARD_BOUNDARY = new Coordinates(0,0);
    private final int MIN_SIMULATION_LENGTH = 0;
    private final int MAX_SIMULATION_LENGTH = 100;
    private final int TIME_BETWEEN_UPDATES = 750;

    public Game(UserInputReader reader, GameDisplay display) {
        this.reader = reader;
        this.display = display;
        this.modifier = new BoardModifier();
    }

    public void run() {
        Board board = createUserDefinedBoard();

        int evolutions = reader.getNumberOfBoardEvolutionsFromUser(MIN_SIMULATION_LENGTH, MAX_SIMULATION_LENGTH);

        for (int generation = 0; generation <= evolutions; generation++) {
            display.displayBoard(board);
            board = modifier.nextGeneration(board);

            try {
                Thread.sleep(TIME_BETWEEN_UPDATES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private Board createUserDefinedBoard() {
        int width = reader.getBoardWidthFromUser(MIN_BOARD_WIDTH, MAX_BOARD_WIDTH);
        int height = reader.getBoardHeightFromUser(MIN_BOARD_HEIGHT, MAX_BOARD_HEIGHT);
        Coordinates maxBoardBoundary = new Coordinates(width - 1, height - 1);
        List<Coordinates> livingCellPositions = reader.getLivingCellPositionsFromUser(MIN_BOARD_BOUNDARY, maxBoardBoundary);

        return new Board(width, height, livingCellPositions);
    }

}
