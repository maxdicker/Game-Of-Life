import core.Coordinates;
import org.junit.Test;
import io.CoordinatesParser;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CoordinatesParserTest {

    @Test
    public void Given_StringConformsToPattern_Then_ParserReturnsCorrespondingCoordinates() {
        String coordinatesDelimiter = "\\;";
        String XYDelimiter = "\\.";
        String correctlyFormattedString = "1.0;0.10;40.20";
        CoordinatesParser parser = new CoordinatesParser(coordinatesDelimiter, XYDelimiter);

        List<Coordinates> actual = parser.parseCoordinates(correctlyFormattedString);

        List<Coordinates> expected = Arrays.asList(new Coordinates(1,0), new Coordinates(0,10), new Coordinates(40,20));
        assertTrue(TestHelper.validatePositionsAreEqual(expected, actual));
    }

    @Test (expected = InputMismatchException.class)
    public void Given_StringDoesNotConformToPattern_Then_ParserThrowsInputMismatchException() {
        String coordinatesDelimiter = "\\;";
        String XYDelimiter = "\\.";
        String incorrectlyFormattedString = "1.0;0.10;40 20";
        CoordinatesParser parser = new CoordinatesParser(coordinatesDelimiter, XYDelimiter);

        parser.parseCoordinates(incorrectlyFormattedString);
    }
}
