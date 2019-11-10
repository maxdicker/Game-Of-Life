import java.util.InputMismatchException;
import java.util.List;

public class ValidUserInput {
    private IO io;
    private CoordinatesParser parser;

    private final String BOARD_WIDTH_PROMPT = "What is the width of the World (in cells)? Please provide a whole number.\n";
    private final String BOARD_HEIGHT_PROMPT = "What is the height of the World (in cells)? Please provide a whole number.\n";
    private final String GAME_LENGTH_PROMPT = "How long would you like the simulation to run (i.e. number of 'game ticks')? Please provide a whole number.\n";
    private final String CELL_POSITIONS_PROMPT = "What are the positions of the initially living cells? Please enter their coordinates.\n" +
        "Format for each coordinate: Number of spaces right, followed by whitespace, followed by spaces down. Place a comma between each coordinate. The very top-left cell is '0 0'.\n";

    private final String INVALID_INTEGER_MESSAGE = "An integer is required. Please try again.\n";
    private final String INVALID_COORDINATES_FORMAT_MESSAGE = "Coordinates must be entered in the specified format. Please try again.\n";

    private final String NUMBER_OUTSIDE_RANGE_MESSAGE = "The number you specified is not within the required range. Please try again.\n";
    private final String COORDINATE_OUTSIDE_RANGE_MESSAGE = "One of the coordinates you specified was not within the required range. Please try again.\n";

    private final String XY_DELIMITER = "\\s";
    private final String COORDINATES_DELIMITER = ",";

    public ValidUserInput(IO io) {
        this.io = io;
        this.parser = new CoordinatesParser(COORDINATES_DELIMITER, XY_DELIMITER);
    }

    public int getBoardWidth(int minWidth, int maxWidth) {
        io.displayOutput(BOARD_WIDTH_PROMPT);
        displayIntFieldBoundariesToUser(minWidth, maxWidth);
        return getIntegerWithinRange(minWidth, maxWidth);
    }

    public int getBoardHeight(int minHeight, int maxHeight) {
        io.displayOutput(BOARD_HEIGHT_PROMPT);
        displayIntFieldBoundariesToUser(minHeight, maxHeight);
        return getIntegerWithinRange(minHeight, maxHeight);
    }

    public int getLengthOfSimulation(int minLength, int maxLength) {
        io.displayOutput(GAME_LENGTH_PROMPT);
        displayIntFieldBoundariesToUser(minLength, maxLength);
        return getIntegerWithinRange(minLength, maxLength);
    }

    private void displayIntFieldBoundariesToUser(int min, int max) {
        io.displayOutput("The minimum and maximum for this field are " + min + " and " + max + ", respectively.\n");
    }

    private int getIntegerWithinRange(int min, int max) {
        String input = io.readUserInput();
        int integer;

        try {
            integer = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            io.displayOutput(INVALID_INTEGER_MESSAGE);
            return getIntegerWithinRange(min, max);
        }

        if (!Validator.numberInRange(integer, min, max)) {
            io.displayOutput(NUMBER_OUTSIDE_RANGE_MESSAGE);
            return getIntegerWithinRange(min, max);
        }

        return integer;
    }

    public List<Coordinates> getPositionsOfLivingCells(Coordinates minBoundary, Coordinates maxBoundary) {
        io.displayOutput(CELL_POSITIONS_PROMPT);
        displayCoordinateBoundariesToUser(minBoundary, maxBoundary);
        return getCoordinatesWithinRange(minBoundary, maxBoundary);
    }

    private List<Coordinates> getCoordinatesWithinRange(Coordinates min, Coordinates max) {
        String input = io.readUserInput();
        List<Coordinates> positions;

        try {
            positions = parser.parseCoordinates(input);
        } catch (InputMismatchException e) {
            io.displayOutput(INVALID_COORDINATES_FORMAT_MESSAGE);
            return getCoordinatesWithinRange(min, max);
        }

        if (!positionsInRange(positions, min, max)) {
            io.displayOutput(COORDINATE_OUTSIDE_RANGE_MESSAGE);
            return getCoordinatesWithinRange(min, max);
        }

        return positions;
    }

    private boolean positionsInRange(List<Coordinates> positions, Coordinates min, Coordinates max) {
        for (Coordinates position : positions) {
            if (!Validator.positionInRange(position, min, max)) {
                return false;
            }
        }
        return true;
    }

    private void displayCoordinateBoundariesToUser(Coordinates min, Coordinates max) {
        io.displayOutput("The Coordinates must be between " + min.toString() + " and " + max.toString() + ".\n");
    }

}
