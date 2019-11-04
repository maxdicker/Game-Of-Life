import java.util.ArrayList;
import java.util.List;

public class BoardInstructions {
    private List<Cell> cellsToKill;
    private List<Cell> cellsToRevive;

    public BoardInstructions() {
        this.cellsToKill = new ArrayList<>();
        this.cellsToRevive = new ArrayList<>();
    }

    public void addKillInstruction(Cell cell) {
        cellsToKill.add(cell);
    }

    public void addReviveInstruction(Cell cell) {
        cellsToRevive.add(cell);
    }

    public List<Cell> getCellsToKill() {
        return cellsToKill;
    }

    public List<Cell> getCellsToRevive() {
        return cellsToRevive;
    }
}
