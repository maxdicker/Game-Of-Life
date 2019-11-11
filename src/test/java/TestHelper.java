import core.Board;
import core.Coordinates;

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
                if (board.getCell(position).isAlive() != comparisonBoard.getCell(position).isAlive()) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean validatePositionListsAreEqual(List<Coordinates> positions, List<Coordinates> comparisonPositions) {

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
}
