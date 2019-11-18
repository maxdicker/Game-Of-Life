import core.Coordinates;
import io.IO;
import io.UserInputReader;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class UserInputReaderTest {

    @Test
    public void Given_InitialInputIsNotAnInteger_When_GettingBoardWidth_Then_ReaderRetrievesMoreInputAndReturnsFirstInteger() {
        String anInteger = "5";
        List<String> stubInput = Arrays.asList("", "\n", "foo", anInteger);
        IO stubIO = new StubIO(stubInput);
        UserInputReader reader = new UserInputReader(stubIO);

        int actual = reader.getBoardWidthFromUser(0, 10);

        int expected = Integer.parseInt(anInteger);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InitialInputIsOutsideRange_When_GettingBoardWidth_Then_ReaderRetrievesMoreInputAndReturnsFirstIntegerInRange() {
        int fieldMin = 0;
        int fieldMax = 10;
        String intWithinRange = "5";
        List<String> stubInput = Arrays.asList("-1", "11", intWithinRange);
        IO stubIO = new StubIO(stubInput);
        UserInputReader reader = new UserInputReader(stubIO);

        int actual = reader.getBoardWidthFromUser(fieldMin, fieldMax);

        int expected = Integer.parseInt(intWithinRange);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InitialInputIsNotAnInteger_When_GettingBoardHeight_Then_ReaderRetrievesMoreInputAndReturnsFirstInteger() {
        String anInteger = "5";
        List<String> stubInput = Arrays.asList("", "\n", "foo", anInteger);
        IO stubIO = new StubIO(stubInput);
        UserInputReader reader = new UserInputReader(stubIO);

        int actual = reader.getBoardHeightFromUser(0, 10);

        int expected = Integer.parseInt(anInteger);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InitialInputIsOutsideRange_When_GettingBoardHeight_Then_ReaderRetrievesMoreInputAndReturnsFirstIntegerInRange() {
        int fieldMin = 0;
        int fieldMax = 10;
        String intWithinRange = "5";
        List<String> stubInput = Arrays.asList("-1", "11", intWithinRange);
        IO stubIO = new StubIO(stubInput);
        UserInputReader reader = new UserInputReader(stubIO);

        int actual = reader.getBoardHeightFromUser(fieldMin, fieldMax);

        int expected = Integer.parseInt(intWithinRange);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InitialInputIsNotAnInteger_When_GettingNumberOfEvolutions_Then_ReaderRetrievesMoreInputAndReturnsFirstInteger() {
        String anInteger = "5";
        List<String> stubInput = Arrays.asList("", "\n", "foo", anInteger);
        IO stubIO = new StubIO(stubInput);
        UserInputReader reader = new UserInputReader(stubIO);

        int actual = reader.getNumberOfBoardEvolutionsFromUser(0,10);

        int expected = Integer.parseInt(anInteger);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InitialInputIsOutsideRange_When_GettingNumberOfEvolutions_Then_ReaderRetrievesMoreInputAndReturnsFirstIntegerInRange() {
        int fieldMin = 0;
        int fieldMax = 10;
        String intWithinRange = "5";
        List<String> stubInput = Arrays.asList("-1", "11", intWithinRange);
        IO stubIO = new StubIO(stubInput);
        UserInputReader reader = new UserInputReader(stubIO);

        int actual = reader.getNumberOfBoardEvolutionsFromUser(fieldMin, fieldMax);

        int expected = Integer.parseInt(intWithinRange);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InputIsFormattedCorrectly_When_GettingCoordinates_Then_ReaderReturnsSingleCoordinatesSet() {
        String positionInCorrectFormat = "1,2";
        IO stubIO = new StubIO(positionInCorrectFormat);
        UserInputReader reader = new UserInputReader(stubIO);

        List<Coordinates> actual = reader.getLivingCellPositionsFromUser(new Coordinates(0,0), new Coordinates(10,10));

        List<Coordinates> expected = Collections.singletonList(new Coordinates(1, 2));
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InputIsFormattedCorrectly_When_GettingCoordinates_Then_ReaderReturnsListOfMultipleCoordinates() {
        String positionsInCorrectFormat = "1,2|2,2|2,3";
        IO stubIO = new StubIO(positionsInCorrectFormat);
        UserInputReader reader = new UserInputReader(stubIO);

        List<Coordinates> actual = reader.getLivingCellPositionsFromUser(new Coordinates(0,0), new Coordinates(10,10));

        List<Coordinates> expected = Arrays.asList(new Coordinates(1, 2), new Coordinates(2, 2), new Coordinates(2, 3));
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InitialInputIsNotFormattedCorrectly_When_GettingCoordinates_Then_ReaderRetrievesMoreInputAndReturnsFirstCorrectlyFormattedCoordinates() {
        String excessiveWhitespace = "3,3 |4,4";
        String excessiveCoordinatesDelimiter = "3,3||4,4";
        String excessiveXYDelimiter = "3,3|4,,4";
        String nonNumeric = "g,g|f,f";
        String positionsInCorrectFormat = "1,2|2,2|2,3";
        List<String> stubInput = Arrays.asList(excessiveWhitespace, excessiveCoordinatesDelimiter, excessiveXYDelimiter, nonNumeric, positionsInCorrectFormat);
        IO stubIO = new StubIO(stubInput);
        UserInputReader reader = new UserInputReader(stubIO);

        List<Coordinates> actual = reader.getLivingCellPositionsFromUser(new Coordinates(0,0), new Coordinates(10,10));

        List<Coordinates> expected = Arrays.asList(new Coordinates(1, 2), new Coordinates(2, 2), new Coordinates(2, 3));
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InitialInputIsOutsideRange_When_GettingCoordinates_Then_ReaderRetrievesMoreInputAndReturnsFirstCoordinatesInRange() {
        Coordinates fieldMin = new Coordinates(0,0);
        Coordinates fieldMax = new Coordinates(10,10);
        String YOutsideRange = "3,11|4,4";
        String XOutsideRange = "3,3|-1,4";
        String positionsInCorrectFormat = "1,2|2,2|2,3";
        List<String> stubInput = Arrays.asList(YOutsideRange, XOutsideRange, positionsInCorrectFormat);
        IO stubIO = new StubIO(stubInput);
        UserInputReader reader = new UserInputReader(stubIO);

        List<Coordinates> actual = reader.getLivingCellPositionsFromUser(fieldMin, fieldMax);

        List<Coordinates> expected = Arrays.asList(new Coordinates(1, 2), new Coordinates(2, 2), new Coordinates(2, 3));
        assertEquals(expected, actual);
    }

}
