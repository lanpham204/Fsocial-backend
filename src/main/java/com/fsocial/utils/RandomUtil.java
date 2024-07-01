package com.fsocial.utils;

import java.util.Random;

public class RandomUtil {
    public static String randomString() {
        Random random = new Random();

        // Generate a random number between 1000 and 9999
        int randomNumber = 1000 + random.nextInt(9000);
        return String.valueOf(randomNumber);
    }
}
