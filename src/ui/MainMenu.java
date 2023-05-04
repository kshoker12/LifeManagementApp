package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private WestPanelManager westPanelManager;
    private EastPanelManager eastPanelManager;
    private LifeManagement beastModeApp;

    public MainMenu(WestPanelManager westPanelManager, LifeManagement beastModeApp, EastPanelManager eastPanelManager) {
        super(new GridLayout(10, 1, 10, 10));
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        setPreferredSize(new Dimension(300, 600));
        setBackground(Color.black);
        this.westPanelManager = westPanelManager;
        this.beastModeApp = beastModeApp;
        this.eastPanelManager = eastPanelManager;
        initPanel();
    }

    private void initPanel() {
        initImage();
        initLifeRatingButton();
        initResponsibility();
        initTodoList();
        initHour();
        initSolutions();
        initWeeklyCompass();
        initPrompts();
        initExit();
    }

    private void initSolutions() {
        JButton solutions = new JButton("Solutions");
        add(solutions);
        solutions.setPreferredSize(new Dimension(280,30));
        solutions.setFont(new Font("Arial", Font.BOLD, 20));
        solutions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eastPanelManager.show("Solutions Menu");
            }
        });
    }

    private void initWeeklyCompass() {
        JButton weeklyCompassButton = new JButton("Weekly Compass");
        add(weeklyCompassButton);
        weeklyCompassButton.setPreferredSize(new Dimension(280,30));
        weeklyCompassButton.setFont(new Font("Arial", Font.BOLD, 20));
        weeklyCompassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                westPanelManager.show("Weekly Compass Menu");
                eastPanelManager.show("Weekly Compass Panel");
            }
        });
    }

    private void initPrompts() {
        JButton promptButton = new JButton("Daily Prompts");
        add(promptButton);
        promptButton.setPreferredSize(new Dimension(280,30));
        promptButton.setFont(new Font("Arial", Font.BOLD, 20));
        promptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eastPanelManager.show("Prompts Panel");
                westPanelManager.show("Prompts Menu");
            }
        });
    }

    private void initImage() {
        ImageIcon image = new ImageIcon("./Data/BoxingGloves.jpg");
        JLabel imagePanel = new JLabel();
        imagePanel.setIcon(image);
        imagePanel.setIconTextGap(10);
        add(imagePanel);
    }

    private void initHour() {
        JButton hourButton = new JButton("Start Hour");
        add(hourButton);
        hourButton.setPreferredSize(new Dimension(280,30));
        hourButton.setFont(new Font("Arial", Font.BOLD, 20));
        hourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eastPanelManager.show("Hour Panel");
            }
        });
    }

    private void initExit() {
        JButton exit = new JButton("Exit");
        add(exit);
        exit.setPreferredSize(new Dimension(280,30));
        exit.setFont(new Font("Arial", Font.BOLD, 20));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                beastModeApp.saveRatingSystem();
                beastModeApp.saveApp();
                beastModeApp.savePromptsSystem();
                beastModeApp.saveCompass();
                beastModeApp.saveSolutions();
                beastModeApp.dispose();
            }
        });
    }

    private void initTodoList() {
        JButton todo = new JButton("To-Do List");
        add(todo);
        todo.setPreferredSize(new Dimension(280,30));
        todo.setFont(new Font("Arial", Font.BOLD, 20));
        todo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eastPanelManager.show("ToDo Panel");
            }
        });
    }

    private void initResponsibility() {
        JButton responsibility = new JButton("Responsibilities");
        add(responsibility);
        responsibility.setPreferredSize(new Dimension(280,30));
        responsibility.setFont(new Font("Arial", Font.BOLD, 20));
        responsibility.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eastPanelManager.show("R Panel");
            }
        });
    }

    private void initLifeRatingButton() {
        JButton lifeRating = new JButton("Ratings");
        add(lifeRating);
        lifeRating.setPreferredSize(new Dimension(280,30));
        lifeRating.setFont(new Font("Arial", Font.BOLD, 20));
        lifeRating.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eastPanelManager.show("Card Panel");
                westPanelManager.show("Life Rating West");
            }
        });
    }


}
