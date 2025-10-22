package se.jensen.yuki.webshop_admin.ui;

import se.jensen.yuki.webshop_admin.model.PromptText;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DialogUi implements Ui {

    @Override
    public String prompt(String message) {
        String choice = JOptionPane.showInputDialog(null, message, "", JOptionPane.PLAIN_MESSAGE);
        return choice == null ? "" : choice;
    }

    @Override
    public void info(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public void info(List<String> messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message + "\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setLineWrap(false);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(200, 300));

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        JOptionPane.showMessageDialog(null, scrollPane, "", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public String menu() {
        String choice = JOptionPane.showInputDialog(null, PromptText.MAIN_MENU, "", JOptionPane.PLAIN_MESSAGE);
        return choice == null ? "" : choice;
    }

    @Override
    public String startMenu() {
        String choice = JOptionPane.showInputDialog(null, PromptText.START_MENU, "", JOptionPane.PLAIN_MESSAGE);
        return choice == null ? "" : choice;
    }
}
