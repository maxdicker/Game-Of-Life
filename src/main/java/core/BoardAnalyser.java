package core;

public class BoardAnalyser {
    private static final int NUMBER_OF_LIVING_NEIGHBOURS_TO_REVIVE_CELL = 3;
    private static final int MIN_NUMBER_OF_LIVING_NEIGHBOURS_TO_SURVIVE = 2;
    private static final int MAX_NUMBER_OF_LIVING_NEIGHBOURS_TO_SURVIVE = 3;

    public BoardInstructions determineBoardChanges(Board board) {
        BoardInstructions instructions = new BoardInstructions();

        for (Cell cell : board.getCells()) {
            int numberOfLivingNeighbours = board.countLivingNeighbours(cell);

            if (numberOfLivingNeighbours < MIN_NUMBER_OF_LIVING_NEIGHBOURS_TO_SURVIVE || numberOfLivingNeighbours > MAX_NUMBER_OF_LIVING_NEIGHBOURS_TO_SURVIVE) {
                instructions.addKillInstruction(cell);
            } else if (numberOfLivingNeighbours == NUMBER_OF_LIVING_NEIGHBOURS_TO_REVIVE_CELL) {
                instructions.addReviveInstruction(cell);
            }
        }

        return instructions;
    }

}
