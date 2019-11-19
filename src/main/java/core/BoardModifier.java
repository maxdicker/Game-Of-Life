package core;

public class BoardModifier {

    public void executeInstructions(BoardInstructions instructions) {
        for (Cell cell : instructions.getCellsToKill()) {
            cell.kill();
        }

        for (Cell cell : instructions.getCellsToRevive()) {
            cell.revive();
        }

    }

}
