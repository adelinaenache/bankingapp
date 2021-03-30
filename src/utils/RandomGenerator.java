package utils;

import java.util.Random;

public class RandomGenerator {
    public static String generateRandomString(int noDigits) {
        Random rand = new Random();
        String number = "";

        for (int i = 0; i < noDigits; i++) {
            number += Integer.toString(rand.nextInt(10));
        }

        return number;
    }
}
