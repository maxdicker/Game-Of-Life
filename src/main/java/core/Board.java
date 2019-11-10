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
            getCell(position).revive();
        }
    }

    public Cell getCell(Coordinates position) {
        int index = convertToIndex(position);
        return getCell(index);
    }

    private int convertToIndex(Coordinates position) {
        return position.y * width + position.x;
    }

    private Cell getCell(int index) {
        return (Cell) neighboursByCell.keySet().toArray()[index];
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

    public Coordinates getCellPosition(Cell cell) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Coordinates position = new Coordinates(x, y);
                if (getCell(position).equals(cell)) {
                    return position;
                }
            }
        }
        throw new IllegalArgumentException("core.Board does not contain the given cell.");
    }

    public Set<Cell> getCells() {
        return neighboursByCell.keySet();
    }

    public Cell[] getNeighbours(Cell cell) {
        return neighboursByCell.get(cell);
    }

    public void killCell(Cell cell) {
        cell.kill();
    }

    public void reviveCell(Cell cell) {
        cell.revive();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
