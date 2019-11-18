package core;

public class BoardModifier {

    public void modifyBoard(BoardInstructions instructions) {
        for (Cell cell : instructions.getCellsToKill()) {
            cell.kill();
        }

        for (Cell cell : instructions.getCellsToRevive()) {
            cell.revive();
        }

    }

}
