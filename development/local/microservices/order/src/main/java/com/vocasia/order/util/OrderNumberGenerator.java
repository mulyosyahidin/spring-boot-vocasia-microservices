package com.vocasia.order.util;

import java.time.LocalDate;
import java.util.Random;

public class OrderNumberGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();

    public static String generate(Long userId) {
        LocalDate today = LocalDate.now();

        String year = String.valueOf(today.getYear());
        String month = String.format("%02d", today.getMonthValue());
        String date = String.format("%02d", today.getDayOfMonth());

        String randomLetters = generateRandomLetters(4);

        return year + month + date + userId + randomLetters;
    }

    private static String generateRandomLetters(int length) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            result.append(CHARACTERS.charAt(index));
        }

        return result.toString();
    }
}
