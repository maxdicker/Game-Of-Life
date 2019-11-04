public class Main {

    public static void main(String[] args) {
        Input input = new UserInput();
        Game game = new Game(input);
        game.run();
    }
}
