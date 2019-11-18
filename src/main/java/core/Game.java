package core;

import io.GameDisplay;
import io.UserInputReader;

import java.util.List;

public class Game {
    private UserInputReader reader;
    private GameDisplay display;
    private BoardAnalyser analyser;
    private BoardModifier modifier;

    private static final int MIN_BOARD_WIDTH = 2;
    private static final int MAX_BOARD_WIDTH = 100;
    private static final int MIN_BOARD_HEIGHT = 2;
    private static final int MAX_BOARD_HEIGHT = 100;
    private static final Coordinates MIN_BOARD_BOUNDARY = new Coordinates(0,0);
    private static final int MIN_SIMULATION_LENGTH = 0;
    private static final int MAX_SIMULATION_LENGTH = 100;

    private static final int TIME_BETWEEN_UPDATES = 750;

    public Game(UserInputReader reader, GameDisplay display) {
        this.reader = reader;
        this.display = display;
        this.analyser = new BoardAnalyser();
        this.modifier = new BoardModifier();
    }

    public Board run() {
        Board board = createUserDefinedBoard();

        int evolutions = reader.getNumberOfBoardEvolutionsFromUser(MIN_SIMULATION_LENGTH, MAX_SIMULATION_LENGTH);

        display.displayBoard(board);

        for (int evolution = 0; evolution < evolutions; evolution++) {
            try {
                Thread.sleep(TIME_BETWEEN_UPDATES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            BoardInstructions instructions = analyser.determineBoardChanges(board);
            modifier.modifyBoard(instructions);
            display.displayBoard(board);
        }

        return board;
    }

    private Board createUserDefinedBoard() {
        int width = reader.getBoardWidthFromUser(MIN_BOARD_WIDTH, MAX_BOARD_WIDTH);
        int height = reader.getBoardHeightFromUser(MIN_BOARD_HEIGHT, MAX_BOARD_HEIGHT);
        Coordinates maxBoardBoundary = new Coordinates(width - 1, height - 1);
        List<Coordinates> livingCellPositions = reader.getLivingCellPositionsFromUser(MIN_BOARD_BOUNDARY, maxBoardBoundary);

        return new Board(width, height, livingCellPositions);
    }

}
