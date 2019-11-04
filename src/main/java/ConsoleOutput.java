public class ConsoleOutput implements Output {

    @Override
    public void displayOutput(String output) {
        System.out.print(output);
    }
}
