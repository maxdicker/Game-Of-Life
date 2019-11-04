public class CellStateDeterminer {
    private final int NUM_LIVE_NEIGHBOURS_TO_REVIVE_CELL = 3;
    private final int MIN_NUM_OF_LIVE_NEIGHBOURS = 2;
    private final int MAX_NUM_OF_LIVE_NEIGHBOURS = 3;

    public BoardInstruction determineBoardChanges(Board board) {
        BoardInstruction action = new BoardInstruction();

        for (Cell cell : board.getCells()) {
            int numberOfLivingNeighbours = countLivingNeighbours(board, cell);

            if (numberOfLivingNeighbours < MIN_NUM_OF_LIVE_NEIGHBOURS || numberOfLivingNeighbours > MAX_NUM_OF_LIVE_NEIGHBOURS) {
                action.addKillAction(cell);
            } else if (numberOfLivingNeighbours == NUM_LIVE_NEIGHBOURS_TO_REVIVE_CELL) {
                action.addReviveAction(cell);
            }
        }

        return action;
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
