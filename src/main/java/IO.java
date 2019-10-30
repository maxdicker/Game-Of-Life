import java.util.List;

public interface IO {
    int getBoardHeight();
    int getBoardWidth();
    List<Coordinates> getPositionsOfLivingCells();
    void displayBoard(Board board);
    void clearDisplay();
}
