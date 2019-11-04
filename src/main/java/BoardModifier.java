public class BoardModifier {
    private CellStateDeterminer determiner = new CellStateDeterminer();

    public Board nextState(Board board) {
        BoardInstructions instructions = determiner.determineBoardChanges(board);

        modifyBoard(board, instructions);

        return board;
    }

    private void modifyBoard(Board board, BoardInstructions instructions) {
        for (Cell cell : instructions.getCellsToKill()) {
            board.killCell(cell);
        }

        for (Cell cell : instructions.getCellsToRevive()) {
            board.reviveCell(cell);
        }

    }

}
