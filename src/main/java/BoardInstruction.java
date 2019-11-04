import java.util.ArrayList;
import java.util.List;

public class BoardInstruction {
    private List<Cell> cellsToKill;
    private List<Cell> cellsToRevive;

    public BoardInstruction() {
        this.cellsToKill = new ArrayList<>();
        this.cellsToRevive = new ArrayList<>();
    }

    public void addKillAction(Cell cell) {
        cellsToKill.add(cell);
    }

    public void addReviveAction(Cell cell) {
        cellsToRevive.add(cell);
    }

    public List<Cell> getCellsToKill() {
        return cellsToKill;
    }

    public List<Cell> getCellsToRevive() {
        return cellsToRevive;
    }
}
