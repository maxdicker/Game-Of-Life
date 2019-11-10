package core;

public class BoardAnalyser {
    private final int NUMBER_OF_LIVING_NEIGHBOURS_TO_REVIVE_CELL = 3;
    private final int MIN_NUMBER_OF_LIVING_NEIGHBOURS_TO_SURVIVE = 2;
    private final int MAX_NUMBER_OF_LIVING_NEIGHBOURS_TO_SURVIVE = 3;

    public BoardInstructions determineBoardChanges(Board board) {
        BoardInstructions instructions = new BoardInstructions();

        for (Cell cell : board.getCells()) {
            int numberOfLivingNeighbours = countLivingNeighbours(board, cell);

            if (numberOfLivingNeighbours < MIN_NUMBER_OF_LIVING_NEIGHBOURS_TO_SURVIVE || numberOfLivingNeighbours > MAX_NUMBER_OF_LIVING_NEIGHBOURS_TO_SURVIVE) {
                instructions.addKillInstruction(cell);
            } else if (numberOfLivingNeighbours == NUMBER_OF_LIVING_NEIGHBOURS_TO_REVIVE_CELL) {
                instructions.addReviveInstruction(cell);
            }
        }

        return instructions;
    }

    private int countLivingNeighbours(Board board, Cell cell) {
        int total = 0;
        for (Cell neighbour : board.getNeighbours(cell)) {
            if (neighbour.isAlive()) {
                total++;
            }
        }
        return total;
    }
}
