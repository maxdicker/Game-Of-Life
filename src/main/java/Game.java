import java.util.List;

public class Game {
    private UserHandler input;
    private GameDisplay display;
    private BoardModifier controller;

    public Game(UserHandler input, GameDisplay display) {
        this.input = input;
        this.display = display;
        this.controller = new BoardModifier();
    }

    public void run() {
        int width = input.getBoardWidthFromUser(2, 100);
        int height = input.getBoardHeightFromUser(2, 100);
        List<Coordinates> positions = input.getLivingCellPositionsFromUser(new Coordinates(0,0), new Coordinates(width, height));
        Board board = new Board(width, height, positions);

        int simulationLength = input.getSimulationLengthFromUser(0, 100);

        for (int generation = 0; generation < simulationLength; generation++) {
            display.displayBoard(board);
            board = controller.nextState(board);
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
