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
        int width = input.getBoardWidth(2, 100);
        int height = input.getBoardHeight(2, 100);
        List<Coordinates> positions = input.getPositionsOfLivingCells(new Coordinates(0,0), new Coordinates(width, height));
        Board board = new Board(width, height, positions);

        int simulationLength = input.getLengthOfSimulation(0, 100);

        for (int generation = 0; generation < simulationLength; generation++) {
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
