package ui;

import javax.swing.*;

public class LifeManagementApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                LifeManagement main = new LifeManagement();
                main.setVisible(true);
            }
        });
    }
}
