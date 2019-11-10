import core.Game;
import io.ConsoleIO;
import io.GameDisplay;
import io.IO;
import io.UserHandler;

public class Main {

    public static void main(String[] args) {
        IO io = new ConsoleIO();
        UserHandler handler = new UserHandler(io);
        GameDisplay display = new GameDisplay(io);
        Game game = new Game(handler, display);
        game.run();
    }
}
