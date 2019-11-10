import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class CoordinatesParser {
    private String coordinatesDelimiter;
    private String XYDelimiter;
    private String coordinatesListPattern;

    public CoordinatesParser(String coordinatesSetsSeparator, String XAndYSeparator) {
        this.coordinatesDelimiter = coordinatesSetsSeparator;
        this.XYDelimiter = XAndYSeparator;
        String singleCoordinatesSetPattern = "\\d+" + XYDelimiter + "\\d+";
        this.coordinatesListPattern = singleCoordinatesSetPattern + "|" + singleCoordinatesSetPattern + "(" + coordinatesDelimiter + singleCoordinatesSetPattern + ")+";
    }

    public List<Coordinates> parseCoordinates(String string) {
        if (string.matches(coordinatesListPattern)) {
            return extractCoordinatesListFromString(string);
        }

        throw new InputMismatchException();
    }

    private List<Coordinates> extractCoordinatesListFromString(String string) {
        List<Coordinates> result = new ArrayList<>();
        String[] coordinatesArray = string.split(coordinatesDelimiter);

        for (String coordinates : coordinatesArray) {
            result.add(createCoordinatesFromString(coordinates));
        }

        return result;
    }

    private Coordinates createCoordinatesFromString(String string) {
        String[] coordinateArray = string.split(XYDelimiter);
        return new Coordinates(Integer.parseInt(coordinateArray[0]), Integer.parseInt(coordinateArray[1]));
    }

}
