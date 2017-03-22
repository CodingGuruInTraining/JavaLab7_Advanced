package com.mark;

import java.util.Scanner;

/**
 * Created by hl4350hb on 3/22/2017.
 */
public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static int getPositiveIntInput(String question) {
        if (question != null) {
            System.out.println(question);
        }
        while (true) {
            try {
                String stringInput = scanner.nextLine();
                int intInput = Integer.parseInt(stringInput);
                if (intInput >= 0) {
                    return intInput;
                }
                else {
                    System.out.println("Please enter a positive number.");
                }
            }
            catch (NumberFormatException err) {
                System.out.println("Please type a positive number.");
            }
        }
    }

    public static String getStringInput(String question) {
        if (question != null) {
            System.out.println(question);
        }
        String entry = scanner.nextLine();
        return entry;
    }
}
