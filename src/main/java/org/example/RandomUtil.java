package org.example;

import java.util.Random;

public class RandomUtil {
    private static final Random random = new Random();

    public static Integer generateRandomBetweenTwoValues(int low, int high) {
        return random.nextInt(high - low) + low;
    }
}
