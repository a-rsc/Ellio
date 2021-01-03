package com.jamescho.framework.util;

import java.util.Random;

public class RandomNumberGenerator {
    private static final Random rand = new Random();

    // lowerBound (inclusive) - upperBound (exclusive)
    public static int getRandIntBetween(int lowerBound, int upperBound) {
        return rand.nextInt(upperBound - lowerBound)+lowerBound;
    }

    // upperBound (exclusive)
    public static int getRandInt(int upperBound) {
        return rand.nextInt(upperBound);
    }
}
