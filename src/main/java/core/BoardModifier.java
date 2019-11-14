package core;

public class BoardModifier {
    private BoardAnalyser analyser;

    public BoardModifier() {
        this.analyser = new BoardAnalyser();
    }

    public Board nextGeneration(Board board) {
        BoardInstructions instructions = analyser.determineBoardChanges(board);
        modifyBoard(instructions);
        return board;
    }

    private void modifyBoard(BoardInstructions instructions) {
        for (Cell cell : instructions.getCellsToKill()) {
            cell.kill();
        }

        for (Cell cell : instructions.getCellsToRevive()) {
            cell.revive();
        }

    }

}
