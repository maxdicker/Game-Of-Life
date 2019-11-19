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
        populateBoardWithDeadCells();
        reviveCellsAtPositions(positionsOfLivingCells);
        assignNeighboursToCells();
    }

    private void populateBoardWithDeadCells() {
        for (int index = 0; index < height * width; index++) {
            neighboursByCell.put(new Cell(false), new Cell[0]);
        }
    }

    private void reviveCellsAtPositions(List<Coordinates> positions) {
        for (Coordinates position : positions) {
            getCellAtPosition(position).revive();
        }
    }

    private void assignNeighboursToCells() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Coordinates position = new Coordinates(x, y);
                neighboursByCell.put(getCellAtPosition(position), findNeighboursByPosition(position));
            }
        }
    }

    private Cell[] findNeighboursByPosition(Coordinates position) {
        int upY =       (position.y + height - 1) % (height);
        int downY =     (position.y + height + 1) % (height);
        int leftX =     (position.x + width - 1) % (width);
        int rightX =    (position.x + width + 1) % (width);

        return new Cell[] {getCellAtPosition(new Coordinates(leftX, upY)),
                getCellAtPosition(new Coordinates(position.x, upY)),
                getCellAtPosition(new Coordinates(rightX, upY)),
                getCellAtPosition(new Coordinates(rightX, position.y)),
                getCellAtPosition(new Coordinates(rightX, downY)),
                getCellAtPosition(new Coordinates(position.x, downY)),
                getCellAtPosition(new Coordinates(leftX, downY)),
                getCellAtPosition(new Coordinates(leftX, position.y))};
    }

    public Cell getCellAtPosition(Coordinates position) {
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
