import java.util.List;

public class BoardActioner {

    public Board transformBoard(Board board, BoardAction action) {
        killCells(action.getCellsToKill());
        reviveCells(action.getCellsToRevive());
        return board;
    }

    private void reviveCells(List<Cell> cellsToRevive) {
        for (Cell cell : cellsToRevive) {
            cell.setState(true);
        }
    }

    private void killCells(List<Cell> cellsToKill) {
        for (Cell cell : cellsToKill) {
            cell.setState(false);
        }
    }

}
