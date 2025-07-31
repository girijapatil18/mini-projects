import java.util.ArrayList;
import java.util.Scanner;

class Task {
    private String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String getDescription() { return description; }
    public boolean isCompleted() { return completed; }
    public void markCompleted() { this.completed = true; }

    @Override
    public String toString() {
        return (completed ? "[✓] " : "[ ] ") + description;
    }
}

public class TodoApp {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== To-Do List Manager ===");

        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addTask(); break;
                case 2: viewTasks(); break;
                case 3: markTaskComplete(); break;
                case 4: removeTask(); break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Mark Complete");
        System.out.println("4. Remove Task");
        System.out.println("5. Exit");
        System.out.print("Choose option: ");
    }

    private static void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        tasks.add(new Task(description));
        System.out.println("Task added successfully!");
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        System.out.println("\nYour Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void markTaskComplete() {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to mark complete: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markCompleted();
            System.out.println("Task marked as complete!");
        } else {
            System.out.println("Invalid task number!");
        }
    }

    private static void removeTask() {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to remove: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("Task removed successfully!");
        } else {
            System.out.println("Invalid task number!");
        }
    }
}
