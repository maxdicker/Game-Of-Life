import java.util.ArrayList;
import java.util.List;

public class BoardController {
    private final int NUM_LIVE_NEIGHBOURS_TO_REVIVE_CELL = 3;
    private final int MIN_NUM_OF_LIVE_NEIGHBOURS = 2;
    private final int MAX_NUM_OF_LIVE_NEIGHBOURS = 3;

    public Board nextGeneration(Board board) {
        List<Cell> cellsToKill = new ArrayList<>();
        List<Cell> cellsToRevive = new ArrayList<>();

        for (Cell cell : board.getCells()) {
            int numberOfLivingNeighbours = countLivingNeighbours(board, cell);

            if (numberOfLivingNeighbours < MIN_NUM_OF_LIVE_NEIGHBOURS || numberOfLivingNeighbours > MAX_NUM_OF_LIVE_NEIGHBOURS) {
                cellsToKill.add(cell);
            } else if (numberOfLivingNeighbours == NUM_LIVE_NEIGHBOURS_TO_REVIVE_CELL) {
                cellsToRevive.add(cell);
            }

        }

        killCells(cellsToKill);
        reviveCells(cellsToRevive);

        return board;
    }

    private int countLivingNeighbours(Board board, Cell cell) {
        int total = 0;
        for (Cell neighbour : board.getNeighbours(cell)) {
            if (neighbour.isAlive()) {
                total++;
            }
        }
        return total;
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
