import java.util.InputMismatchException;
import java.util.List;

public class UserHandler {
    private IO io;
    private CoordinatesParser parser;

    private final String BOARD_WIDTH_PROMPT = "What is the width of the World (in cells)? Please provide a whole number.\n";
    private final String BOARD_HEIGHT_PROMPT = "What is the height of the World (in cells)? Please provide a whole number.\n";
    private final String GAME_LENGTH_PROMPT = "How long would you like the simulation to run (i.e. number of 'game ticks')? Please provide a whole number.\n";
    private final String CELL_POSITIONS_PROMPT = "What are the positions of the initially living cells? Please enter their coordinates.\n" +
            "Format: X coordinate (spaces from left), then whitespace, then Y coordinate (spaces from top). Place a comma between each set of coordinates.\n" +
            "i.e. The very top-left position is written as '0 0'.\n";

    private final String INVALID_NUMBER_FORMAT_MESSAGE = "An integer is required here. Please try again.\n";
    private final String INVALID_COORDINATE_FORMAT_MESSAGE = "Coordinates must be entered in the specified format. Please try again.\n";
    private final String INVALID_NUMBER_OUTSIDE_RANGE_MESSAGE = "The number you specified is not within the required range. Please try again.\n";
    private final String INVALID_COORDINATE_OUTSIDE_RANGE_MESSAGE = "One of the coordinates you specified was not within the required range. Please try again.\n";

    private final String DEFAULT_COORDINATES_DELIMITER = ",";
    private final String DEFAULT_XY_DELIMITER = "\\s";

    public UserHandler(IO io) {
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

    public int getSimulationLengthFromUser(int minLength, int maxLength) {
        io.displayOutput(GAME_LENGTH_PROMPT);
        displayIntegerBoundariesToUser(minLength, maxLength);
        return getValidIntegerFromUser(minLength, maxLength);
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

        if (!InputValidator.numberInRange(integer, min, max)) {
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

    private List<Coordinates> getValidPositionsFromUser(Coordinates min, Coordinates max) {
        String input = io.readUserInput();
        List<Coordinates> positions;

        try {
            positions = parser.parseCoordinates(input);
        } catch (InputMismatchException e) {
            io.displayOutput(INVALID_COORDINATE_FORMAT_MESSAGE);
            return getValidPositionsFromUser(min, max);
        }

        if (!positionsInRange(positions, min, max)) {
            io.displayOutput(INVALID_COORDINATE_OUTSIDE_RANGE_MESSAGE);
            return getValidPositionsFromUser(min, max);
        }

        return positions;
    }

    private boolean positionsInRange(List<Coordinates> positions, Coordinates min, Coordinates max) {
        for (Coordinates position : positions) {
            if (!InputValidator.positionInRange(position, min, max)) {
                return false;
            }
        }
        return true;
    }

    private void displayPositionBoundariesToUser(Coordinates min, Coordinates max) {
        io.displayOutput("The Coordinates must be between " + min.toString() + " and " + max.toString() + ".\n");
    }

}
