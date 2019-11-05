public class Main {

    public static void main(String[] args) {
        GameDisplay display = new GameDisplay();
        ValidUserInput input = new ValidUserInput();
        Game game = new Game(input, display);
        game.run();
    }
}
