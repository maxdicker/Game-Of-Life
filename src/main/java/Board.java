import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Board {
    private Map<Cell, Cell[]> neighboursByCell;
    private int width;
    private int height;

    public Board(int width, int height, List<Coordinates> positionsOfLivingCells) {
        this.width = width;
        this.height = height;

        this.neighboursByCell = new LinkedHashMap<>();

        for (int i = 0; i < height * width; i++) {
            neighboursByCell.put(new Cell(false), new Cell[0]);
        }

        for (int i = 0; i < height * width; i++) {
            neighboursByCell.put(getCell(i), findNeighbours(i));
        }

        for (Coordinates position : positionsOfLivingCells) {
            getCell(position).setState(true);
        }
    }

    private Cell[] findNeighbours(int index) {
        int total = height*width;

        int upLeft =        (index - 1 + width + total) % total;
        int up =            (index - width + total) % total;
        int upRight =       (index + 1 - width + total) % total;
        int right =         (index + 1 + total) % total;
        int downRight =     (index + 1 + width + total) % total;
        int down =          (index + width + total) % total;
        int downLeft =      (index - 1 + width + width + total) % total;
        int left =          (index - 1 + total) % total;

        return new Cell[] {getCell(upLeft), getCell(up),
                getCell(upRight), getCell(right), getCell(downRight),
                getCell(down), getCell(downLeft), getCell(left)
        };
    }

    private Cell getCell(int index) {
        return (Cell) neighboursByCell.keySet().toArray()[index];
    }

    public Set<Cell> getCells() {
        return neighboursByCell.keySet();
    }

    public Cell getCell(Coordinates position) {
        int coordinatesAsIndex = position.y * width + position.x;
        return getCell(coordinatesAsIndex);
    }

    public Cell[] getNeighbours(Cell cell) {
        return neighboursByCell.get(cell);
    }

}
