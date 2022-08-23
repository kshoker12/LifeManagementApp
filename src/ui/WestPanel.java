package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WestPanel extends JPanel {
    private EastPanelManager panelManager;
    private WestPanelManager westPanelManager;


    public WestPanel(EastPanelManager panelManager, WestPanelManager westPanelManager) {
        super(new GridLayout(5, 1, 10, 10));
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        setBackground(Color.black);
        setPreferredSize(new Dimension(300, 600));
        this.panelManager = panelManager;
        this.westPanelManager = westPanelManager;
        ImageIcon image = new ImageIcon("./Data/background.jpg");
        JLabel imagePanel = new JLabel();
        imagePanel.setIcon(image);
        imagePanel.setIconTextGap(10);
        add(imagePanel);
        initButtons();
    }

    private void initButtons() {
        initMentalToughness();
        initMindset();
        initWorkEthics();
        initSocialSkills();
        initUpdate();
        initExit();
    }

    private void initExit() {
        JButton exit = new JButton("Back");
        add(exit);
        exit.setPreferredSize(new Dimension(280,40));
        exit.setFont(new Font("Arial", Font.BOLD, 20));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.show("Blank Panel");
                westPanelManager.show("Main West Panel");
            }
        });
    }

    private void initUpdate() {
        JButton update = new JButton("Update");
        add(update);
        update.setPreferredSize(new Dimension(280,40));
        update.setFont(new Font("Arial", Font.BOLD, 20));
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.startUpdating();
            }
        });
    }

    private void initSocialSkills() {
        JButton socialSkills = new JButton("Social Skills");
        add(socialSkills);
        socialSkills.setPreferredSize(new Dimension(280,40));
        socialSkills.setFont(new Font("Arial", Font.BOLD, 20));
        socialSkills.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.show("Social Skills Category Panel");
            }
        });

    }

    private void initWorkEthics() {
        JButton workEthic = new JButton("Work Ethic");
        add(workEthic);
        workEthic.setPreferredSize(new Dimension(280,40));
        workEthic.setFont(new Font("Arial", Font.BOLD, 20));
        workEthic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.show("Work Ethic Category Panel");
            }
        });
    }

    private void initMindset() {
        JButton mindset = new JButton("Mindset");
        add(mindset);
        mindset.setPreferredSize(new Dimension(280,40));
        mindset.setFont(new Font("Arial", Font.BOLD, 20));
        mindset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.show("Mindset Category Panel");
            }
        });
    }

    private void initMentalToughness() {
        JButton mentalToughness = new JButton("Mental Toughness");
        add(mentalToughness);
        mentalToughness.setPreferredSize(new Dimension(280,40));
        mentalToughness.setFont(new Font("Arial", Font.BOLD, 20));
        mentalToughness.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.show("Mental Toughness Category Panel");
            }
        });
    }
}
