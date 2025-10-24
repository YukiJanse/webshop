package se.jensen.yuki.webshop_admin.ui;

import java.util.List;
import java.util.Scanner;

import static se.jensen.yuki.webshop_admin.constants.PromptText.MAIN_MENU;
import static se.jensen.yuki.webshop_admin.constants.PromptText.START_MENU;

/**
 * The ConsoleUI class is an implementation of the Ui interface that provides
 * user interaction capabilities via the console. It employs a Scanner object
 * for reading user input and displays messages to the standard output.
 */
public class ConsoleUI implements Ui {
    Scanner scanner;

    /**
     * Constructs a new instance of ConsoleUI that uses the provided Scanner to
     * interact with the user through the console.
     *
     * @param scanner the Scanner object used to read user input from the console
     */
    public ConsoleUI(Scanner scanner) {
        this.scanner = scanner;
    }


    /**
     * Prompts the user with a message in the console and reads the user's input.
     *
     * @param message the message to display to the user
     * @return the text input entered by the user
     */
    @Override
    public String prompt(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    /**
     * Displays an informational message to the user in the console.
     *
     * @param message the message to be displayed in the console
     */
    @Override
    public void info(String message) {
        System.out.println(message + "\n");
    }

    /**
     * Displays an informational message composed of multiple lines to the user in the console.
     *
     * @param messages the list of messages to be displayed, each message appearing on a new line
     */
    @Override
    public void info(List<String> messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message + "\n");
        }
        System.out.println(sb);
    }

    /**
     * Displays the main menu in the console and captures the user's input.
     *
     * @return the user's input as a string after showing the main menu
     */
    @Override
    public String menu() {
        System.out.println(MAIN_MENU);
        return scanner.nextLine();
    }

    /**
     * Displays the start menu in the console and captures the user's input.
     *
     * @return the user's input as a string after displaying the start menu
     */
    @Override
    public String startMenu() {
        System.out.println(START_MENU);
        return scanner.nextLine();
    }
}
