import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("=== Number Guessing Game ===");
        System.out.println("1. Easy (1-50, 10 attempts)");
        System.out.println("2. Medium (1-100, 7 attempts)");
        System.out.println("3. Hard (1-200, 5 attempts)");
        System.out.print("Choose difficulty: ");

        int difficulty = scanner.nextInt();
        int maxNumber, maxAttempts;

        switch (difficulty) {
            case 1:
                maxNumber = 50;
                maxAttempts = 10;
                System.out.println("Easy mode: Guess number between 1-50");
                break;
            case 2:
                maxNumber = 100;
                maxAttempts = 7;
                System.out.println("Medium mode: Guess number between 1-100");
                break;
            case 3:
                maxNumber = 200;
                maxAttempts = 5;
                System.out.println("Hard mode: Guess number between 1-200");
                break;
            default:
                maxNumber = 100;
                maxAttempts = 7;
                System.out.println("Invalid choice. Using Medium mode by default.");
        }

        int secretNumber = random.nextInt(maxNumber) + 1;
        int attempts = 0;
        boolean hasWon = false;

        while (attempts < maxAttempts && !hasWon) {
            System.out.print("Attempt " + (attempts + 1) + "/" + maxAttempts + " - Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess == secretNumber) {
                hasWon = true;
                System.out.println("🎉 Congratulations! You guessed it in " + attempts + " attempts!");
            } else if (guess < secretNumber) {
                System.out.println("Too low! Try a higher number.");
                if (secretNumber - guess <= 5) {
                    System.out.println("Hint: You're very close!");
                }
            } else {
                System.out.println("Too high! Try a lower number.");
                if (guess - secretNumber <= 5) {
                    System.out.println("Hint: You're very close!");
                }
            }

            if (!hasWon && attempts < maxAttempts) {
                System.out.println("Attempts remaining: " + (maxAttempts - attempts));
            }
        }

        if (!hasWon) {
            System.out.println("💀 Game Over! The number was: " + secretNumber);
        }

        System.out.print("Play again? (y/n): ");
        if (scanner.next().toLowerCase().charAt(0) == 'y') {
            main(args);
        }

        scanner.close();
    }
}
