import core.Game;
import io.ConsoleIO;
import io.GameDisplay;
import io.IO;
import io.UserInputReader;

public class Main {

    public static void main(String[] args) {
        IO io = new ConsoleIO();
        UserInputReader reader = new UserInputReader(io);
        GameDisplay display = new GameDisplay(io);
        Game game = new Game(reader, display);
        game.run();
    }
}
