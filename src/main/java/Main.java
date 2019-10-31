public class Main {

    public static void main(String[] args) {
        IO io = new ConsoleKeyboardIO();
        Game game = new Game(io);
        game.run();
    }
}
