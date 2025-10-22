package se.jensen.yuki.webshop_admin.ui;

import java.util.List;
import java.util.Scanner;

import static se.jensen.yuki.webshop_admin.model.PromptText.MAIN_MENU;
import static se.jensen.yuki.webshop_admin.model.PromptText.START_MENU;

public class ConsoleUI implements Ui {
    Scanner scanner;

    public ConsoleUI(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String prompt(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    @Override
    public void info(String message) {
        System.out.println(message + "\n");
    }

    @Override
    public void info(List<String> messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message + "\n");
        }
        System.out.println(sb);
    }

    @Override
    public String menu() {
        System.out.println(MAIN_MENU);
        return scanner.nextLine();
    }

    @Override
    public String startMenu() {
        System.out.println(START_MENU);
        return scanner.nextLine();
    }
}
