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

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Coordinates position = new Coordinates(x, y);
                neighboursByCell.put(getCell(position), findNeighbours(position));
            }
        }

        for (Coordinates position : positionsOfLivingCells) {
            getCell(position).setState(true);
        }
    }

    private Cell[] findNeighbours(Coordinates position) {
        int up = (position.y + height - 1) % (height);
        int down = (position.y + height + 1) % (height);
        int left = (position.x + width - 1) % (width);
        int right = (position.x + width + 1) % (width);

        return new Cell[] {getCell(new Coordinates(left, up)), getCell(new Coordinates(position.x, up)),
                getCell(new Coordinates(right, up)), getCell(new Coordinates(right, position.y)), getCell(new Coordinates(right, down)),
                getCell(new Coordinates(position.x, down)), getCell(new Coordinates(left, down)), getCell(new Coordinates(left, position.y))
        };
    }

    public Set<Cell> getCells() {
        return neighboursByCell.keySet();
    }

    public Cell getCell(Coordinates position) {
        int positionInSet = position.y * width + position.x;
        return (Cell) neighboursByCell.keySet().toArray()[positionInSet];
    }

    public Cell[] getNeighbours(Cell cell) {
        return neighboursByCell.get(cell);
    }

}
