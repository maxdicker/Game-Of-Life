import java.util.*;

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

    public Cell getCell(Coordinates position) {
        int index = convertToIndex(position);
        return getCell(index);
    }

    private Cell getCell(int index) {
        return (Cell) neighboursByCell.keySet().toArray()[index];
    }

    private int convertToIndex(Coordinates position) {
        return position.y * width + position.x;
    }

    public Coordinates getCellPosition(Cell cell) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Coordinates position = new Coordinates(x, y);
                if (getCell(position).equals(cell)) {
                    return position;
                }
            }
        }
        throw new IllegalArgumentException("Board does not contain the given cell.");
    }

    private Cell[] findNeighbours(Coordinates position) {
        int upY =       (position.y + height - 1) % (height);
        int downY =     (position.y + height + 1) % (height);
        int leftX =     (position.x + width - 1) % (width);
        int rightX =    (position.x + width + 1) % (width);

        return new Cell[] {getCell(new Coordinates(leftX, upY)), getCell(new Coordinates(position.x, upY)),
                            getCell(new Coordinates(rightX, upY)), getCell(new Coordinates(rightX, position.y)),
                            getCell(new Coordinates(rightX, downY)), getCell(new Coordinates(position.x, downY)),
                            getCell(new Coordinates(leftX, downY)), getCell(new Coordinates(leftX, position.y))};
    }

    public Set<Cell> getCells() {
        return neighboursByCell.keySet();
    }

    public Cell[] getNeighbours(Cell cell) {
        return neighboursByCell.get(cell);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Board board = (Board) o;
        boolean cellsEqual = true;
        for (int i = 0; i < height * width; i++) {
            if (getCell(i).isAlive() != board.getCell(i).isAlive()) {
                cellsEqual = false;
                break;
            }
        }

        return width == board.width &&
                height == board.height &&
                cellsEqual;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                string.append(getCell(y*width + x));
            }
            string.append("\n");
        }
        return string.toString();
    }
}
