package io;

import core.Coordinates;

import java.util.List;

public class InputValidator {

    public static boolean numberInRange(int number, int min, int max) {
        return min <= number && number <= max;
    }

    public static boolean positionsInRange(List<Coordinates> positions, Coordinates min, Coordinates max) {
        for (Coordinates position : positions) {
            if (position.compareTo(min) < 0 || position.compareTo(max) > 0) {
                return false;
            }
        }
        return true;
    }

}