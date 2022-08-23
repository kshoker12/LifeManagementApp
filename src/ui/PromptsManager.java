package ui;

import Model.Log;
import Model.Prompt;
import Model.PromptsSystem;
import Model.WeeklyPrompt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PromptsManager {
    private PromptsSystem promptsSystem;
    private EastPanelManager eastPanelManager;
    private WestPanelManager westPanelManager;

    public PromptsManager(PromptsSystem promptsSystem, EastPanelManager eastPanelManager, WestPanelManager westPanelManager) {
        this.promptsSystem = promptsSystem;
        this.eastPanelManager = eastPanelManager;
        this.westPanelManager = westPanelManager;
        initPanels();
    }

    private void initPanels() {
        initPromptsMenu();
        initDailyPromptPanel();
        initMainPanel();
        initViewPromptPanel();
        initAddPromptPanel();
        initWeeklyPromptPanel();
    }

    private void initWeeklyPromptPanel() {
        eastPanelManager.addPanel(new WeeklyPromptPanel(), "Weekly Prompts Panel");
    }

    private void initAddPromptPanel() {
        eastPanelManager.addPanel(new AddPromptPanel(), "Add Prompts Panel");
    }

    private void initViewPromptPanel() {
        eastPanelManager.addPanel(new ChooseLogPanel(), "Choose Log Panel");
    }

    private void initDailyPromptPanel() {
        for (Log next: promptsSystem.getLogList()) {
            LogPanel logPanel = new LogPanel(next);
            eastPanelManager.addPanel(logPanel, next.getDay() + " Log Panel");
        }
    }

    private void initPromptsMenu() {
        PromptsMenu promptsMenu = new PromptsMenu();
        westPanelManager.addPanel(promptsMenu, "Prompts Menu");
    }

    private void initMainPanel() {
        MainPromptsPanel promptsPanel = new MainPromptsPanel();
        eastPanelManager.addPanel(promptsPanel, "Prompts Panel");
    }

    private class MainPromptsPanel extends JPanel {

        public MainPromptsPanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.cyan);
            initImage();
        }

        private void initImage() {
            ImageIcon image = new ImageIcon("./Data/view.jpg");
            JLabel imagePanel = new JLabel();
            imagePanel.setIcon(image);
            imagePanel.setIconTextGap(10);
            imagePanel.setPreferredSize(new Dimension(270, 300));
            add(imagePanel);
        }
    }

    private class PromptsMenu extends JPanel {

        public PromptsMenu() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.black);
            initImage();
            initDailyPrompt();
            initWeeklyPrompt();
            initLog();
            initAddPrompt();
            initReset();
            initBack();
        }

        private void initImage() {
            ImageIcon image = new ImageIcon("./Data/quote.png");
            JLabel imagePanel = new JLabel();
            imagePanel.setIcon(image);
            imagePanel.setIconTextGap(10);
            add(imagePanel);
        }

        private void initReset() {
            JButton resetButton = new JButton("Reset");
            add(resetButton);
            resetButton.setPreferredSize(new Dimension(280,40));
            resetButton.setFont(new Font("Arial", Font.BOLD, 20));
            resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    promptsSystem.resetPrompts();
                    initPanels();
                }
            });
        }

        private void initWeeklyPrompt() {
            JButton weeklyPromptButton = new JButton("Weekly Prompts");
            add(weeklyPromptButton);
            weeklyPromptButton.setPreferredSize(new Dimension(280,40));
            weeklyPromptButton.setFont(new Font("Arial", Font.BOLD, 20));
            weeklyPromptButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("Weekly Prompts Panel");
                }
            });
        }

        private void initAddPrompt() {
            JButton addPromptButton = new JButton("Add Prompt");
            add(addPromptButton);
            addPromptButton.setPreferredSize(new Dimension(280,40));
            addPromptButton.setFont(new Font("Arial", Font.BOLD, 20));
            addPromptButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("Add Prompts Panel");
                }
            });
        }

        private void initDailyPrompt() {
            JButton dailyPromptButton = new JButton("Daily Prompts");
            add(dailyPromptButton);
            dailyPromptButton.setPreferredSize(new Dimension(280,40));
            dailyPromptButton.setFont(new Font("Arial", Font.BOLD, 20));
            dailyPromptButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Log curr = promptsSystem.getFirstIncomplete();
                    eastPanelManager.show(curr.getDay() + " Log Panel");
                }
            });
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
                    westPanelManager.show("Main West Panel");
                }
            });
        }

        private void initLog() {
            JButton viewWeeklyLogButton = new JButton("View Weekly Log");
            add(viewWeeklyLogButton);
            viewWeeklyLogButton.setPreferredSize(new Dimension(280,40));
            viewWeeklyLogButton.setFont(new Font("Arial", Font.BOLD, 20));
            viewWeeklyLogButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("Choose Log Panel");
                }
            });
        }
    }

    private class LogPanel extends JPanel {
        private Log log;

        public LogPanel(Log next) {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.lightGray);
            this.log = next;
            initDayLabel();
            initPromptButtons();
            initBackButton();
        }

        private void initBackButton() {
            JButton backButton = new JButton("Back");
            add(backButton);
            backButton.setPreferredSize(new Dimension(280,40));
            backButton.setFont(new Font("Arial", Font.BOLD, 20));
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("Prompts Panel");
                }
            });
        }

        private void initPromptButtons() {
            for (Prompt next: log.getPromptList()) {
                JButton taskLabel = new JButton("Prompt " + next.getId());
                add(taskLabel);
                taskLabel.setPreferredSize(new Dimension(280,30));
                taskLabel.setFont(new Font("Arial", Font.BOLD, 20));
                eastPanelManager.addPanel(new PromptPanel(next), log.getDay() + next.getId() + " Prompt Panel");
                if (next.getComplete()) {
                    taskLabel.setBackground(Color.green);
                } else {
                    taskLabel.setBackground(Color.red);
                    taskLabel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            eastPanelManager.show( log.getDay() + next.getId() + " Prompt Panel");
                        }
                    });
                }
            }
        }

        private void initDayLabel() {
            JTextField hourLabel = new JTextField(log.getDay() + " Prompts");
            hourLabel.setBackground(Color.cyan);
            hourLabel.setFont(new Font("Arial", Font.BOLD, 20));
            hourLabel.setPreferredSize(new Dimension(280, 50));
            hourLabel.setForeground(Color.black);
            hourLabel.setEditable(false);
            hourLabel.setHorizontalAlignment(JTextField.CENTER);
            add(hourLabel);
        }
    }

    private class ChooseLogPanel extends JPanel {

        public ChooseLogPanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.lightGray);
            initWeeklyButton();
            initDayButtons();
            initBackButton();
        }

        private void initWeeklyButton() {
            JButton logButton = new JButton("Weekly Prompts");
            add(logButton);
            logButton.setPreferredSize(new Dimension(280,40));
            logButton.setFont(new Font("Arial", Font.BOLD, 20));
            eastPanelManager.addPanel(new WeeklyPromptViewPanel(promptsSystem.getWeeklyPrompt()), "View Weekly Prompts");
            logButton.setBackground(Color.green);
            logButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("View Weekly Prompts");
                }
            });
        }

        private void initBackButton() {
            JButton backButton = new JButton("Back");
            add(backButton);
            backButton.setPreferredSize(new Dimension(280,40));
            backButton.setFont(new Font("Arial", Font.BOLD, 20));
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("Prompts Panel");
                }
            });
        }

        private void initDayButtons() {
            for (Log log: promptsSystem.getLogList()) {
                JButton logButton = new JButton(log.getDay());
                add(logButton);
                logButton.setPreferredSize(new Dimension(280,40));
                logButton.setFont(new Font("Arial", Font.BOLD, 20));
                eastPanelManager.addPanel(new ChoosePromptPanel(log), log.getDay() + " Choose Prompt Panel");
                logButton.setBackground(Color.green);
                logButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastPanelManager.show(log.getDay() + " Choose Prompt Panel");
                    }
                });
            }
        }

        private class ChoosePromptPanel extends JPanel {
            private Log log;

            public ChoosePromptPanel(Log log) {
                super(new GridLayout(10, 1, 10, 10));
                setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                setPreferredSize(new Dimension(300, 600));
                setBackground(Color.lightGray);
                this.log = log;
                initDayLabel();
                initPrompts();
                initBackButton();
            }

            private void initBackButton() {
                JButton backButton = new JButton("Back");
                add(backButton);
                backButton.setPreferredSize(new Dimension(280,40));
                backButton.setFont(new Font("Arial", Font.BOLD, 20));
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastPanelManager.show("Choose Log Panel");
                    }
                });
            }



            private void initPrompts() {
                for (Prompt prompt: log.getPromptList()) {
                    JButton taskLabel = new JButton("Prompt " + prompt.getId());
                    add(taskLabel);
                    taskLabel.setPreferredSize(new Dimension(280,30));
                    taskLabel.setFont(new Font("Arial", Font.BOLD, 20));
                    taskLabel.setBackground(Color.green);
                    eastPanelManager.addPanel(new PromptViewPanel(prompt), log.getDay() + prompt.getId() + " View Prompt Panel");
                    taskLabel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            eastPanelManager.show(log.getDay() + prompt.getId() + " View Prompt Panel");
                        }
                    });
                }
            }

            private void initDayLabel() {
                JTextField hourLabel = new JTextField(log.getDay() + " Prompts");
                hourLabel.setBackground(Color.cyan);
                hourLabel.setFont(new Font("Arial", Font.BOLD, 20));
                hourLabel.setPreferredSize(new Dimension(280, 50));
                hourLabel.setForeground(Color.black);
                hourLabel.setEditable(false);
                hourLabel.setHorizontalAlignment(JTextField.CENTER);
                add(hourLabel);
            }

        }

        private class WeeklyPromptViewPanel extends JPanel {
            private WeeklyPrompt weeklyPrompt;

            public WeeklyPromptViewPanel(WeeklyPrompt weeklyPrompt) {
                super(new GridLayout(10, 1, 10, 10));
                setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                setPreferredSize(new Dimension(300, 600));
                setBackground(Color.lightGray);
                this.weeklyPrompt = weeklyPrompt;
                initLabel();
                initPrompts();
                initBack();
            }

            private void initPrompts() {
                for (Prompt prompt: weeklyPrompt.getPromptList()) {
                    JButton taskLabel = new JButton("Prompt " + prompt.getId());
                    add(taskLabel);
                    taskLabel.setPreferredSize(new Dimension(280,30));
                    taskLabel.setFont(new Font("Arial", Font.BOLD, 20));
                    taskLabel.setBackground(Color.green);
                    eastPanelManager.addPanel(new PromptViewPanel(prompt), prompt.getId() + " Week View Panel");
                    taskLabel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            eastPanelManager.show(prompt.getId() + " Week View Panel");
                        }
                    });
                }

            }

            private void initLabel() {
                JTextField hourLabel = new JTextField("Weekly Prompts");
                hourLabel.setBackground(Color.cyan);
                hourLabel.setFont(new Font("Arial", Font.BOLD, 20));
                hourLabel.setPreferredSize(new Dimension(280, 50));
                hourLabel.setForeground(Color.black);
                hourLabel.setEditable(false);
                hourLabel.setHorizontalAlignment(JTextField.CENTER);
                add(hourLabel);
            }

            private void initBack() {
                JButton backButton = new JButton("Back");
                add(backButton);
                backButton.setPreferredSize(new Dimension(280,40));
                backButton.setFont(new Font("Arial", Font.BOLD, 20));
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastPanelManager.show("Choose Log Panel");
                    }
                });
            }

        }
    }

    private class AddPromptPanel extends JPanel {
        private JTextField textField;
        private JCheckBox checkBox;

        public AddPromptPanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.lightGray);
            initTextField();
            initConfirm();
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
                    eastPanelManager.show("Prompts Panel");
                }
            });
        }

        private void initConfirm() {
            JButton confirmButton = new JButton("Confirm");
            add(confirmButton);
            confirmButton.setPreferredSize(new Dimension(280,40));
            confirmButton.setFont(new Font("Arial", Font.BOLD, 20));
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String prompt = textField.getText();
                    if (!prompt.equals("")) {
                        textField.setText("");
                        if (checkBox.isSelected()) {
                            promptsSystem.getWeeklyPrompt().addPrompt(prompt);
                        } else {
                            promptsSystem.addPromptQuestion(prompt);
                        }
                    }
                    checkBox.setSelected(false);
                    initPanels();
                    eastPanelManager.show("Prompts Panel");
                }
            });
        }

        private void initTextField() {
            add(new JLabel("Enter Prompt Below"));
            textField = new JTextField(20);
            textField.setPreferredSize(new Dimension(100, 30));
            textField.setFont(new Font("Arial", Font.ITALIC, 16));
            add(textField);
            checkBox = new JCheckBox("Weekly Prompt");
            add(checkBox);
        }
    }

    private class WeeklyPromptPanel extends JPanel {
        private WeeklyPrompt weeklyPrompt;

        public WeeklyPromptPanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.lightGray);
            this.weeklyPrompt = promptsSystem.getWeeklyPrompt();
            initWeeklyPromptsLabel();
            initPromptButtons();
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
                    eastPanelManager.show("Prompts Panel");
                }
            });
        }

        private void initPromptButtons() {
            for (Prompt prompt: weeklyPrompt.getPromptList()) {
                JButton taskLabel = new JButton("Prompt " + prompt.getId());
                add(taskLabel);
                taskLabel.setPreferredSize(new Dimension(280,30));
                taskLabel.setFont(new Font("Arial", Font.BOLD, 20));
                if (prompt.getComplete()) {
                    taskLabel.setBackground(Color.red);
                } else {
                    taskLabel.setBackground(Color.green);
                    eastPanelManager.addPanel(new WeeklyPromptTypePanel(prompt), prompt.getId() + "Week Prompt Panel");
                    taskLabel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            eastPanelManager.show(prompt.getId() + "Week Prompt Panel");
                        }
                    });
                }
            }

        }

        private void initWeeklyPromptsLabel() {
            JTextField hourLabel = new JTextField("Weekly Prompts");
            hourLabel.setBackground(Color.cyan);
            hourLabel.setFont(new Font("Arial", Font.BOLD, 20));
            hourLabel.setPreferredSize(new Dimension(280, 50));
            hourLabel.setForeground(Color.black);
            hourLabel.setEditable(false);
            hourLabel.setHorizontalAlignment(JTextField.CENTER);
            add(hourLabel);
        }
    }

    private class PromptViewPanel extends JPanel {
        private Prompt prompt;

        public PromptViewPanel(Prompt prompt) {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.lightGray);
            this.prompt = prompt;
            initPromptQuestion();
            initPromptTextArea();
            initBackButton();
        }

        private void initBackButton() {
            JButton backButton = new JButton("Back");
            add(backButton);
            backButton.setPreferredSize(new Dimension(280,40));
            backButton.setFont(new Font("Arial", Font.BOLD, 20));
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (prompt.getDay().equals("Week")) {
                        eastPanelManager.show("View Weekly Prompts");
                    } else {
                        eastPanelManager.show(prompt.getDay() + " Choose Prompt Panel");
                    }
                }
            });
        }

        private void initPromptQuestion() {
            JTextField hourLabel = new JTextField("Prompt: " + prompt.getQuestion());
            hourLabel.setBackground(Color.cyan);
            hourLabel.setFont(new Font("Arial", Font.BOLD, 12));
            hourLabel.setPreferredSize(new Dimension(280, 50));
            hourLabel.setForeground(Color.black);
            hourLabel.setEditable(false);
            hourLabel.setHorizontalAlignment(JTextField.CENTER);
            add(hourLabel);
        }

        private void initPromptTextArea() {
            JTextArea jTextArea = new JTextArea();
            jTextArea.setVisible(true);
            jTextArea.setSize(new Dimension(280, 400));
            jTextArea.setBackground(Color.white);
            jTextArea.setLineWrap(true);
            jTextArea.setFont(new Font("Arial", Font.BOLD, 16));
            jTextArea.setText(prompt.getAnswers());
            jTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(jTextArea);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane, BorderLayout.CENTER);
            scrollPane.setPreferredSize(new Dimension(280, 90));
            scrollPane.setVisible(true);
        }
    }

    private class WeeklyPromptTypePanel extends JPanel {
        private Prompt prompt;
        private JTextArea jTextArea;

        public WeeklyPromptTypePanel(Prompt prompt) {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.lightGray);
            this.prompt = prompt;
            initPromptLabel();
            initPromptTextArea();
            initConfirmButton();
            initBackButton();
        }

        private void initBackButton() {
            JButton backButton = new JButton("Back");
            add(backButton);
            backButton.setPreferredSize(new Dimension(280,40));
            backButton.setFont(new Font("Arial", Font.BOLD, 20));
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("Weekly Prompts Panel");
                }
            });
        }

        private void initConfirmButton() {
            JButton confirmButton = new JButton("Confirm");
            add(confirmButton);
            confirmButton.setPreferredSize(new Dimension(280,40));
            confirmButton.setFont(new Font("Arial", Font.BOLD, 20));
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String answer = jTextArea.getText();
                    prompt.addAnswer(answer);
                    jTextArea.setText("");
                    initPanels();
                    eastPanelManager.show("Weekly Prompts Panel");
                }
            });
        }

        private void initPromptTextArea() {
            add(new JLabel("Add Answer Below"));
            jTextArea = new JTextArea();
            jTextArea.setVisible(true);
            jTextArea.setSize(new Dimension(280, 400));
            jTextArea.setBackground(Color.white);
            jTextArea.setLineWrap(true);
            jTextArea.setFont(new Font("Arial", Font.BOLD, 16));

            JScrollPane scrollPane = new JScrollPane(jTextArea);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane, BorderLayout.CENTER);
            scrollPane.setPreferredSize(new Dimension(280, 90));
            scrollPane.setVisible(true);
        }

        private void initPromptLabel() {
            JTextField hourLabel = new JTextField("Prompt: " + prompt.getQuestion());
            hourLabel.setBackground(Color.cyan);
            hourLabel.setFont(new Font("Arial", Font.BOLD, 12));
            hourLabel.setPreferredSize(new Dimension(280, 50));
            hourLabel.setForeground(Color.black);
            hourLabel.setEditable(false);
            hourLabel.setHorizontalAlignment(JTextField.CENTER);
            add(hourLabel);
        }
    }

    private class PromptPanel extends JPanel {
        private Prompt prompt;
        private JTextArea jTextArea;

        public PromptPanel(Prompt prompt) {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.lightGray);
            this.prompt = prompt;
            initPromptLabel();
            initPromptTextArea();
            initConfirmButton();
            initBackButton();
        }

        private void initBackButton() {
            JButton backButton = new JButton("Back");
            add(backButton);
            backButton.setPreferredSize(new Dimension(280,40));
            backButton.setFont(new Font("Arial", Font.BOLD, 20));
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show(prompt.getDay() + " Log Panel");
                }
            });
        }

        private void initConfirmButton() {
            JButton confirmButton = new JButton("Confirm");
            add(confirmButton);
            confirmButton.setPreferredSize(new Dimension(280,40));
            confirmButton.setFont(new Font("Arial", Font.BOLD, 20));
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String answer = jTextArea.getText();
                    prompt.addAnswer(answer);
                    promptsSystem.searchLog(prompt.getDay()).complete();
                    jTextArea.setText("");
                    initPanels();
                    eastPanelManager.show(prompt.getDay() + " Log Panel");
                }
            });
        }

        private void initPromptTextArea() {
            add(new JLabel("Add Answer Below"));
            jTextArea = new JTextArea();
            jTextArea.setVisible(true);
            jTextArea.setSize(new Dimension(280, 400));
            jTextArea.setBackground(Color.white);
            jTextArea.setLineWrap(true);
            jTextArea.setFont(new Font("Arial", Font.BOLD, 16));

            JScrollPane scrollPane = new JScrollPane(jTextArea);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane, BorderLayout.CENTER);
            scrollPane.setPreferredSize(new Dimension(280, 90));
            scrollPane.setVisible(true);
        }

        private void initPromptLabel() {
            JTextField hourLabel = new JTextField("Prompt: " + prompt.getQuestion());
            hourLabel.setBackground(Color.cyan);
            hourLabel.setFont(new Font("Arial", Font.BOLD, 12));
            hourLabel.setPreferredSize(new Dimension(280, 50));
            hourLabel.setForeground(Color.black);
            hourLabel.setEditable(false);
            hourLabel.setHorizontalAlignment(JTextField.CENTER);
            add(hourLabel);
        }
    }
}
