public class Main {

    public static void main(String[] args) {
        IO io = new ConsoleIO();
        GameDisplay display = new GameDisplay(io);
        ValidUserInput input = new ValidUserInput(io);
        Game game = new Game(input, display);
        game.run();
    }
}
