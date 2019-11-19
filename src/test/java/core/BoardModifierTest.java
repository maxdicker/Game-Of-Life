package core;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardModifierTest {

    @Test
    public void KillsAndRevivesCellsAccordingToInstructions() {
        Cell cellToKill = new Cell(true);
        Cell cellToRevive = new Cell(false);
        BoardInstructions instructions = new BoardInstructions();
        instructions.addKillInstruction(cellToKill);
        instructions.addReviveInstruction(cellToRevive);
        BoardModifier modifier = new BoardModifier();

        modifier.modifyBoard(instructions);

        assertFalse(cellToKill.isAlive());
        assertTrue(cellToRevive.isAlive());
    }
}
