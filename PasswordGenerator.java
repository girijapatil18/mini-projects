import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()_+-=[]{}|;:,.<>?";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Password Generator ===");
        System.out.print("Enter password length (6-50): ");
        int length = scanner.nextInt();

        if (length < 6 || length > 50) {
            System.out.println("Invalid length! Using default length of 12.");
            length = 12;
        }

        System.out.print("Include lowercase letters? (y/n): ");
        boolean includeLower = scanner.next().toLowerCase().charAt(0) == 'y';

        System.out.print("Include uppercase letters? (y/n): ");
        boolean includeUpper = scanner.next().toLowerCase().charAt(0) == 'y';

        System.out.print("Include digits? (y/n): ");
        boolean includeDigits = scanner.next().toLowerCase().charAt(0) == 'y';

        System.out.print("Include symbols? (y/n): ");
        boolean includeSymbols = scanner.next().toLowerCase().charAt(0) == 'y';

        String password = generatePassword(length, includeLower, includeUpper, includeDigits, includeSymbols);

        if (password.isEmpty()) {
            System.out.println("Error: At least one character type must be selected!");
        } else {
            System.out.println("\nGenerated Password: " + password);
            System.out.println("Password Strength: " + getStrengthRating(password));
        }

        scanner.close();
    }

    private static String generatePassword(int length, boolean lower, boolean upper, boolean digits, boolean symbols) {
        StringBuilder charset = new StringBuilder();
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();

        if (lower) charset.append(LOWERCASE);
        if (upper) charset.append(UPPERCASE);
        if (digits) charset.append(DIGITS);
        if (symbols) charset.append(SYMBOLS);

        if (charset.length() == 0) return "";

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charset.length());
            password.append(charset.charAt(randomIndex));
        }

        return password.toString();
    }

    private static String getStrengthRating(String password) {
        int score = 0;

        if (password.length() >= 8) score++;
        if (password.length() >= 12) score++;
        if (password.matches(".*[a-z].*")) score++;
        if (password.matches(".*[A-Z].*")) score++;
        if (password.matches(".*[0-9].*")) score++;
        if (password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{}|;:,.<>?].*")) score++;

        if (score <= 2) return "Weak";
        if (score <= 4) return "Medium";
        return "Strong";
    }
}
