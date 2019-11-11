import core.Coordinates;
import org.junit.Test;
import utils.CoordinatesParser;
import utils.DataValidator;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import static org.junit.Assert.*;

public class UtilsTest {
    private int numMin = 1;
    private int numMax = 3;
    private int numWithinRange = 1;
    private int numOutsideRange = 4;
    private Coordinates positionMin = new Coordinates(0,0);
    private Coordinates positionMax = new Coordinates(3,3);
    private List<Coordinates> positionsWithinRange = Arrays.asList(new Coordinates(3,3), new Coordinates(0,3));
    private List<Coordinates> positionsOutsideRange = Arrays.asList(new Coordinates(3,3), new Coordinates(4,3));

    @Test
    public void Given_NumberWithinRange_When_Validating_Then_ValidatorReturnsTrue() {
        assertTrue(DataValidator.numberInRange(numWithinRange, numMin, numMax));
    }

    @Test
    public void Given_NumberOutsideRange_When_Validating_Then_ValidatorReturnsFalse() {
        assertFalse(DataValidator.numberInRange(numOutsideRange, numMin, numMax));
    }

    @Test
    public void Given_APositionWithinRange_When_Validating_Then_ValidatorReturnsTrue() {
        assertTrue(DataValidator.positionsInRange(positionsWithinRange, positionMin, positionMax));
    }

    @Test
    public void Given_APositionOutsideRange_When_Validating_Then_ValidatorReturnsFalse() {
        assertFalse(DataValidator.positionsInRange(positionsOutsideRange, positionMin, positionMax));
    }

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
