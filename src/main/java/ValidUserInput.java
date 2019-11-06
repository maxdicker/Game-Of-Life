import java.util.Collections;
import java.util.List;

public class ValidUserInput {
    private InputGatherer gatherer;
    private IO io;

    private final String BOARD_WIDTH_PROMPT = "What is the width of the World (in cells)? Please provide a whole number.\n";
    private final String BOARD_HEIGHT_PROMPT = "What is the height of the World (in cells)? Please provide a whole number.\n";
    private final String INVALID_BOARD_DIMENSION_MESSAGE = "The size you specified is not within the required range. Please try again.\n";

    public ValidUserInput(IO io) {
        this.io = io;
        this.gatherer = new InputGatherer(io);
    }

    public int getBoardWidth(int minWidth, int maxWidth) {
        io.displayOutput(BOARD_WIDTH_PROMPT);
        displayBoundariesToUser(minWidth, maxWidth);
        return getValidBoardDimension(minWidth, maxWidth);
    }

    private void displayBoundariesToUser(int min, int max) {
        io.displayOutput("The minimum and maximum for this field are " + min + " and " + max + ", respectively.\n");
    }

    private int getValidBoardDimension(int min, int max) {
        int size = gatherer.getIntegerFromUser();

        if (size >= min && size <= max) {
            return size;
        } else {
            io.displayOutput(INVALID_BOARD_DIMENSION_MESSAGE);
        }

        return getValidBoardDimension(min, max);
    }

    public int getBoardHeight(int minHeight, int maxHeight) {
        io.displayOutput(BOARD_HEIGHT_PROMPT);
        displayBoundariesToUser(minHeight, maxHeight);
        return getValidBoardDimension(minHeight, maxHeight);
    }

    public List<Coordinates> getPositionsOfLivingCells() {
        io.displayOutput("What are the positions of the (initially) living cells?");
        io.displayOutput("Please enter their coordinates - number of cells right and down from the very top-left cell.");
        io.displayOutput("Format: Amount right, followed by whitespace, then amount down.");
        io.displayOutput("To stop providing locations, enter 'Q'.");
        return Collections.emptyList();
    }

    public int getLengthOfSimulation() {
        io.displayOutput("How long would you like the simulation to run? Please enter the number of 'game ticks' as a whole number.");
        return gatherer.getIntegerFromUser();
    }
}
