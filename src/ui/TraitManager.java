package ui;

import Model.SelfImage;
import Model.Trait;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TraitManager {
    private EastPanelManager eastPanelManager;
    private WestPanelManager westPanelManager;
    private SelfImage selfImage;

    public TraitManager(EastPanelManager eastPanelManager, WestPanelManager westPanelManager, SelfImage selfImage) {
        this.eastPanelManager = eastPanelManager;
        this.westPanelManager = westPanelManager;
        this.selfImage = selfImage;
        initMainMenu();
        initMainTraitPanel();
        initTraitPanels();
    }

    private void initMainTraitPanel() {
        TraitMainPanel traitPanel = new TraitMainPanel();
        eastPanelManager.addPanel(traitPanel, "Main Trait Panel");
    }

    private void initTraitPanels() {
        for (Trait trait: selfImage.getTraitList()) {
            TraitPanel traitPanel = new TraitPanel(trait);
            eastPanelManager.addPanel(traitPanel, trait.getName() + " Trait Panel");
        }
    }

    private void initMainMenu() {
        TraitMenu traitMenu = new TraitMenu();
        westPanelManager.addPanel(traitMenu, "Trait Menu");
    }

    private class TraitMenu extends JPanel {

        public TraitMenu() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.black);
            initLabels();
            initBack();
        }

        private void initBack() {
            JButton backButton = new JButton("Back");
            add(backButton);
            backButton.setPreferredSize(new Dimension(280,40));
            backButton.setFont(new Font("Arial", Font.BOLD, 20));
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    westPanelManager.show("Main West Panel");
                    eastPanelManager.show("Blank Panel");
                }
            });
        }

        private void initLabels() {
            for (Trait trait: selfImage.getTraitList()) {
                JButton traitButton = new JButton(trait.getName());
                add(traitButton);
                traitButton.setPreferredSize(new Dimension(280,40));
                traitButton.setFont(new Font("Arial", Font.BOLD, 20));
                traitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastPanelManager.show(trait.getName() + " Trait Panel");
                    }
                });
            }
        }
    }

    private class TraitPanel extends JPanel {
        private Trait trait;
        private JTextArea jTextArea;

        public TraitPanel(Trait trait) {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.lightGray);
            this.trait = trait;
            initTraitLabel();
            initImage();
            initDescription();
            initToggleEdit();
            initBackButton();
        }

        private void initBackButton() {
            JButton backButton = new JButton("Back");
            add(backButton);
            backButton.setPreferredSize(new Dimension(280,38));
            backButton.setFont(new Font("Arial", Font.BOLD, 20));
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("Main Trait Panel");
                }
            });
        }

        private void initToggleEdit() {
            JButton editButton = new JButton("Toggle Edit");
            add(editButton);
            editButton.setPreferredSize(new Dimension(280,38));
            editButton.setFont(new Font("Arial", Font.BOLD, 20));
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (jTextArea.isEditable()) {
                        jTextArea.setEditable(false);
                        trait.setDescription(jTextArea.getText());
                    } else {
                        jTextArea.setEditable(true);
                    }
                }
            });
        }

        private void initDescription() {
            jTextArea = new JTextArea();
            jTextArea.setVisible(true);
            jTextArea.setSize(new Dimension(280, 400));
            jTextArea.setBackground(Color.white);
            jTextArea.setLineWrap(true);
            jTextArea.setFont(new Font("Arial", Font.BOLD, 16));
            jTextArea.setText(trait.getDescription());
            jTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(jTextArea);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane, BorderLayout.CENTER);
            scrollPane.setPreferredSize(new Dimension(280, 90));
            scrollPane.setVisible(true);
        }

        private void initImage() {
            ImageIcon image = new ImageIcon(trait.getImage());
            JLabel imagePanel = new JLabel();
            imagePanel.setIcon(image);
            imagePanel.setPreferredSize(new Dimension(260, 300));
            imagePanel.setIconTextGap(10);
            add(imagePanel);
        }

        private void initTraitLabel() {
            JTextField rating = new JTextField(trait.getName());
            rating.setBackground(Color.cyan);
            rating.setFont(new Font("Arial", Font.BOLD, 16));
            rating.setPreferredSize(new Dimension(276, 35));
            rating.setBackground(Color.cyan);
            rating.setForeground(Color.black);
            rating.setHorizontalAlignment(JTextField.CENTER);
            rating.setEditable(false);
            add(rating);
        }
    }

    private class TraitMainPanel extends JPanel {

        public TraitMainPanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.lightGray);
        }
    }
}
