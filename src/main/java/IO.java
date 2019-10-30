import java.util.List;

public interface IO {
    int getBoardHeight();
    int getBoardWidth();
    List<Coordinates> getPositionsOfLivingCells();
    int getLengthOfSimulation();
    void printWelcomeMessageAndRules();
    void printBoard(Board board);
    void clearDisplay();
}
