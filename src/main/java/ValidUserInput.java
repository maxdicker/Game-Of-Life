import java.util.Collections;
import java.util.List;

public class ValidUserInput {
    private UserInput input;
    private IO io;

    public ValidUserInput(IO io) {
        this.io = io;
        this.input = new UserInput(io);
    }

    public int getBoardWidth() {
        io.displayOutput("What is the width of the World (in cells)? Please provide a whole number.");
        return input.getInteger();
    }

    public int getBoardHeight() {
        io.displayOutput("What is the height of the World (in cells)? Please provide a whole number.");
        return input.getInteger();
    }

    public List<Coordinates> getPositionsOfLivingCells() {
        return Collections.emptyList();
    }

    public int getLengthOfSimulation() {
        io.displayOutput("How long would you like the simulation to run? Please enter the number of 'game ticks' as a whole number.");
        return input.getInteger();
    }
}
