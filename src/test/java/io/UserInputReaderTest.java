package io;

import core.Coordinates;
import org.junit.Test;
import utils.StubIO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserInputReaderTest {
    private static final String anInteger = "5";
    private static final String intWithinRange = "5";
    private static final int fieldMin = 0;
    private static final int fieldMax = 10;

    private static final String positionInCorrectFormat = "1,2";
    private static final String positionsInCorrectFormat = "1,2|2,2|2,3";
    private static final String excessiveWhitespace = "3,3 |4,4";
    private static final String excessiveCoordinatesDelimiter = "3,3||4,4";
    private static final String excessiveXYDelimiter = "3,3|4,,4";
    private static final String nonNumeric = "3,g|4,4";
    private static final String YOutsideRange = "3,11|4,4";
    private static final String XOutsideRange = "3,3|-1,4";
    private static final Coordinates positionMin = new Coordinates(0,0);
    private static final Coordinates positionMax = new Coordinates(10,10);
    private static final List<Coordinates> expectedSinglePosition = Collections.singletonList(
            new Coordinates(1, 2));
    private static final List<Coordinates> expectedPositions = Arrays.asList(
            new Coordinates(1, 2), new Coordinates(2, 2), new Coordinates(2, 3));

    @Test
    public void Given_InitialInputIsNotInteger_When_GettingBoardWidth_Then_GetsMoreInputAndReturnsFirstInteger() {
        List<String> stubInput = Arrays.asList("", "\n", "foo", anInteger);
        IO stubIO = new StubIO(stubInput);
        UserInputReader reader = new UserInputReader(stubIO);

        int actual = reader.getBoardWidthFromUser(fieldMin, fieldMax);

        int expected = Integer.parseInt(anInteger);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InitialInputIsOutsideRange_When_GettingBoardWidth_Then_GetsMoreInputAndReturnsFirstWithinRange() {
        List<String> stubInput = Arrays.asList("-1", "11", intWithinRange);
        IO stubIO = new StubIO(stubInput);
        UserInputReader reader = new UserInputReader(stubIO);

        int actual = reader.getBoardWidthFromUser(fieldMin, fieldMax);

        int expected = Integer.parseInt(intWithinRange);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InitialInputIsNotInteger_When_GettingBoardHeight_Then_GetsMoreInputAndReturnsFirstInteger() {
        List<String> stubInput = Arrays.asList("", "\n", "foo", anInteger);
        IO stubIO = new StubIO(stubInput);
        UserInputReader reader = new UserInputReader(stubIO);

        int actual = reader.getBoardHeightFromUser(fieldMin, fieldMax);

        int expected = Integer.parseInt(anInteger);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InitialInputIsOutsideRange_When_GettingBoardHeight_Then_GetsMoreInputAndReturnsFirstWithinRange() {
        List<String> stubInput = Arrays.asList("-1", "11", intWithinRange);
        IO stubIO = new StubIO(stubInput);
        UserInputReader reader = new UserInputReader(stubIO);

        int actual = reader.getBoardHeightFromUser(fieldMin, fieldMax);

        int expected = Integer.parseInt(intWithinRange);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InitialInputIsNotInteger_When_GettingNumberOfEvolutions_Then_GetsMoreInputAndReturnsFirstInteger() {
        List<String> stubInput = Arrays.asList("", "\n", "foo", anInteger);
        IO stubIO = new StubIO(stubInput);
        UserInputReader reader = new UserInputReader(stubIO);

        int actual = reader.getNumberOfBoardEvolutionsFromUser(fieldMin, fieldMax);

        int expected = Integer.parseInt(anInteger);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InitialInputIsOutsideRange_When_GettingNumberOfEvolutions_Then_GetsMoreInputAndReturnsFirstWithinRange() {
        List<String> stubInput = Arrays.asList("-1", "11", intWithinRange);
        IO stubIO = new StubIO(stubInput);
        UserInputReader reader = new UserInputReader(stubIO);

        int actual = reader.getNumberOfBoardEvolutionsFromUser(fieldMin, fieldMax);

        int expected = Integer.parseInt(intWithinRange);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InputIsFormattedCorrectly_When_GettingCoordinates_Then_ReturnsSingleSetOfCoordinates() {
        IO stubIO = new StubIO(positionInCorrectFormat);
        UserInputReader reader = new UserInputReader(stubIO);

        List<Coordinates> actual = reader.getLivingCellPositionsFromUser(positionMin, positionMax);

        assertEquals(expectedSinglePosition, actual);
    }

    @Test
    public void Given_InputIsFormattedCorrectly_When_GettingCoordinates_Then_ReturnsMultipleCoordinates() {
        IO stubIO = new StubIO(positionsInCorrectFormat);
        UserInputReader reader = new UserInputReader(stubIO);

        List<Coordinates> actual = reader.getLivingCellPositionsFromUser(positionMin, positionMax);

        assertEquals(expectedPositions, actual);
    }

    @Test
    public void Given_InitialInputIsNotFormattedCorrectly_When_GettingCoordinates_Then_GetsMoreInputAndReturnsFirstCorrectlyFormattedCoordinates() {
        List<String> stubInput = Arrays.asList(excessiveWhitespace, excessiveCoordinatesDelimiter, excessiveXYDelimiter, nonNumeric, positionsInCorrectFormat);
        IO stubIO = new StubIO(stubInput);
        UserInputReader reader = new UserInputReader(stubIO);

        List<Coordinates> actual = reader.getLivingCellPositionsFromUser(positionMin, positionMax);

        assertEquals(expectedPositions, actual);
    }

    @Test
    public void Given_InitialInputIsOutsideRange_When_GettingCoordinates_Then_GetsMoreInputAndReturnsFirstWithinRange() {
        List<String> stubInput = Arrays.asList(YOutsideRange, XOutsideRange, positionsInCorrectFormat);
        IO stubIO = new StubIO(stubInput);
        UserInputReader reader = new UserInputReader(stubIO);

        List<Coordinates> actual = reader.getLivingCellPositionsFromUser(positionMin, positionMax);

        assertEquals(expectedPositions, actual);
    }

}
