package se.jensen.yuki.webshop_admin.ui;

import java.util.Scanner;

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
    public String menu() {
        System.out.println("""
                **** Web Shop Admin Menu ***
                1. Add a product
                2. Show all products
                3. Show the information of a product
                4. Quit Menu
                
                Enter from 1 - 4
                """);
        return scanner.nextLine();
    }

}
