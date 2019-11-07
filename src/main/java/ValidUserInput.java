import java.util.List;

public class ValidUserInput {
    private InputGatherer gatherer;
    private IO io;

    private final String BOARD_WIDTH_PROMPT = "What is the width of the World (in cells)? Please provide a whole number.\n";
    private final String BOARD_HEIGHT_PROMPT = "What is the height of the World (in cells)? Please provide a whole number.\n";
    private final String GAME_LENGTH_PROMPT = "How long would you like the simulation to run (i.e. number of 'game ticks')? Please provide a whole number.\n";
    private final String CELL_POSITIONS_PROMPT = "What are the positions of the initially living cells? Please enter their coordinates.\n" +
        "Format for each coordinate: Number of spaces right, followed by whitespace, followed by spaces down. Place a comma between each coordinate. The very top-left cell is '0 0'.\n";

    private final String NUMBER_OUTSIDE_RANGE_MESSAGE = "The number you specified is not within the required range. Please try again.\n";
    private final String COORDINATE_OUTSIDE_RANGE_MESSAGE = "One of the coordinates you specified was not within the required range. Please try again.\n";

    public ValidUserInput(IO io) {
        this.io = io;
        this.gatherer = new InputGatherer(io);
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
        int integer = gatherer.getIntegerFromUser();

        if (integer >= min && integer <= max) {
            return integer;
        } else {
            io.displayOutput(NUMBER_OUTSIDE_RANGE_MESSAGE);
        }

        return getIntegerWithinRange(min, max);
    }

    public List<Coordinates> getPositionsOfLivingCells(Coordinates minBoundary, Coordinates maxBoundary) {
        io.displayOutput(CELL_POSITIONS_PROMPT);
        displayCoordinateBoundariesToUser(minBoundary, maxBoundary);
        return getCoordinatesWithinRange(minBoundary, maxBoundary);
    }

    private List<Coordinates> getCoordinatesWithinRange(Coordinates min, Coordinates max) {
        List<Coordinates> positions = gatherer.getCoordinatesFromUser();

        boolean allWithinRange = true;
        for (Coordinates position : positions) {
            if (position.compareTo(min) < 0 || position.compareTo(max) > 0) {
                allWithinRange = false;
                break;
            }
        }

        if (allWithinRange) {
            return positions;
        } else {
            io.displayOutput(COORDINATE_OUTSIDE_RANGE_MESSAGE);
        }

        return getCoordinatesWithinRange(min, max);
    }

    private void displayCoordinateBoundariesToUser(Coordinates min, Coordinates max) {
        io.displayOutput("The Coordinates must be between " + toString(min) + " and " + toString(max) + ".\n");
    }

    private String toString(Coordinates position) {
        return "(" + position.x + " " + position.y + ")";
    }

}
