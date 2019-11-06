import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class UserInput {
    private IO io;
    private String standardFormat = "[0-9]+\\s[0-9]+";

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
        String input = io.readUserInput();

        if (input.matches(standardFormat + "|" + standardFormat + "(," + standardFormat + ")+")) {
            String[] inputArrr = input.split(",");
            for (String s : inputArrr) {
                String[] inputArr = s.split("\\s");

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
