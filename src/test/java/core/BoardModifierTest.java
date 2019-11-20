package core;

import org.junit.Before;
import org.junit.Test;
import utils.TestHelper;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoardModifierTest {
    private BoardModifier modifier;
    private BoardInstructions instructions;
    private static final List<Cell> cells = Arrays.asList(
            new Cell(true), new Cell(true), new Cell(false), new Cell(false));

    @Before
    public void init() {
        modifier = new BoardModifier();
        instructions = new BoardInstructions();
    }

    @Test
    public void Given_InstructionsToKillCells_Then_ThoseCellsDie() {
        TestHelper.addKillInstructions(instructions, cells);

        modifier.executeInstructions(instructions);
        int numberOfLivingCells = TestHelper.countLivingCells(cells);

        // since the expected value is so simple, I think doing `var expected = 0;` makes the test less readable
        // also, the name 'actual' makes my goldfish brain forget what the actual is referring to
        assertEquals(0, numberOfLivingCells);
    }

    @Test
    public void Given_InstructionsToReviveCells_Then_ThoseCellsAreRevived() {
        TestHelper.addReviveInstructions(instructions, cells);

        modifier.executeInstructions(instructions);
        int actual = TestHelper.countLivingCells(cells);

        int expected = cells.size();
        assertEquals(expected, actual);
    }

}
