import core.Board;
import core.Coordinates;

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
}
