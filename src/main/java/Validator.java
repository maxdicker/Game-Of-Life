public class Validator {

    public static boolean numberInRange(int number, int min, int max) {
        return min <= number && number <= max;
    }

    public static boolean positionInRange(Coordinates position, Coordinates min, Coordinates max) {
        return position.compareTo(min) >= 0 && position.compareTo(max) <= 0;
    }

}