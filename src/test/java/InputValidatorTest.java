import core.Coordinates;
import org.junit.Test;
import io.InputValidator;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class InputValidatorTest {
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
        assertTrue(InputValidator.numberInRange(numWithinRange, numMin, numMax));
    }

    @Test
    public void Given_NumberOutsideRange_When_Validating_Then_ValidatorReturnsFalse() {
        assertFalse(InputValidator.numberInRange(numOutsideRange, numMin, numMax));
    }

    @Test
    public void Given_APositionWithinRange_When_Validating_Then_ValidatorReturnsTrue() {
        assertTrue(InputValidator.positionsInRange(positionsWithinRange, positionMin, positionMax));
    }

    @Test
    public void Given_APositionOutsideRange_When_Validating_Then_ValidatorReturnsFalse() {
        assertFalse(InputValidator.positionsInRange(positionsOutsideRange, positionMin, positionMax));
    }
}
