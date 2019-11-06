public class Main {

    public static void main(String[] args) {
        GameDisplay display = new GameDisplay();
        IO io = new ConsoleIO();
        ValidUserInput input = new ValidUserInput(io);
        Game game = new Game(input, display);
        game.run();
    }
}
