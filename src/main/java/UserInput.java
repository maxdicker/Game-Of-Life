import java.util.ArrayList;
import java.util.List;

public class UserInput {
    private IO io;

    private final String XY_DELIMITER = "\\s";
    private final String COORDINATE_DELIMITER = ",";
    private final String SINGLE_COORDINATE_PATTERN = "\\d+" + XY_DELIMITER + "\\d+";
    private final String COORDINATES_INPUT_PATTERN = SINGLE_COORDINATE_PATTERN + "|" + SINGLE_COORDINATE_PATTERN + "(" + COORDINATE_DELIMITER + SINGLE_COORDINATE_PATTERN + ")+";

    public UserInput(IO io) {
        this.io = io;
    }

    public int getIntegerFromUser() {
        String input = io.readUserInput();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            io.displayOutput("An integer is required. Please try again.");
        }

        return getIntegerFromUser();
    }

    public List<Coordinates> getCoordinatesFromUser() {
        List<Coordinates> result = new ArrayList<>();
        String input = io.readUserInput();

        if (input.matches(COORDINATES_INPUT_PATTERN)) {
            String[] coordinatesArray = input.split(COORDINATE_DELIMITER);

            for (String coordinates : coordinatesArray) {
                String[] coordinateArray = coordinates.split(XY_DELIMITER);
                int x = Integer.parseInt(coordinateArray[0]);
                int y = Integer.parseInt(coordinateArray[1]);

                result.add(new Coordinates(x, y));
            }

            return result;
        } else {
            io.displayOutput("Coordinates must be entered in the specified format. Please try again.");
        }

        return getCoordinatesFromUser();
    }

}
