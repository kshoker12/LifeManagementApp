package ui;

import Model.Destination;
import Model.WeeklyCompass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class WeeklyCompassManager {
    private LifeManagement beastModeApp;
    private EastPanelManager eastPanelManager;
    private WestPanelManager westPanelManager;
    private WeeklyCompass weeklyCompass;

    public WeeklyCompassManager(LifeManagement beastModeApp, EastPanelManager eastPanelManager, WestPanelManager westPanelManager, WeeklyCompass weeklyCompass) {
        this.beastModeApp = beastModeApp;
        this.westPanelManager = westPanelManager;
        this.eastPanelManager = eastPanelManager;
        this.weeklyCompass = weeklyCompass;
        initPanels();
    }


    private void initPanels() {
        initWeeklyCompassMenu();
        initMainCompassPanel();
        initViewWeeklyCompass();
        initFinishWeek();
        initResetPanel();
    }

    private void initResetPanel() {
        ResetPanel resetPanel = new ResetPanel();
        eastPanelManager.addPanel(resetPanel, "Reset Panel");
    }

    private void initFinishWeek() {
        FinishWeekPanel finishWeekPanel = new FinishWeekPanel();
        eastPanelManager.addPanel(finishWeekPanel, "Finish Week Panel");
    }

    private void initViewWeeklyCompass() {
        WeeklyCompassView weeklyCompassView = new WeeklyCompassView();
        eastPanelManager.addPanel(weeklyCompassView, "Weekly Compass View");
    }

    private void initMainCompassPanel() {
        WeeklyCompassPanel weeklyCompassPanel = new WeeklyCompassPanel();
        eastPanelManager.addPanel(weeklyCompassPanel, "Weekly Compass Panel");
    }

    private void initWeeklyCompassMenu() {
        WeeklyCompassMenu compassMenu = new WeeklyCompassMenu();
        westPanelManager.addPanel(compassMenu, "Weekly Compass Menu");
    }

    private class WeeklyCompassMenu extends JPanel {

        public WeeklyCompassMenu() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.white);
            initView();
            initFinished();
            initReset();
            initButtons();
        }

        private void initFinished() {
            JButton finished = new JButton("Finish Week");
            add(finished);
            finished.setPreferredSize(new Dimension(280,40));
            finished.setFont(new Font("Arial", Font.BOLD, 20));
            finished.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("Finish Week Panel");
                }
            });
        }

        private void initReset() {
            JButton resetButton = new JButton("Weekly Reset");
            add(resetButton);
            resetButton.setPreferredSize(new Dimension(280,40));
            resetButton.setFont(new Font("Arial", Font.BOLD, 20));
            resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    weeklyCompass.reset();
                    eastPanelManager.show("Reset Panel");
                }
            });
        }

        private void initView() {
            JButton viewButtom = new JButton("View Weekly Destinations");
            add(viewButtom);
            viewButtom.setPreferredSize(new Dimension(280,40));
            viewButtom.setFont(new Font("Arial", Font.BOLD, 20));
            viewButtom.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("Weekly Compass View");
                }
            });
        }

        private void initButtons() {
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
    }

    private class WeeklyCompassPanel extends JPanel {

        public WeeklyCompassPanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.darkGray);
        }


    }

    private class WeeklyCompassView extends JPanel {

        public WeeklyCompassView() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.gray);
            initLabel();
            initPercentage();
            initDestinations();
            initBack();
        }

        private void initPercentage() {
            JLabel percentage;
            if (weeklyCompass.isFinish()) {
                percentage = new JLabel(weeklyCompass.getTotalEarnedPoints() + " / " + weeklyCompass.getTotalPoints() + " Points                                    Percentage: "+ weeklyCompass.getPercentage() + "%");
            } else {
                percentage = new JLabel(weeklyCompass.getTotalEarnedPoints() + " / " + weeklyCompass.getTotalPoints() + " Points                                    "+ "Percentage: n/a");
            }
            percentage.setHorizontalAlignment(SwingConstants.LEFT);
            add(percentage);

        }

        private void initBack() {
            JButton backButton = new JButton("Back");
            add(backButton);
            backButton.setPreferredSize(new Dimension(285,40));
            backButton.setFont(new Font("Arial", Font.BOLD, 20));
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("Weekly Compass Panel");
                }
            });
        }

        private void initDestinations() {
            for (Destination next: weeklyCompass.getDestinationList()) {
                JTextField nextLabel;
                if (!next.isFinish()) {
                    nextLabel = new JTextField(next.getName() + ": " + next.getPoint() + " Points");
                } else {
                    nextLabel = new JTextField(next.getName() + ": " + next.getEarned() + " / " + next.getPoint() + " Points");
                }
                nextLabel.setBackground(Color.darkGray);
                nextLabel.setFont(new Font("Arial", Font.BOLD, 16));
                nextLabel.setPreferredSize(new Dimension(285, 30));
                nextLabel.setForeground(Color.white);
                add(nextLabel);
            }
        }

        private void initLabel() {
            JTextField destinationsLabel = new JTextField("Destinations");
            destinationsLabel.setBackground(Color.darkGray);
            destinationsLabel.setFont(new Font("Arial", Font.BOLD, 20));
            destinationsLabel.setPreferredSize(new Dimension(285, 50));
            destinationsLabel.setForeground(Color.white);
            destinationsLabel.setEditable(false);
            destinationsLabel.setHorizontalAlignment(JTextField.CENTER);
            add(destinationsLabel);
        }
    }

    private class FinishWeekPanel extends JPanel {

        public FinishWeekPanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.gray);
            initLabel();
            initDestinations();
            initBack();
        }

        private void initDestinations() {
            for (Destination next: weeklyCompass.getDestinationList()) {
                JButton nextLabel;
                if (!next.isFinish()) {
                    nextLabel = new JButton(next.getName() + ": " + next.getPoint() + " Points");
                    add(nextLabel);
                } else {
                    nextLabel = new JButton(next.getName() + ": " + next.getEarned() + " / " + next.getPoint() + " Points");
                    add(nextLabel);
                }
                FinishDestinationPanel finishDestinationPanel = new FinishDestinationPanel(next);
                eastPanelManager.addPanel(finishDestinationPanel, next.getPoint() + "Finish Panel");
                if (next.isFinish()) {
                    nextLabel.setBackground(Color.green);
                    nextLabel.setFont(new Font("Arial", Font.BOLD, 16));
                    nextLabel.setPreferredSize(new Dimension(285, 30));
                    nextLabel.setForeground(Color.white);
                } else {
                    nextLabel.setBackground(Color.red);
                    nextLabel.setFont(new Font("Arial", Font.BOLD, 16));
                    nextLabel.setPreferredSize(new Dimension(285, 30));
                    nextLabel.setForeground(Color.white);
                    nextLabel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            eastPanelManager.show(next.getPoint() + "Finish Panel");
                        }
                    });
                }
                nextLabel.setHorizontalAlignment(JButton.LEFT);
                add(nextLabel);
            }
        }

        private void initLabel() {
            JTextField destinationsLabel = new JTextField("Destinations");
            destinationsLabel.setBackground(Color.darkGray);
            destinationsLabel.setFont(new Font("Arial", Font.BOLD, 20));
            destinationsLabel.setPreferredSize(new Dimension(285, 50));
            destinationsLabel.setForeground(Color.white);
            destinationsLabel.setEditable(false);
            destinationsLabel.setHorizontalAlignment(JTextField.CENTER);
            add(destinationsLabel);
        }

        private void initBack() {
            JButton backButton = new JButton("Back");
            add(backButton);
            backButton.setPreferredSize(new Dimension(285,40));
            backButton.setFont(new Font("Arial", Font.BOLD, 20));
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("Weekly Compass Panel");
                }
            });
        }

        private class FinishDestinationPanel extends JPanel {
            private Destination destination;

            public FinishDestinationPanel(Destination destination) {
                super(new GridLayout(10, 1, 10, 10));
                setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                setPreferredSize(new Dimension(300, 600));
                setBackground(Color.gray);
                this.destination = destination;
                initLabel();
                initButton();
                initBack();
            }

            private void initButton() {
                for (int i = destination.getPoint(); i >= 0; i--) {
                    int d = i;
                    JButton nextLabel = new JButton(i + " Points");
                    nextLabel.setBackground(Color.green);
                    nextLabel.setFont(new Font("Arial", Font.BOLD, 16));
                    nextLabel.setPreferredSize(new Dimension(285, 30));
                    nextLabel.setForeground(Color.white);
                    add(nextLabel);
                    nextLabel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            destination.setEarned(d);
                            if (weeklyCompass.isFinish()) {
                                weeklyCompass.getEarnedPoints();
                            }
                            initPanels();
                            eastPanelManager.show("Finish Week Panel");
                        }
                    });
                }
            }

            private void initLabel() {
                JTextField destinationsLabel = new JTextField(destination.getName() + ": " + destination.getPoint() + " Points");
                destinationsLabel.setBackground(Color.darkGray);
                destinationsLabel.setFont(new Font("Arial", Font.BOLD, 20));
                destinationsLabel.setPreferredSize(new Dimension(285, 50));
                destinationsLabel.setForeground(Color.white);
                destinationsLabel.setEditable(false);
                destinationsLabel.setHorizontalAlignment(JTextField.CENTER);
                add(destinationsLabel);
            }

            private void initBack() {
                JButton backButton = new JButton("Back");
                add(backButton);
                backButton.setPreferredSize(new Dimension(285,40));
                backButton.setFont(new Font("Arial", Font.BOLD, 20));
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastPanelManager.show("Finish Week Panel");
                    }
                });
            }
        }
    }

    private class ResetPanel extends JPanel {
        private List<JTextField> textFieldList;


        public ResetPanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.gray);
            textFieldList = new ArrayList<>();
            initLabels();
            initConfirm();
        }

        private void initConfirm() {
            JButton backButton = new JButton("Confirm");
            add(backButton);
            backButton.setPreferredSize(new Dimension(285,40));
            backButton.setFont(new Font("Arial", Font.BOLD, 20));
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    initReset();
                    initPanels();
                    eastPanelManager.show("Weekly Compass Panel");

                }
            });
        }

        public void initReset() {
            int i = 0;
            for (Destination next: weeklyCompass.getDestinationList()) {
                next.setName(textFieldList.get(i).getText());
                i+=1;
            }
        }

        private void initLabels() {
            for (int i = 10; i > 0; i--) {
                JLabel label = new JLabel(i + " Points");
                add(label);
                JTextField textField = new JTextField();
                textField.setBackground(Color.green);
                textField.setFont(new Font("Arial", Font.ITALIC, 16));
                textField.setPreferredSize(new Dimension(280, 20));
                textField.setForeground(Color.black);
                textFieldList.add(textField);
                add(textField);
            }
        }
    }
}
