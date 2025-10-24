package se.jensen.yuki.webshop_admin.ui;

import java.util.List;

/**
 * The Ui interface defines a contract for user interaction in an application.
 * It includes methods for prompting user input, displaying informational messages,
 * and presenting menu options.
 */
public interface Ui {
    public String prompt(String message);

    public void info(String message);

    public void info(List<String> messages);

    public String menu();

    public String startMenu();
}
