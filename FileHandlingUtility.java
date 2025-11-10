/**
 * ================================================================
 *                     FILE HANDLING UTILITY
 * ================================================================
 * 
 * PROJECT OBJECTIVE:
 * ------------------
 * This Java program demonstrates basic file handling operations such as:
 * 1. Writing data to a file
 * 2. Reading data from a file
 * 3. Modifying existing text within a file
 *
 * The program uses core Java I/O classes such as:
 * - FileReader
 * - FileWriter
 * - BufferedReader
 * - BufferedWriter
 *
 * This implementation is console-based and allows the user to 
 * choose from multiple options to perform file operations.
 *
 * Developed by  : Vivin
 * Department     : Computer Science and Engineering
 * Submitted to   : Internship Task
 * Date           : November 2025
 * Language Used  : Java
 * ================================================================
 */

import java.io.*;
import java.util.Scanner;

public class FileHandlingUtility {

    // Constant file name used for all operations
    private static final String FILE_NAME = "sample.txt";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("================================================");
        System.out.println("             FILE HANDLING UTILITY              ");
        System.out.println("================================================");
        System.out.println("1. Write to File");
        System.out.println("2. Read from File");
        System.out.println("3. Modify File Content");
        System.out.print("Enter your choice: ");

        int choice = sc.nextInt();
        sc.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                writeToFile(sc);
                break;
            case 2:
                readFromFile();
                break;
            case 3:
                modifyFile(sc);
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }

        sc.close();
    }

    /**
     * =================================================================
     * METHOD: writeToFile()
     * -----------------------------------------------------------------
     * Purpose:
     * --------
     * To write user input text to a file named 'sample.txt'.
     * 
     * Description:
     * ------------
     * - Takes multiple lines of input from the user.
     * - Writes each line to the file using BufferedWriter.
     * - Stops when the user types 'exit'.
     * 
     * Exception Handling:
     * -------------------
     * IOException handled to ensure smooth file writing operation.
     * =================================================================
     */
    private static void writeToFile(Scanner sc) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            System.out.println("Enter text to write into the file (type 'exit' to stop):");

            while (true) {
                String line = sc.nextLine();
                if (line.equalsIgnoreCase("exit"))
                    break;
                writer.write(line);
                writer.newLine();
            }

            System.out.println("✅ File written successfully: " + FILE_NAME);

        } catch (IOException e) {
            System.out.println("⚠ Error writing to file: " + e.getMessage());
        }
    }

    /**
     * =================================================================
     * METHOD: readFromFile()
     * -----------------------------------------------------------------
     * Purpose:
     * --------
     * To read and display all contents of the file 'sample.txt'.
     * 
     * Description:
     * ------------
     * - Uses BufferedReader to read each line.
     * - Displays file contents on the console.
     * 
     * Exception Handling:
     * -------------------
     * - FileNotFoundException: If file doesn't exist.
     * - IOException: For read errors.
     * =================================================================
     */
    private static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            System.out.println("\n--- File Content ---");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("---------------------");

        } catch (FileNotFoundException e) {
            System.out.println("⚠ File not found! Please write data first.");
        } catch (IOException e) {
            System.out.println("⚠ Error reading file: " + e.getMessage());
        }
    }

    /**
     * =================================================================
     * METHOD: modifyFile()
     * -----------------------------------------------------------------
     * Purpose:
     * --------
     * To modify an existing file by replacing a word or text.
     * 
     * Description:
     * ------------
     * - Reads the entire file content into a StringBuilder.
     * - Takes user input for word to replace and new word.
     * - Replaces all occurrences and overwrites the file.
     * 
     * Exception Handling:
     * -------------------
     * Handles IOException for both reading and writing operations.
     * =================================================================
     */
    private static void modifyFile(Scanner sc) {
        try {
            // Step 1: Read existing content
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append(System.lineSeparator());
                }
            }

            // Step 2: Ask user for modification details
            System.out.print("Enter the word to replace: ");
            String oldWord = sc.nextLine();

            System.out.print("Enter the new word: ");
            String newWord = sc.nextLine();

            // Step 3: Replace text
            String modifiedContent = content.toString().replaceAll(oldWord, newWord);

            // Step 4: Write updated content back to file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                writer.write(modifiedContent);
            }

            System.out.println("✅ File modified successfully!");

        } catch (IOException e) {
            System.out.println("⚠ Error modifying file: " + e.getMessage());
        }
    }
}
