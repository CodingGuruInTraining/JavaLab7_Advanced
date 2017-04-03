package com.mark;

import java.util.Scanner;

/**
 * This class provides User input prompts and validation.
 */
public class Input {
    // Creates scanner object.
    private static Scanner scanner = new Scanner(System.in);

    public static int getPositiveIntInput(String question) {
        // Checks if provided question string is empty before continuing.
        if (question != null) {
            System.out.println(question);
        }
        // Continues asking question until a valid response is given.
        while (true) {
            // Exception handler.
            try {
                String stringInput = scanner.nextLine();
                // Attempts to parse input.
                int intInput = Integer.parseInt(stringInput);
                // Checks that a positive number has been entered.
                if (intInput >= 0) {
                    return intInput;
                }
                else {
                    System.out.println("Please enter a positive number.");
                }
            }
            // Catch for entering non-integers.
            catch (NumberFormatException err) {
                System.out.println("Please type a positive number.");
            }
        }
    }

    public static String getStringInput(String question) {
        // Checks if provided question string is empty before continuing.
        if (question != null) {
            System.out.println(question);
        }
        // Returns entered string.
        String entry = scanner.nextLine();
        return entry;
    }
}
