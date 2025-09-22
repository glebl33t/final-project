package ui.utils;

import java.security.SecureRandom;

public final class Generators {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String GMAIL_PATTERN = "%s.%s@gmail.com";
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 10;

    private Generators() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String generateRandomPassword(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0.");
        }
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }

        return sb.toString();
    }

    public static String generateRandomString(int length) {
        int leftLimit = 97;  // 'a'
        int rightLimit = 122; // 'z'

        return RANDOM.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static String generateRandomStringWithRandomLength() {
        int length = RANDOM.nextInt(MAX_LENGTH - MIN_LENGTH + 1) + MIN_LENGTH;
        return generateRandomString(length);
    }

    public static String generateEmail() {

        String firstPart = generateRandomStringWithRandomLength();
        String secondPart = generateRandomStringWithRandomLength();

        return String.format(GMAIL_PATTERN, firstPart, secondPart);
    }
}
