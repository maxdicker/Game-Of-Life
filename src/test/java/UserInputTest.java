import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UserInputTest {

    @Test
    public void Given_InputIsInteger_When_GettingBoardHeight_Then_ReturnsHeightAsInteger() {
        String stubInput = "5";
        IO stubIO = new StubIO(stubInput);
        UserInput input = new UserInput(stubIO);

        int actual = input.getBoardHeight();

        int expected = Integer.parseInt(stubInput);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InputIsNotInteger_When_GettingBoardHeight_Then_RetrievesMoreInputUntilIntegerFound() {
        String height = "5";
        List<String> stubInput = Arrays.asList("", "foo", height);
        IO stub = new StubIO(stubInput);
        UserInput input = new UserInput(stub);

        int actual = input.getBoardHeight();

        int expected = Integer.parseInt(height);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InputIsInteger_When_GettingBoardWidth_Then_ReturnsWidthAsInteger() {
        String stubInput = "5";
        IO stubIO = new StubIO(stubInput);
        UserInput input = new UserInput(stubIO);

        int actual = input.getBoardWidth();

        int expected = Integer.parseInt(stubInput);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InputIsNotInteger_When_GettingBoardWidth_Then_RetrievesMoreInputUntilIntegerFound() {
        String width = "5";
        List<String> stubInput = Arrays.asList("", "foo", width);
        IO stub = new StubIO(stubInput);
        UserInput input = new UserInput(stub);

        int actual = input.getBoardWidth();

        int expected = Integer.parseInt(width);
        assertEquals(expected, actual);
    }

}
