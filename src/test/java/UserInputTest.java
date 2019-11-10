import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class UserInputTest {

    @Test
    public void Given_InputIsAnInteger_When_GettingIntegerFromUser_Then_ReturnsInteger() {
        String stubInput = "5";
        IO stubIO = new StubIO(stubInput);
        UserHandler input = new UserHandler(stubIO);

        int actual = input.getBoardWidthFromUser(0, 10);

        int expected = Integer.parseInt(stubInput);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InputIsNotInteger_When_GettingIntegerFromUser_Then_RetrievesMoreInputUntilIntegerFound() {
        String five = "5";
        List<String> stubInput = Arrays.asList("", "\n", "foo", five);
        IO stubIO = new StubIO(stubInput);
        UserHandler input = new UserHandler(stubIO);

        int actual = input.getBoardWidthFromUser(0, 10);

        int expected = Integer.parseInt(five);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InputInCorrectFormat_When_GettingCoordinatesFromUser_Then_ReturnsListOfMultipleCoordinates() {
        String stubInput = "1 2,2 2,2 3";
        IO stubIO = new StubIO(stubInput);
        UserHandler input = new UserHandler(stubIO);

        List<Coordinates> actual = input.getLivingCellPositionsFromUser(new Coordinates(0,0), new Coordinates(10,10));

        List<Coordinates> expected = Arrays.asList(new Coordinates(1, 2), new Coordinates(2, 2), new Coordinates(2, 3));
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InputInCorrectFormat_When_GettingCoordinatesFromUser_Then_ReturnsSingleCoordinates() {
        String stubInput = "1 2";
        IO stubIO = new StubIO(stubInput);
        UserHandler input = new UserHandler(stubIO);

        List<Coordinates> actual = input.getLivingCellPositionsFromUser(new Coordinates(0,0), new Coordinates(10,10));

        List<Coordinates> expected = Collections.singletonList(new Coordinates(1, 2));
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InputInIncorrectFormat_When_GettingCoordinatesFromUser_Then_RetrievesMoreInputUntilCorrectFormat() {
        String correctlyFormattedInput = "1 2,2 2,2 3";
        String excessiveWhitespace = "3 3 ,4 4";
        String excessiveCoordinatesDelimiter = "3 3,,4 4";
        String excessiveXYDelimiter = "3 3,4  4";
        String nonNumeric = "g g,f f";
        List<String> stubInput = Arrays.asList(excessiveWhitespace, excessiveCoordinatesDelimiter, excessiveXYDelimiter, nonNumeric, correctlyFormattedInput);
        IO stub = new StubIO(stubInput);
        UserHandler input = new UserHandler(stub);

        List<Coordinates> actual = input.getLivingCellPositionsFromUser(new Coordinates(0,0), new Coordinates(10,10));

        List<Coordinates> expected = Arrays.asList(new Coordinates(1, 2), new Coordinates(2, 2), new Coordinates(2, 3));
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InputOutsideRange_When_GettingBoardWidth_Then_ValidatorRetrievesMoreInputUntilCorrectInputFound() {
        String five = "5";
        List<String> stubInput = Arrays.asList("1", "1000", "foo", five);
        IO stubIO = new StubIO(stubInput);
        UserHandler input = new UserHandler(stubIO);

        int actual = input.getBoardWidthFromUser(2, 100);

        int expected = Integer.parseInt(five);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InputOutsideRange_When_GettingBoardHeight_Then_ValidatorRetrievesMoreInputUntilCorrectInputFound() {
        String five = "5";
        List<String> stubInput = Arrays.asList("1", "1000", "foo", five);
        IO stubIO = new StubIO(stubInput);
        UserHandler input = new UserHandler(stubIO);

        int actual = input.getBoardHeightFromUser(2, 100);

        int expected = Integer.parseInt(five);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InputOutsideRange_When_GettingSimulationLength_Then_ValidatorRetrievesMoreInputUntilCorrectInputFound() {
        String five = "5";
        List<String> stubInput = Arrays.asList("-1", "1000", "foo", five);
        IO stubIO = new StubIO(stubInput);
        UserHandler input = new UserHandler(stubIO);

        int actual = input.getSimulationLengthFromUser(0, 100);

        int expected = Integer.parseInt(five);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InputOutsideRange_When_GettingCoordinates_Then_ValidatorRetrievesMoreInputUntilCorrectInputFound() {
        String correctlyFormattedInput = "1 2,2 2,2 3";
        String YOutsideRange = "3 1000,4 4";
        String XOutsideRange = "3 3,-1 4";
        List<String> stubInput = Arrays.asList(YOutsideRange, XOutsideRange, correctlyFormattedInput);
        IO stubIO = new StubIO(stubInput);
        UserHandler input = new UserHandler(stubIO);

        List<Coordinates> actual = input.getLivingCellPositionsFromUser(new Coordinates(0,0), new Coordinates(10, 10));

        List<Coordinates> expected = Arrays.asList(new Coordinates(1, 2), new Coordinates(2, 2), new Coordinates(2, 3));
        assertEquals(expected, actual);
    }

    //What happens when excessive spaces
    //What happens when extra delimiter
    //what about duplicate coordinates
    //what if only one coordinate is bad

}
