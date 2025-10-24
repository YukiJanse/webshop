package se.jensen.yuki.webshop_admin.ui;

import se.jensen.yuki.webshop_admin.constants.PromptText;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The DialogUi class is an implementation of the Ui interface that provides
 * user interaction capabilities through dialog-based graphical user interface (GUI) using Swing components.
 */
public class DialogUi implements Ui {

    /**
     * Prompts the user with a specified message and retrieves the user's input
     * through a dialog box. If the user cancels the dialog or provides no input,
     * an empty string is returned.
     *
     * @param message the message to display in the dialog box
     * @return the user's input as a string, or an empty string if the input is null
     */
    @Override
    public String prompt(String message) {
        String choice = JOptionPane.showInputDialog(null, message, "", JOptionPane.PLAIN_MESSAGE);
        return choice == null ? "" : choice;
    }

    /**
     * Displays an informational message to the user using a dialog box.
     *
     * @param message the message to display in the dialog box
     */
    @Override
    public void info(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Displays an informational dialog containing multiple lines of messages.
     * Each message in the provided list is displayed on a new line within a scrollable text area.
     *
     * @param messages the list of messages to be displayed in the dialog;
     *                 each message will appear on a separate line
     */
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

    /**
     * Displays the main menu to the user using a dialog box and captures the user's input.
     * If the user cancels the dialog or provides no input, an empty string is returned.
     *
     * @return the user's input as a string, or an empty string if the input is null
     */
    @Override
    public String menu() {
        String choice = JOptionPane.showInputDialog(null, PromptText.MAIN_MENU, "", JOptionPane.PLAIN_MESSAGE);
        return choice;
    }

    /**
     * Displays the start menu to the user in a dialog box and captures the user's input.
     * The menu includes options for login or quitting the application. If the user cancels
     * the dialog or provides no input, an empty string is returned.
     *
     * @return the user's input as a string, or an empty string if the input is null
     */
    @Override
    public String startMenu() {
        String choice = JOptionPane.showInputDialog(null, PromptText.START_MENU, "", JOptionPane.PLAIN_MESSAGE);
        return choice;
    }
}
