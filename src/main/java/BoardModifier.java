import java.util.List;

public class BoardModifier {
    private CellStateDeterminer determiner = new CellStateDeterminer();

    public Board nextGeneration(Board board) {
        BoardInstruction instruction = determiner.determineBoardChanges(board);

        killCells(instruction.getCellsToKill());
        reviveCells(instruction.getCellsToRevive());

        return board;
    }

    private void reviveCells(List<Cell> cellsToRevive) {
        for (Cell cell : cellsToRevive) {
            cell.revive();
        }
    }

    private void killCells(List<Cell> cellsToKill) {
        for (Cell cell : cellsToKill) {
            cell.kill();
        }
    }

}
