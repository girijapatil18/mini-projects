import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public double getBalance() { return balance; }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account: " + accountNumber + " | Holder: " + accountHolder + " | Balance: $" + String.format("%.2f", balance);
    }
}

public class BankingSystem {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextAccountNumber = 1001;

    public static void main(String[] args) {
        System.out.println("=== Simple Banking System ===");

        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: createAccount(); break;
                case 2: deposit(); break;
                case 3: withdraw(); break;
                case 4: checkBalance(); break;
                case 5: viewAllAccounts(); break;
                case 6:
                    System.out.println("Thank you for using our banking system!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Check Balance");
        System.out.println("5. View All Accounts");
        System.out.println("6. Exit");
        System.out.print("Choose option: ");
    }

    private static void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial deposit amount: $");
        double initialBalance = scanner.nextDouble();

        if (initialBalance < 0) {
            System.out.println("Initial balance cannot be negative!");
            return;
        }

        String accountNumber = String.valueOf(nextAccountNumber++);
        BankAccount account = new BankAccount(accountNumber, name, initialBalance);
        accounts.add(account);

        System.out.println("Account created successfully!");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + name);
        System.out.println("Initial Balance: $" + String.format("%.2f", initialBalance));
    }

    private static void deposit() {
        BankAccount account = findAccount();
        if (account == null) return;

        System.out.print("Enter deposit amount: $");
        double amount = scanner.nextDouble();

        if (account.deposit(amount)) {
            System.out.println("Deposit successful!");
            System.out.println("New balance: $" + String.format("%.2f", account.getBalance()));
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    private static void withdraw() {
        BankAccount account = findAccount();
        if (account == null) return;

        System.out.print("Enter withdrawal amount: $");
        double amount = scanner.nextDouble();

        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful!");
            System.out.println("New balance: $" + String.format("%.2f", account.getBalance()));
        } else {
            System.out.println("Insufficient funds or invalid amount!");
        }
    }

    private static void checkBalance() {
        BankAccount account = findAccount();
        if (account != null) {
            System.out.println("Current balance: $" + String.format("%.2f", account.getBalance()));
        }
    }

    private static void viewAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        System.out.println("\n=== All Accounts ===");
        for (BankAccount account : accounts) {
            System.out.println(account);
        }
    }

    private static BankAccount findAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        System.out.println("Account not found!");
        return null;
    }
}
