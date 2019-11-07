public class GameDisplay {
    private IO io;

    public GameDisplay(IO io) {
        this.io = io;
    }

    private void clearDisplay() {
        io.displayOutput("\033[H\033[2J");
    }

    public void printBoard(Board board) {
        clearDisplay();
        int height = board.getHeight();
        int width = board.getWidth();
        StringBuilder string = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (board.getCell(new Coordinates(x, y)).isAlive()) {
                    string.append("\u25FC");
                } else {
                    string.append("\u25FB");
                }
            }
            string.append("\n");
        }
        io.displayOutput(string.toString());
    }

}
