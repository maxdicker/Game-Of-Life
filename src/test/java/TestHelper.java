import core.Board;
import core.Cell;
import core.Coordinates;

import java.util.Collection;
import java.util.List;

public class TestHelper {

    public static boolean validateBoardsAreEqual(Board board, Board comparisonBoard) {
        int height = board.getHeight();
        int width = board.getWidth();

        if (height != comparisonBoard.getHeight() || width != comparisonBoard.getWidth()) {
            return false;
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Coordinates position = new Coordinates(x, y);
                if (board.getCellByPosition(position).isAlive() != comparisonBoard.getCellByPosition(position).isAlive()) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean validatePositionsAreEqual(List<Coordinates> positions, List<Coordinates> comparisonPositions) {

        if (positions.size() != comparisonPositions.size()) {
            return false;
        }

        for (int index = 0; index < positions.size(); index++) {
            if (!(positions.get(index).compareTo(comparisonPositions.get(index)) == 0)) {
                return false;
            }
        }

        return true;
    }

    public static int countLivingCells(Collection<Cell> cells) {
        int total = 0;
        for (Cell cell : cells) {
            if (cell.isAlive()) {
                total++;
            }
        }
        return total;
    }

    public static Cell[] getCells(Board board, List<Coordinates> cellPositions) {
        Cell[] cells = new Cell[cellPositions.size()];

        for (int index = 0; index < cellPositions.size(); index++) {
            cells[index] = board.getCellByPosition(cellPositions.get(index));
        }

        return cells;
    }

    public static void reviveCells(Board board, List<Coordinates> cellPositions) {
        for (Coordinates position: cellPositions) {
            board.getCellByPosition(position).revive();
        }
    }
}
