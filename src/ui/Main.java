package ui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                BeastModeApp main = new BeastModeApp();
                main.setVisible(true);
            }
        });
    }
}