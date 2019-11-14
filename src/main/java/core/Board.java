package core;

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
        populateBoardWithNewCells(positionsOfLivingCells);
        assignNeighboursToCells();
    }

    private void populateBoardWithNewCells(List<Coordinates> positionsOfLivingCells) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Coordinates position = new Coordinates(x, y);
                boolean alive = false;

                if (positionsOfLivingCells.contains(position)){
                    alive = true;
                }

                neighboursByCell.put(new Cell(alive), new Cell[0]);
            }
        }
    }

    private void assignNeighboursToCells() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Coordinates position = new Coordinates(x, y);
                neighboursByCell.put(getCellByPosition(position), findNeighboursByPosition(position));
            }
        }
    }

    private Cell[] findNeighboursByPosition(Coordinates position) {
        int upY =       (position.y + height - 1) % (height);
        int downY =     (position.y + height + 1) % (height);
        int leftX =     (position.x + width - 1) % (width);
        int rightX =    (position.x + width + 1) % (width);

        return new Cell[] {getCellByPosition(new Coordinates(leftX, upY)),
                getCellByPosition(new Coordinates(position.x, upY)),
                getCellByPosition(new Coordinates(rightX, upY)),
                getCellByPosition(new Coordinates(rightX, position.y)),
                getCellByPosition(new Coordinates(rightX, downY)),
                getCellByPosition(new Coordinates(position.x, downY)),
                getCellByPosition(new Coordinates(leftX, downY)),
                getCellByPosition(new Coordinates(leftX, position.y))};
    }

    public Cell getCellByPosition(Coordinates position) {
        int positionAsIndex = position.y * width + position.x;
        return (Cell) neighboursByCell.keySet().toArray()[positionAsIndex];
    }

    public Set<Cell> getCells() {
        return neighboursByCell.keySet();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell[] getNeighbours(Cell cell) {
        return neighboursByCell.get(cell);
    }

    public int countLivingNeighbours(Cell cell) {
        int total = 0;
        for (Cell neighbour : neighboursByCell.get(cell)) {
            if (neighbour.isAlive()) {
                total++;
            }
        }
        return total;
    }

}
