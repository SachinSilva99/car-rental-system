package com.carentalsystem.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class IdGenerator {
    final String VALID_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String generateRandomID(int length) {
        Random random = new Random();
        StringBuilder idBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(VALID_CHARACTERS.length());
            char randomChar = VALID_CHARACTERS.charAt(randomIndex);
            idBuilder.append(randomChar);
        }
        return idBuilder.toString();
    }
}
