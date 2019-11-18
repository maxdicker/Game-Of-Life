import core.Coordinates;
import org.junit.Test;
import io.InputValidator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class InputValidatorTest {
    private static final int numMin = 1;
    private static final int numMax = 3;
    private static final int numWithinRange = 2;

    private static final Coordinates positionMin = new Coordinates(0,0);
    private static final Coordinates positionMax = new Coordinates(3,3);
    private static final List<Coordinates> positionsWithinRange = Arrays.asList(new Coordinates(1,3), new Coordinates(3,2));
    private static final List<Coordinates> positionsGreaterThanMax = Arrays.asList(new Coordinates(10,10), new Coordinates(4,3));
    private static final List<Coordinates> positionsLessThanMin = Arrays.asList(new Coordinates(-1,0), new Coordinates(0,-1));

    @Test
    public void Given_NumberWithinRange_Then_ValidatorReturnsTrue() {
        assertTrue(InputValidator.numberInRange(numWithinRange, numMin, numMax));
    }

    @Test
    public void Given_NumberEqualToRangeMax_Then_ValidatorReturnsTrue() {
        assertTrue(InputValidator.numberInRange(numMax, numMin, numMax));
    }

    @Test
    public void Given_NumberEqualToRangeMin_Then_ValidatorReturnsTrue() {
        assertTrue(InputValidator.numberInRange(numMin, numMin, numMax));
    }

    @Test
    public void Given_NumberGreaterThanMax_Then_ValidatorReturnsFalse() {
        assertFalse(InputValidator.numberInRange(numMax + 1, numMin, numMax));
    }

    @Test
    public void Given_NumberLessThanMin_Then_ValidatorReturnsFalse() {
        assertFalse(InputValidator.numberInRange(numMin - 1, numMin, numMax));
    }

    @Test
    public void Given_PositionsAreWithinRange_Then_ValidatorReturnsTrue() {
        assertTrue(InputValidator.positionsInRange(positionsWithinRange, positionMin, positionMax));
    }

    @Test
    public void Given_PositionEqualToRangeMax_Then_ValidatorReturnsTrue() {
        assertTrue(InputValidator.positionsInRange(Collections.singletonList(positionMax), positionMin, positionMax));
    }

    @Test
    public void Given_PositionEqualToRangeMin_Then_ValidatorReturnsTrue() {
        assertTrue(InputValidator.positionsInRange(Collections.singletonList(positionMin), positionMin, positionMax));
    }

    @Test
    public void Given_PositionsGreaterThanMax_Then_ValidatorReturnsFalse() {
        assertFalse(InputValidator.positionsInRange(positionsGreaterThanMax, positionMin, positionMax));
    }

    @Test
    public void Given_PositionsLessThanMin_Then_ValidatorReturnsFalse() {
        assertFalse(InputValidator.positionsInRange(positionsLessThanMin, positionMin, positionMax));
    }

}
