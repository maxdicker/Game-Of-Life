import java.util.Scanner;

public class ConsoleIO implements IO {
    private Scanner scanner;

    public ConsoleIO() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readUserInput() {
        return scanner.nextLine();
    }

    @Override
    public void displayOutput(String output) {
        System.out.print(output);
    }

}
