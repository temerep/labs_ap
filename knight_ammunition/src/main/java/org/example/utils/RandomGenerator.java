package org.example.utils;

import org.example.entity.VisionLevel;

import java.util.Random;

public class RandomGenerator {
    private static final int UPPER_DOUBLE = 100;
    private static final int LEFT_LIMIT = 97; // letter 'a'
    private static final int RIGHT_LIMIT = 122; // letter 'z'
    private static final int TARGET_STRING_LENGTH = 10;
    private static final Random random = new Random();

    public static String generateString() {
        return random.ints(LEFT_LIMIT, RIGHT_LIMIT + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(TARGET_STRING_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static double generateDouble() {
        return UPPER_DOUBLE * random.nextDouble();
    }

    public static VisionLevel generateVisionLevel() {
        if (random.nextBoolean()) {
            return VisionLevel.FULL_FACE;
        }
        return VisionLevel.HALF_FACE;
    }
}
