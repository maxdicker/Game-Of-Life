import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UserInputTest {

    @Test
    public void Given_InputIsAnInteger_When_GettingIntegerFromUser_Then_ReturnsInteger() {
        String stubInput = "5";
        IO stubIO = new StubIO(stubInput);
        UserInput input = new UserInput(stubIO);

        int actual = input.getInteger();

        int expected = Integer.parseInt(stubInput);
        assertEquals(expected, actual);
    }

    @Test
    public void Given_InputIsNotInteger_When_GettingIntegerFromUser_Then_RetrievesMoreInputUntilIntegerFound() {
        String five = "5";
        List<String> stubInput = Arrays.asList("", "foo", five);
        IO stub = new StubIO(stubInput);
        UserInput input = new UserInput(stub);

        int actual = input.getInteger();

        int expected = Integer.parseInt(five);
        assertEquals(expected, actual);
    }

}
