import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class UserInput {
    private IO io;

    public UserInput(IO io) {
        this.io = io;
    }

    public int getInteger() {
        String input = io.readUserInput();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            io.displayOutput("Please provide an integer.");
        }

        return getInteger();
    }

    public List<Coordinates> getPositionsOfLivingCells() {
        List<Coordinates> positions = new ArrayList<>();
        io.displayOutput("What are the positions of the (initially) living cells?");
        io.displayOutput("Please enter their coordinates - number of cells right and down from the very top-left cell.");
        io.displayOutput("Format: Amount right, followed by whitespace, then amount down.");
        io.displayOutput("To stop providing locations, enter 'Q'.");
        String input = "";

        while (!input.equals("Q")) {
            input = io.readUserInput();

            if (input.matches("[0-9]+\\s[0-9]+")) {
                String[] inputArr = input.split("\\s");

                try {
                    int x = Integer.parseInt(inputArr[0]);
                    int y = Integer.parseInt(inputArr[1]);
                    positions.add(new Coordinates(x, y));
                } catch (InputMismatchException e) {
                    io.displayOutput("Please provide integers in the required format only.");
                    io.readUserInput();
                }
            }
        }
        return positions;
    }

}
