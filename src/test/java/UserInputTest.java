import org.junit.Test;

import static org.junit.Assert.*;

public class UserInputTest {

    @Test
    public void getBoardHeight_ReturnsHeightAsInteger() {
        Input stub = new StubInput("5");
        UserInput input = new UserInput(stub);

        assertEquals(5, input.getBoardHeight());
    }

}
