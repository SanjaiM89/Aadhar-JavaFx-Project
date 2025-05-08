package app.util;

import java.util.Random;

public class IDGenerator {
    public static String generateAadharNumber() {
        Random random = new Random();
        StringBuilder aadhar = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            aadhar.append(random.nextInt(10));
        }
        return aadhar.toString();
    }
}