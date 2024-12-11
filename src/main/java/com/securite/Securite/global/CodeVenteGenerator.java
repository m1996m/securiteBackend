package com.securite.Securite.global;

import java.security.SecureRandom;

public class CodeVenteGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SLUG_LENGTH = 5;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateCode() {
        StringBuilder slugBuilder = new StringBuilder(SLUG_LENGTH);
        for (int i = 0; i < SLUG_LENGTH; i++) {
            slugBuilder.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return slugBuilder.toString();
    }
}

