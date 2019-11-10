package core;

public class BoardModifier {
    private BoardAnalyser analyser;

    public BoardModifier() {
        this.analyser = new BoardAnalyser();
    }

    public Board nextGeneration(Board board) {
        BoardInstructions instructions = analyser.determineBoardChanges(board);

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
