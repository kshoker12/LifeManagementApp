package ui;

import Model.App;

import javax.swing.*;
import java.awt.*;

public class WestPanelManager {
    private LifeManagement beastModeApp;
    private JPanel mainPanel;
    private CardLayout layoutWest;
    private MainMenu mainMenu;
    private EastPanelManager eastPanelManager;
    private App beastmode;
    private ResponsibilityManager responsibilityPanel;
    private ToDoListManager toDoListManager;
    private StartHourManager startHourManager;

    public WestPanelManager(LifeManagement beastModeApp, EastPanelManager eastPanelManager, App beastmode) {
        this.beastModeApp = beastModeApp;
        this.beastmode = beastmode;
        layoutWest = new CardLayout();
        mainPanel = new JPanel(layoutWest);
        beastModeApp.add(mainPanel, BorderLayout.WEST);
        this.eastPanelManager = eastPanelManager;
        initWestPanel();
        initMainPanel();
        layoutWest.show(mainPanel, "Main West Panel");
        initToDoListPanel();
        initResponsibilityPanel();
        initStartHourPanel();
    }

    public StartHourManager getStartHourManager() {
        return startHourManager;
    }

    public void initStartHourPanel() {
        startHourManager = new StartHourManager(toDoListManager, eastPanelManager, responsibilityPanel, beastmode);
    }

    private void initToDoListPanel() {
        toDoListManager = new ToDoListManager(eastPanelManager, beastmode, this);
    }

    private void initResponsibilityPanel() {
        responsibilityPanel = new ResponsibilityManager(beastmode, eastPanelManager, toDoListManager);
    }

    private void initWestPanel() {
        WestPanel westPanel = new WestPanel(eastPanelManager, this);
        mainPanel.add(westPanel, "Life Rating West");
    }

    private void initMainPanel() {
        mainMenu = new MainMenu(this, beastModeApp, eastPanelManager);
        mainPanel.add(mainMenu, "Main West Panel");
    }


    public void show(String panelName) {
        layoutWest.show(mainPanel, panelName);
    }

    public void addPanel(JPanel panel, String panelName) {
        mainPanel.add(panel, panelName);
    }

}
