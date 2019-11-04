import java.util.List;

public class Game {
    private Input input;
    private BoardModifier controller;

    public Game(Input input) {
        this.input = input;
        this.controller = new BoardModifier();
    }

    public void run() {
        input.printWelcomeMessageAndRules();

        int width = input.getBoardWidth();
        int height = input.getBoardHeight();
        List<Coordinates> positions = input.getPositionsOfLivingCells();
        Board board = new Board(width, height, positions);

        int simulationLength = input.getLengthOfSimulation();

        for (int generation = 0; generation < simulationLength; generation++) {
            input.clearDisplay();
            input.printBoard(board);
            board = controller.nextState(board);
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
