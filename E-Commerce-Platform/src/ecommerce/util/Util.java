package ecommerce.util;

import ecommerce.config.Config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

public final class Util {
    private static final DateTimeFormatter FRIENDLY = DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a", Locale.US);
    private static final DateTimeFormatter ISO = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private Util() {
    }

    public static String money(double value) {
        return Config.CURRENCY + String.format(Locale.US, "%,.2f", value);
    }

    public static String plain(double value) {
        return String.format(Locale.US, "%.2f", value);
    }

    public static String dateTime(LocalDateTime value) {
        return value.format(FRIENDLY);
    }

    public static String iso(LocalDateTime value) {
        return value.format(ISO);
    }

    public static LocalDateTime parseIso(String value) {
        try {
            return LocalDateTime.parse(value, ISO);
        } catch (DateTimeParseException e) {
            return LocalDateTime.now();
        }
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidEmail(String value) {
        return value != null && EMAIL.matcher(value.trim()).matches();
    }

    public static boolean isValidPhone(String value) {
        if (value == null) {
            return false;
        }
        String digits = value.replaceAll("[^0-9]", "");
        return digits.length() >= 7 && digits.length() <= 15;
    }

    public static String digitsOnly(String value) {
        return value == null ? "" : value.replaceAll("[^0-9]", "");
    }

    /** Luhn checksum, the same check real payment forms use before contacting a gateway. */
    public static boolean passesLuhn(String digits) {
        if (digits == null || digits.length() < 12) {
            return false;
        }
        int sum = 0;
        boolean doubleDigit = false;
        for (int i = digits.length() - 1; i >= 0; i--) {
            int digit = digits.charAt(i) - '0';
            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            doubleDigit = !doubleDigit;
        }
        return sum % 10 == 0;
    }

    public static String reference(String prefix) {
        int value = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
        return prefix + "-" + String.format(Locale.US, "%08X", value);
    }

    public static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public static String ratingText(double rating) {
        return String.format(Locale.US, "%.1f / 5", rating);
    }
}
