import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordCounter {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java WordCounter <filename>");
            System.out.println("Example: java WordCounter sample.txt");
            return;
        }

        String filename = args[0];
        analyzeFile(filename);
    }

    private static void analyzeFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;
            int charCountNoSpaces = 0;
            Map<String, Integer> wordFrequency = new HashMap<>();

            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                charCount += line.length();
                charCountNoSpaces += line.replaceAll("\\s", "").length();

                String[] words = line.toLowerCase().split("[\\s\\p{Punct}]+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCount++;
                        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                    }
                }
            }

            System.out.println("=== File Analysis: " + filename + " ===");
            System.out.println("Lines: " + lineCount);
            System.out.println("Words: " + wordCount);
            System.out.println("Characters (with spaces): " + charCount);
            System.out.println("Characters (without spaces): " + charCountNoSpaces);
            System.out.println("Average words per line: " + (lineCount > 0 ? (double) wordCount / lineCount : 0));

            System.out.println("\n=== Most Frequent Words ===");
            wordFrequency.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(10)
                    .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.out.println("\nCreating sample file 'sample.txt' for testing...");
            createSampleFile();
        }
    }

    private static void createSampleFile() {
        try (java.io.PrintWriter writer = new java.io.PrintWriter("sample.txt")) {
            writer.println("This is a sample text file for testing the word counter.");
            writer.println("It contains multiple lines with various words.");
            writer.println("The word counter will analyze this file and provide statistics.");
            writer.println("You can replace this content with your own text.");
            System.out.println("Sample file created! Run: java WordCounter sample.txt");
        } catch (IOException e) {
            System.err.println("Error creating sample file: " + e.getMessage());
        }
    }
}
