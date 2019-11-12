package io;

import core.Coordinates;
import utils.CoordinatesParser;
import utils.DataValidator;

import java.util.InputMismatchException;
import java.util.List;

public class UserInputReader {
    private IO io;
    private CoordinatesParser parser;

    private static final String BOARD_WIDTH_PROMPT = "What is the width of the World (in cells)? Please provide a whole number.\n";
    private static final String BOARD_HEIGHT_PROMPT = "What is the height of the World (in cells)? Please provide a whole number.\n";
    private static final String SIMULATION_LENGTH_PROMPT = "How long would you like the simulation to run (i.e. number of 'board evolutions')? Please provide a whole number.\n";
    private static final String CELL_POSITIONS_PROMPT = "What are the positions of the initially living cells? Please enter their coordinates.\n" +
            "Format: X coordinate (spaces from left), then whitespace, then Y coordinate (spaces from top). Place a comma between each set of coordinates.\n" +
            "i.e. The very top-left position is written as '0 0'.\n";

    private static final String INVALID_NUMBER_FORMAT_MESSAGE = "An integer is required here. Please try again.\n";
    private static final String INVALID_COORDINATE_FORMAT_MESSAGE = "core.Coordinates must be entered in the specified format. Please try again.\n";
    private static final String INVALID_NUMBER_OUTSIDE_RANGE_MESSAGE = "The number you specified is not within the required range. Please try again.\n";
    private static final String INVALID_COORDINATE_OUTSIDE_RANGE_MESSAGE = "One of the coordinates you specified was not within the required range. Please try again.\n";

    private static final String DEFAULT_COORDINATES_DELIMITER = ",";
    private static final String DEFAULT_XY_DELIMITER = "\\s";

    public UserInputReader(IO io) {
        this.io = io;
        this.parser = new CoordinatesParser(DEFAULT_COORDINATES_DELIMITER, DEFAULT_XY_DELIMITER);
    }

    public int getBoardWidthFromUser(int minWidth, int maxWidth) {
        io.displayOutput(BOARD_WIDTH_PROMPT);
        displayIntegerBoundariesToUser(minWidth, maxWidth);
        return getValidIntegerFromUser(minWidth, maxWidth);
    }

    public int getBoardHeightFromUser(int minHeight, int maxHeight) {
        io.displayOutput(BOARD_HEIGHT_PROMPT);
        displayIntegerBoundariesToUser(minHeight, maxHeight);
        return getValidIntegerFromUser(minHeight, maxHeight);
    }

    public int getNumberOfBoardEvolutionsFromUser(int minEvolutions, int maxEvolutions) {
        io.displayOutput(SIMULATION_LENGTH_PROMPT);
        displayIntegerBoundariesToUser(minEvolutions, maxEvolutions);
        return getValidIntegerFromUser(minEvolutions, maxEvolutions);
    }

    private void displayIntegerBoundariesToUser(int min, int max) {
        io.displayOutput("The minimum and maximum for this field are " + min + " and " + max + ", respectively.\n");
    }

    private int getValidIntegerFromUser(int min, int max) {
        String input = io.readUserInput();
        int integer;

        try {
            integer = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            io.displayOutput(INVALID_NUMBER_FORMAT_MESSAGE);
            return getValidIntegerFromUser(min, max);
        }

        if (!DataValidator.numberInRange(integer, min, max)) {
            io.displayOutput(INVALID_NUMBER_OUTSIDE_RANGE_MESSAGE);
            return getValidIntegerFromUser(min, max);
        }

        return integer;
    }

    public List<Coordinates> getLivingCellPositionsFromUser(Coordinates minBoundary, Coordinates maxBoundary) {
        io.displayOutput(CELL_POSITIONS_PROMPT);
        displayPositionBoundariesToUser(minBoundary, maxBoundary);
        return getValidPositionsFromUser(minBoundary, maxBoundary);
    }

    private void displayPositionBoundariesToUser(Coordinates min, Coordinates max) {
        io.displayOutput("The core.Coordinates must be between " + convertCoordinatesToString(min) + " and " + convertCoordinatesToString(max) + ".\n");
    }

    private String convertCoordinatesToString(Coordinates position) {
        return "(" + position.x + " " + position.y + ")";
    }

    private List<Coordinates> getValidPositionsFromUser(Coordinates min, Coordinates max) {
        String input = io.readUserInput();
        List<Coordinates> positions;

        try {
            positions = parser.parseCoordinates(input);
        } catch (InputMismatchException e) {
            io.displayOutput(INVALID_COORDINATE_FORMAT_MESSAGE);
            return getValidPositionsFromUser(min, max);
        }

        if (!DataValidator.positionsInRange(positions, min, max)) {
            io.displayOutput(INVALID_COORDINATE_OUTSIDE_RANGE_MESSAGE);
            return getValidPositionsFromUser(min, max);
        }

        return positions;
    }

}
