import java.util.List;

public class Game {
    private IO io;
    private BoardController controller;

    public Game(IO io) {
        this.io = io;
        this.controller = new BoardController();
    }

    public void run() {
        io.printWelcomeMessageAndRules();

        int width = io.getBoardWidth();
        int height = io.getBoardHeight();
        List<Coordinates> positions = io.getPositionsOfLivingCells();
        Board board = new Board(width, height, positions);

        int simulationLength = io.getLengthOfSimulation();

        for (int generation = 0; generation < simulationLength; generation++) {
            io.clearDisplay();
            io.printBoard(board);
            board = controller.nextGeneration(board);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
