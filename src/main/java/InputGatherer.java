import java.util.ArrayList;
import java.util.List;

public class InputGatherer {
    private IO io;

    private final String XY_DELIMITER = "\\s";
    private final String COORDINATES_DELIMITER = ",";
    private final String SINGLE_COORDINATES_PATTERN = "\\d+" + XY_DELIMITER + "\\d+";
    private final String COORDINATES_INPUT_PATTERN = SINGLE_COORDINATES_PATTERN + "|" + SINGLE_COORDINATES_PATTERN + "(" + COORDINATES_DELIMITER + SINGLE_COORDINATES_PATTERN + ")+";

    private final String INVALID_INTEGER_MESSAGE = "An integer is required. Please try again.\n";
    private final String INVALID_COORDINATES_FORMAT_MESSAGE = "Coordinates must be entered in the specified format. Please try again.\n";

    public InputGatherer(IO io) {
        this.io = io;
    }

    public int getIntegerFromUser() {
        String input = io.readUserInput();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            io.displayOutput(INVALID_INTEGER_MESSAGE);
        }

        return getIntegerFromUser();
    }

    public List<Coordinates> getCoordinatesFromUser() {
        String input = io.readUserInput();

        if (input.matches(COORDINATES_INPUT_PATTERN)) {
            return extractCoordinatesListFromString(input);
        } else {
            io.displayOutput(INVALID_COORDINATES_FORMAT_MESSAGE);
        }

        return getCoordinatesFromUser();
    }

    private List<Coordinates> extractCoordinatesListFromString(String string) {
        List<Coordinates> result = new ArrayList<>();
        String[] coordinatesArray = string.split(COORDINATES_DELIMITER);

        for (String coordinates : coordinatesArray) {
            result.add(createCoordinatesFromString(coordinates));
        }

        return result;
    }

    private Coordinates createCoordinatesFromString(String string) {
        String[] coordinateArray = string.split(XY_DELIMITER);
        return new Coordinates(Integer.parseInt(coordinateArray[0]), Integer.parseInt(coordinateArray[1]));
    }

}
