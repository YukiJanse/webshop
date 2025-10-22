package se.jensen.yuki.webshop_admin.ui;

import java.util.List;

public interface Ui {
    public String prompt(String message);

    public void info(String message);

    public void info(List<String> messages);

    public String menu();

    public String startMenu();
}
