package ui;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EastPanelManager {
    private RatingSystem ratingSystem;
    private JPanel eastPanel;
    private CardLayout layoutEast;
    private LifeManagementApp lifeRatingApp;
    private List<Attribute> updateList;

    public EastPanelManager(RatingSystem ratingSystem, LifeManagementApp lifeRatingApp) {
        this.ratingSystem = ratingSystem;
        this.lifeRatingApp = lifeRatingApp;
        layoutEast = new CardLayout();
        eastPanel = new JPanel(layoutEast);
        eastPanel.setPreferredSize(new Dimension(300, 1000));
        lifeRatingApp.add(eastPanel, BorderLayout.EAST);
        initPanels();
        layoutEast.show(eastPanel, "Blank Panel");
    }

    public LifeManagementApp getLifeRatingApp() {
        return lifeRatingApp;
    }

    private void initUpdateList() {
        updateList = new ArrayList<>();
        for (Attribute next: ratingSystem) {
            updateList.add(next);
        }
    }


    void initPanels() {
        initCardPanel();
        initRatingPanel();
        initCategoryPanel();
        initUpdatePanel();
        initBlankPanel();
    }

    public void initBlankPanel() {
        BlankPanel blankPanel = new BlankPanel();
        addPanel(blankPanel, "Blank Panel");
    }

    private void initCardPanel() {
        CardPanel cardPanel = new CardPanel(ratingSystem, this);
        eastPanel.add(cardPanel, "Card Panel");
    }

    private void initUpdatePanel() {
        for (Attribute next: ratingSystem) {
            UpdatePanel nextPanel = new UpdatePanel(next, this);
            eastPanel.add(nextPanel,next.getName() + " Update Panel");
        }
    }

    public void startUpdating() {
        initUpdateList();
        displayUpdatePanels();
    }

    private void displayUpdatePanels() {
        if (!updateList.isEmpty()) {
            show(updateList.get(0).getName() + " Update Panel");
        } else {
            checkReset();
            initPanels();
            show("Card Panel");
        }
    }

    private void checkReset() {
        if (ratingSystem.getCategories().get(0).getAttributes().get(0).getWeeklyRatings().size() >= 7) {
            ratingSystem.update();
        }
    }

    public void removeUpdatePanel() {
        updateList.remove(0);
        displayUpdatePanels();
    }


    private void initCategoryPanel() {
        for (Category next: ratingSystem.getCategories()) {
            CategoryPanel nextPanel = new CategoryPanel(next, this);
            eastPanel.add(nextPanel, next.getName() + " Category Panel");
        }
    }

    private void initRatingPanel() {
        for (Attribute next: ratingSystem) {
            RatingPanel nextPanel = new RatingPanel(next, this);
            eastPanel.add(nextPanel, next.getName() + " Rating Panel");
        }
    }

    public JPanel getEastPanel() {
        return eastPanel;
    }

    public void show(String panelName) {
        layoutEast.show(eastPanel, panelName);
    }

    public void addPanel(JPanel responsibilityViewPanel, String r_view_panel) {
        eastPanel.add(responsibilityViewPanel, r_view_panel);
    }

    private class BlankPanel extends JPanel {

        public BlankPanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.gray);
            initLabel();
            initImage();
            initTotalHours();
            initResponsibilityHours();
        }

        private void initResponsibilityHours() {
            for (Responsibility next: lifeRatingApp.getApp().getResponsibilities()) {
                JTextField responsibilityLabels = new JTextField(next.getName() + " - Hours: " + next.getHours());
                responsibilityLabels.setBackground(Color.darkGray);
                responsibilityLabels.setFont(new Font("Arial", Font.BOLD, 16));
                responsibilityLabels.setPreferredSize(new Dimension(276, 22));
                responsibilityLabels.setForeground(Color.white);
                add(responsibilityLabels);
            }
        }

        private void initTotalHours() {
            JLabel hoursLabel = new JLabel("Total Hours: " + lifeRatingApp.getApp().getHours());
            hoursLabel.setBackground(Color.darkGray);
            hoursLabel.setFont(new Font("Arial", Font.BOLD, 20));
            hoursLabel.setPreferredSize(new Dimension(276, 25));
            hoursLabel.setForeground(Color.white);
            add(hoursLabel);
        }

        private void initLabel() {
            JTextField beastModeLabel = new JTextField("Life Management App");
            beastModeLabel.setBackground(Color.darkGray);
            beastModeLabel.setFont(new Font("Arial", Font.BOLD, 20));
            beastModeLabel.setPreferredSize(new Dimension(285, 50));
            beastModeLabel.setForeground(Color.white);
            beastModeLabel.setEditable(false);
            beastModeLabel.setHorizontalAlignment(JTextField.CENTER);
            add(beastModeLabel);
        }

        private void initImage() {
            ImageIcon image = new ImageIcon("./Data/setting_icon.jpg");
            JLabel imagePanel = new JLabel();
            imagePanel.setIcon(image);
            imagePanel.setIconTextGap(10);
            add(imagePanel);
        }
    }
}
