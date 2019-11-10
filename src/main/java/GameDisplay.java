public class GameDisplay {
    private IO io;

    private final String ALIVE_CELL = "\u25FC";
    private final String DEAD_CELL = "\u25FB";

    public GameDisplay(IO io) {
        this.io = io;
    }

    public void displayBoard(Board board) {
        String boardAsString = convertBoardToString(board);

        clearDisplay();
        io.displayOutput(boardAsString);
    }

    private void clearDisplay() {
        io.displayOutput("\033[H\033[2J");
    }

    private String convertBoardToString(Board board) {
        int height = board.getHeight();
        int width = board.getWidth();
        StringBuilder boardAsString = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = board.getCell(new Coordinates(x, y));
                boardAsString.append(getCellSymbol(cell));
            }
            boardAsString.append("\n");
        }

        return boardAsString.toString();
    }

    private String getCellSymbol(Cell cell) {
        if (cell.isAlive()) {
            return ALIVE_CELL;
        } else {
            return DEAD_CELL;
        }
    }

}
