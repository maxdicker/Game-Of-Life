import java.util.List;

public class Game {
    private ValidUserInput input;
    private GameDisplay display;
    private BoardModifier controller;

    public Game(ValidUserInput input, GameDisplay display) {
        this.input = input;
        this.display = display;
        this.controller = new BoardModifier();
    }

    public void run() {
        display.printWelcomeMessageAndRules();

        int width = input.getBoardWidth();
        int height = input.getBoardHeight();
        List<Coordinates> positions = input.getPositionsOfLivingCells();
        Board board = new Board(width, height, positions);

        int simulationLength = input.getLengthOfSimulation();

        for (int generation = 0; generation < simulationLength; generation++) {
            display.clearDisplay();
            display.printBoard(board);
            board = controller.nextState(board);
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
