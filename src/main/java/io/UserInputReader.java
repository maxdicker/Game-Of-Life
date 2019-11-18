package io;

import core.Coordinates;

import java.util.InputMismatchException;
import java.util.List;

public class UserInputReader {
    private IO io;
    private CoordinatesParser parser;

    private static final String BOARD_WIDTH_PROMPT = "What is the width of the World (in cells)? Please provide a whole number.\n";
    private static final String BOARD_HEIGHT_PROMPT = "What is the height of the World (in cells)? Please provide a whole number.\n";
    private static final String SIMULATION_LENGTH_PROMPT = "How long would you like the simulation to run (i.e. number of 'board evolutions')? Please provide a whole number.\n";
    private static final String CELL_POSITIONS_PROMPT = "What are the positions of the initially living cells? Please enter their coordinates.\n" +
            "Format: X coordinate (spaces from left), then a comma, then the Y coordinate (spaces from top). Place a vertical bar ('|') between each coordinates set.\n" +
            "i.e. The very top-left position is written as '0,0'.\n";

    private static final String INVALID_NUMBER_FORMAT_MESSAGE = "An integer is required here. Please try again.\n";
    private static final String INVALID_COORDINATE_FORMAT_MESSAGE = "Coordinates must be entered in the specified format. Please try again.\n";
    private static final String INVALID_NUMBER_OUTSIDE_RANGE_MESSAGE = "The number you specified is not within the required range. Please try again.\n";
    private static final String INVALID_COORDINATE_OUTSIDE_RANGE_MESSAGE = "One of the coordinates you specified was not within the required range. Please try again.\n";

    public static final String DEFAULT_COORDINATES_DELIMITER = "\\|";
    public static final String DEFAULT_XY_DELIMITER = ",";

    public UserInputReader(IO io) {
        this.io = io;
        this.parser = new CoordinatesParser(DEFAULT_COORDINATES_DELIMITER, DEFAULT_XY_DELIMITER);
    }

    public int getBoardWidthFromUser(int minWidth, int maxWidth) {
        return getValidIntegerFromUser(BOARD_WIDTH_PROMPT, minWidth, maxWidth);
    }

    public int getBoardHeightFromUser(int minHeight, int maxHeight) {
        return getValidIntegerFromUser(BOARD_HEIGHT_PROMPT, minHeight, maxHeight);
    }

    public int getNumberOfBoardEvolutionsFromUser(int minEvolutions, int maxEvolutions) {
        return getValidIntegerFromUser(SIMULATION_LENGTH_PROMPT, minEvolutions, maxEvolutions);
    }

    private int getValidIntegerFromUser(String fieldPrompt, int min, int max) {
        io.displayOutput(fieldPrompt);
        displayIntegerBoundariesToUser(min, max);
        int integer;

        while (true) {
            String input = io.readUserInput();

            try {
                integer = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                io.displayOutput(INVALID_NUMBER_FORMAT_MESSAGE);
                continue;
            }

            if (!InputValidator.numberInRange(integer, min, max)) {
                io.displayOutput(INVALID_NUMBER_OUTSIDE_RANGE_MESSAGE);
                continue;
            }

            break;
        }

        return integer;
    }

    private void displayIntegerBoundariesToUser(int min, int max) {
        io.displayOutput("The minimum and maximum for this field are " + min + " and " + max + ", respectively.\n");
    }

    public List<Coordinates> getLivingCellPositionsFromUser(Coordinates minBoundary, Coordinates maxBoundary) {
        io.displayOutput(CELL_POSITIONS_PROMPT);
        displayPositionBoundariesToUser(minBoundary, maxBoundary);
        List<Coordinates> positions;

        while (true) {
            String input = io.readUserInput();

            try {
                positions = parser.parseCoordinates(input);
            } catch (InputMismatchException e) {
                io.displayOutput(INVALID_COORDINATE_FORMAT_MESSAGE);
                continue;
            }

            if (!InputValidator.positionsInRange(positions, minBoundary, maxBoundary)) {
                io.displayOutput(INVALID_COORDINATE_OUTSIDE_RANGE_MESSAGE);
                continue;
            }

            break;
        }

        return positions;
    }

    private void displayPositionBoundariesToUser(Coordinates min, Coordinates max) {
        io.displayOutput("The core.Coordinates must be between " + convertCoordinatesToString(min) + " and " + convertCoordinatesToString(max) + ".\n");
    }

    private String convertCoordinatesToString(Coordinates position) {
        return "(" + position.x + "," + position.y + ")";
    }

}
