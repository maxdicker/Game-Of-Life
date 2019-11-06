import java.util.Collections;
import java.util.List;

public class ValidUserInput {
    private InputGatherer gatherer;
    private IO io;

    private final String BOARD_WIDTH_PROMPT = "What is the width of the World (in cells)? Please provide a whole number.\n";
    private final String BOARD_HEIGHT_PROMPT = "What is the height of the World (in cells)? Please provide a whole number.\n";
    private final String GAME_LENGTH_PROMPT = "How long would you like the simulation to run (i.e. number of 'game ticks')? Please provide a whole number.\n";
    private final String NUMBER_OUTSIDE_RANGE_MESSAGE = "The number you specified is not within the required range. Please try again.\n";

    public ValidUserInput(IO io) {
        this.io = io;
        this.gatherer = new InputGatherer(io);
    }

    public int getBoardWidth(int minWidth, int maxWidth) {
        io.displayOutput(BOARD_WIDTH_PROMPT);
        displayFieldBoundariesToUser(minWidth, maxWidth);
        return getIntegerWithinRange(minWidth, maxWidth);
    }

    public int getBoardHeight(int minHeight, int maxHeight) {
        io.displayOutput(BOARD_HEIGHT_PROMPT);
        displayFieldBoundariesToUser(minHeight, maxHeight);
        return getIntegerWithinRange(minHeight, maxHeight);
    }

    public int getLengthOfSimulation(int minLength, int maxLength) {
        io.displayOutput(GAME_LENGTH_PROMPT);
        displayFieldBoundariesToUser(minLength, maxLength);
        return getIntegerWithinRange(minLength, maxLength);
    }

    private void displayFieldBoundariesToUser(int min, int max) {
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



    public List<Coordinates> getPositionsOfLivingCells() {
        io.displayOutput("What are the positions of the (initially) living cells?");
        io.displayOutput("Please enter their coordinates - number of cells right and down from the very top-left cell.");
        io.displayOutput("Format: Amount right, followed by whitespace, then amount down.");
        io.displayOutput("To stop providing locations, enter 'Q'.");
        return Collections.emptyList();
    }


}
