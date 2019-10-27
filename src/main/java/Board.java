import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Board {
    private Map<Cell, Cell[]> neighboursByCell;
    private int width;
    private int height;

    public Board(int width, int height, List<Coordinates> positionsOfAliveCells) {
        this.width = width;
        this.height = height;

        this.neighboursByCell = new LinkedHashMap<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                neighboursByCell.put(new Cell(false), new Cell[8]);
            }
        }

        for (Coordinates position : positionsOfAliveCells) {
            getCell(position).setState(true);
        }
    }

    public Set<Cell> getCells() {
        return neighboursByCell.keySet();
    }

    public Cell getCell(Coordinates position) {
        int positionInSet = position.y * width + position.x;
        return (Cell) neighboursByCell.keySet().toArray()[positionInSet];
    }

}
