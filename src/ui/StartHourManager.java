package ui;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartHourManager {
    private ToDoListManager toDoListManager;
    private EastPanelManager eastPanelManager;
    private ResponsibilityManager responsibilityManagerPanel;
    private App beastmode;

    public StartHourManager(ToDoListManager toDoListManager, EastPanelManager eastPanelManager, ResponsibilityManager responsibilityManagerPanel, App beastmode) {
        this.toDoListManager = toDoListManager;
        this.eastPanelManager = eastPanelManager;
        this.responsibilityManagerPanel = responsibilityManagerPanel;
        this.beastmode = beastmode;
        initPanels();
    }

    public void initPanels() {
        beastmode.initHourlyList();
        initHourlyPanel();
    }


    private void initHourlyPanel() {
        HourPanel hourPanel = new HourPanel();
        eastPanelManager.addPanel(hourPanel, "Hour Panel");
    }

    private class HourPanel extends JPanel {

        public HourPanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.white);
            initHourLabel();
            initTasks();
            initFinishHour();
            initBack();
        }

        private void incrementHour() {
            beastmode.findResponsibility(beastmode.getHourlyList().getTask().getResponsibility()).incrementHours();
//            beastmode.getHourlyList().getTask().getResponsibility().incrementHours();
//            hourlyList.getTask().getResponsibility().incrementHours();
            beastmode.finishHour();
        }

        private void initFinishHour() {
            JButton finishHour = new JButton("Finish Hour");
            add(finishHour);
            finishHour.setPreferredSize(new Dimension(280,40));
            finishHour.setFont(new Font("Arial", Font.BOLD, 20));
            if (beastmode.getHourlyList().getTask() != null) {
                finishHour.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        beastmode.getHourNum().incrementHour();
                        incrementHour();
                        responsibilityManagerPanel.initPanels();
                        eastPanelManager.initBlankPanel();
                        eastPanelManager.show("Blank Panel");
                    }
                });
            }
        }

        private void initBack() {
            JButton backButton = new JButton("Back");
            add(backButton);
            backButton.setPreferredSize(new Dimension(280,40));
            backButton.setFont(new Font("Arial", Font.BOLD, 20));
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("Blank Panel");
                }
            });
        }

        private void initTasks() {
            for (SubTask next: beastmode.getHourlyList()) {
                JButton taskLabel = new JButton(next.getName());
                add(taskLabel);
                taskLabel.setPreferredSize(new Dimension(280,30));
                taskLabel.setFont(new Font("Arial", Font.BOLD, 20));
                taskLabel.setBackground(Color.green);
                JCheckBox checkBox = new JCheckBox();
                taskLabel.add(checkBox);
                checkBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        next.complete();
                        beastmode.searchSubTask(next);
                    }
                });
            }
        }

        private void initHourLabel() {
            JTextField hourLabel = new JTextField(beastmode.getHourNum().getHour() + beastmode.getHourNum().getEnding() + " Hour");
            hourLabel.setBackground(Color.cyan);
            hourLabel.setFont(new Font("Arial", Font.BOLD, 20));
            hourLabel.setPreferredSize(new Dimension(280, 50));
            hourLabel.setForeground(Color.black);
            hourLabel.setEditable(false);
            hourLabel.setHorizontalAlignment(JTextField.CENTER);
            add(hourLabel);
        }
    }
}
