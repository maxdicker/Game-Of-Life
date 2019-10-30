import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleKeyboardIO implements IO {
    private Scanner scanner;

    public ConsoleKeyboardIO() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public int getBoardHeight() {
        System.out.println("What is the height of the World (in cells)? Please provide a whole number.");
        return getInteger();
    }

    private int getInteger() {
        int input;
        try {
            input = scanner.nextInt();
            return input;
        } catch (InputMismatchException e) {
            System.out.println("Please provide an integer.");
            scanner.nextLine();
        }
        return getInteger();
    }

    @Override
    public int getBoardWidth() {
        System.out.println("What is the width of the World (in cells)? Please provide a whole number.");
        return getInteger();
    }

    @Override
    public List<Coordinates> getPositionsOfLivingCells() {
        List<Coordinates> positions = new ArrayList<>();
        System.out.println("What are the positions of the (initially) living cells?");
        System.out.println("Please enter their coordinates - number of cells right and down from the very top-left cell.");
        System.out.println("Format: Amount right, followed by whitespace, then amount down.");
        System.out.println("To stop providing locations, enter 'Q'.");
        String input = "";

        while (!input.equals("Q")) {
            input = scanner.nextLine();

            if (input.matches("[0-9]+\\s[0-9]+")) {
                String[] inputArr = input.split("\\s");

                try {
                    int x = Integer.parseInt(inputArr[0]);
                    int y = Integer.parseInt(inputArr[1]);
                    positions.add(new Coordinates(x, y));
                } catch (InputMismatchException e) {
                    System.out.println("Please provide integers in the required format only.");
                    scanner.nextLine();
                }
            }
        }
        return positions;
    }

    @Override
    public int getLengthOfSimulation() {
        System.out.println("How long would you like the simulation to run? Please enter the number of 'game ticks' as a whole number.");
        return getInteger();
    }

    @Override
    public void printWelcomeMessageAndRules() {
        System.out.println("Welcome to Conway's Game of Life!");
        System.out.println("Some details are required to set the initial state of the World.");
    }

    @Override
    public void printBoard(Board board) {

    }

    @Override
    public void clearDisplay() {

    }
}
