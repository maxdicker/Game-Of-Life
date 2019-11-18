import core.Coordinates;
import io.UserInputReader;
import org.junit.Before;
import org.junit.Test;
import io.CoordinatesParser;

import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CoordinatesParserTest {
    private CoordinatesParser parser;
    private static final String coordinatesDelimiter = UserInputReader.DEFAULT_COORDINATES_DELIMITER;
    private static final String XYDelimiter = UserInputReader.DEFAULT_XY_DELIMITER;

    @Before
    public void init() {
        parser = new CoordinatesParser(coordinatesDelimiter, XYDelimiter);
    }

    @Test
    public void Given_StringConformsToPattern_Then_ParserReturnsCorrespondingSingleCoordinatesSet() {
        String input = "1,2";

        List<Coordinates> actual = parser.parseCoordinates(input);

        List<Coordinates> expected = Collections.singletonList(new Coordinates(1, 2));
        assertEquals(expected, actual);
    }

    @Test
    public void Given_StringConformsToPattern_Then_ParserReturnsCorrespondingListOfCoordinates() {
        String input = "1,0|0,10|40,20";

        List<Coordinates> actual = parser.parseCoordinates(input);

        List<Coordinates> expected = Arrays.asList(new Coordinates(1,0), new Coordinates(0,10), new Coordinates(40,20));
        assertEquals(expected, actual);
    }

    @Test (expected = InputMismatchException.class)
    public void Given_StringHasExcessWhitespace_Then_ParserThrowsInputMismatchException() {
        String input = "1,0|0,10|40,20 ";

        parser.parseCoordinates(input);
    }

    @Test (expected = InputMismatchException.class)
    public void Given_StringHasExcessCoordinatesDelimiters_Then_ParserThrowsInputMismatchException() {
        String input = "1,0|0,10||40,20";

        parser.parseCoordinates(input);
    }

    @Test (expected = InputMismatchException.class)
    public void Given_StringHasExcessXYDelimiters_Then_ParserThrowsInputMismatchException() {
        String input = "1,0|0,,10|40,20";

        parser.parseCoordinates(input);
    }

    @Test (expected = InputMismatchException.class)
    public void Given_StringIsNonNumeric_Then_ParserThrowsInputMismatchException() {
        String input = "1,0|0,g|40,20";

        parser.parseCoordinates(input);
    }

}
