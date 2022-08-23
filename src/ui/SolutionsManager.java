package ui;

import Model.Problem;
import Model.Solutions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolutionsManager {
    private EastPanelManager eastPanelManager;
    private Solutions solutions;

    public SolutionsManager(Solutions solutions, EastPanelManager eastPanelManager) {
        this.solutions = solutions;
        this.eastPanelManager = eastPanelManager;
        initPanels();
    }

    private void initAddProblemPanel() {
        AddProblemPanel addProblemPanel = new AddProblemPanel();
        eastPanelManager.addPanel(addProblemPanel, "Add Problem Panel");
    }

    private void initPanels() {
        SolutionsMenu solutionsMenu = new SolutionsMenu();
        eastPanelManager.addPanel(solutionsMenu, "Solutions Menu");
        initAddProblemPanel();
        initViewPanel();
        initRemovePanel();
    }

    private void initRemovePanel() {
        ProblemsRemovePanel problemsRemovePanel = new ProblemsRemovePanel();
        eastPanelManager.addPanel(problemsRemovePanel, "Problems Remove Panel");
    }

    private void initViewPanel() {
        ProblemsViewPanel problemsViewPanel = new ProblemsViewPanel();
        eastPanelManager.addPanel(problemsViewPanel, "Problems View Panel");
    }

    private class SolutionsMenu extends JPanel {

        public SolutionsMenu() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.gray);
            initSolutionsLabel();
            initViewProblem();
            initAddProblem();
            initClear();
            initBack();
        }

        private void initClear() {
            JButton addProblemButton = new JButton("Clear Solved");
            add(addProblemButton);
            addProblemButton.setPreferredSize(new Dimension(280,40));
            addProblemButton.setFont(new Font("Arial", Font.BOLD, 20));
            addProblemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    solutions.clear();
                    initPanels();
                }
            });
        }

        private void initAddProblem() {
            JButton addProblemButton = new JButton("Add Problem");
            add(addProblemButton);
            addProblemButton.setPreferredSize(new Dimension(280,40));
            addProblemButton.setFont(new Font("Arial", Font.BOLD, 20));
            addProblemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("Add Problem Panel");
                }
            });
        }

        private void initViewProblem() {
            JButton viewProblems = new JButton("View Problems");
            add(viewProblems);
            viewProblems.setPreferredSize(new Dimension(280,40));
            viewProblems.setFont(new Font("Arial", Font.BOLD, 20));
            viewProblems.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("Problems View Panel");
                }
            });
        }

        private void initSolutionsLabel() {
            JTextField solutionsLabel = new JTextField("Solutions");
            solutionsLabel.setBackground(Color.darkGray);
            solutionsLabel.setFont(new Font("Arial", Font.BOLD, 20));
            solutionsLabel.setPreferredSize(new Dimension(285, 50));
            solutionsLabel.setForeground(Color.white);
            solutionsLabel.setEditable(false);
            solutionsLabel.setHorizontalAlignment(JTextField.CENTER);
            add(solutionsLabel);
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
    }

    private class AddProblemPanel extends JPanel {
        private JTextField textField;

        public AddProblemPanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.gray);
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
                    eastPanelManager.show("Solutions Menu");
                    textField.setText("");
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
                    if (!textField.getText().isEmpty()) {
                        solutions.addProblem(new Problem(textField.getText()));
                        initPanels();
                    }
                    textField.setText("");
                    eastPanelManager.show("Solutions Menu");
                }
            });
        }

        private void initTextField() {
            add(new JLabel("Enter Problem"));
            textField = new JTextField(20);
            textField.setPreferredSize(new Dimension(280, 30));
            textField.setFont(new Font("Arial", Font.ITALIC, 16));
            add(textField);
        }
    }

    private class ProblemsViewPanel extends JPanel {

        public ProblemsViewPanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.gray);
            initLabel();
            initStats();
            initProblem();
            initToggleRemove();
            initBack();
        }

        private void initStats() {
            JLabel jLabel = new JLabel("Completed: " + solutions.getCompletedTasks() + " / " + solutions.getProblemList().size());
            JLabel jLabel2 = new JLabel("Pending: " + solutions.getPendingTasks() + " / " + solutions.getProblemList().size());
            add(jLabel);
            jLabel.setHorizontalAlignment(SwingConstants.LEFT);
            add(jLabel2);
        }

        private void initToggleRemove() {
            JButton removeButton = new JButton("Toggle Remove");
            add(removeButton);
            removeButton.setPreferredSize(new Dimension(280,40));
            removeButton.setFont(new Font("Arial", Font.BOLD, 20));
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("Problems Remove Panel");
                }
            });
        }

        private void initProblem() {
            for (Problem problem: solutions.getProblemList()) {
                JButton nextLabel = new JButton(problem.getProblem());
                JCheckBox checkBox = new JCheckBox();
                nextLabel.add(checkBox);
                if (problem.isSolved()) {
                    checkBox.setSelected(true);
                } else {
                    checkBox.setSelected(false);
                }
                nextLabel.setBackground(Color.green);
                nextLabel.setFont(new Font("Arial", Font.BOLD, 15));
                nextLabel.setPreferredSize(new Dimension(285, 30));
                nextLabel.setForeground(Color.white);
                add(nextLabel);
                ProblemViewPanel problemViewPanel = new ProblemViewPanel(problem);
                eastPanelManager.addPanel(problemViewPanel, problem.getProblem() + " View Panel");
                nextLabel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastPanelManager.show(problem.getProblem() + " View Panel");
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
                    eastPanelManager.show("Solutions Menu");
                }
            });
        }

        private void initLabel() {
            JTextField solutionsLabel = new JTextField("Problems");
            solutionsLabel.setBackground(Color.darkGray);
            solutionsLabel.setFont(new Font("Arial", Font.BOLD, 20));
            solutionsLabel.setPreferredSize(new Dimension(285, 50));
            solutionsLabel.setForeground(Color.white);
            solutionsLabel.setEditable(false);
            solutionsLabel.setHorizontalAlignment(JTextField.CENTER);
            add(solutionsLabel);
        }

        private class ProblemViewPanel extends JPanel{
            private Problem problem;
            private JTextArea jTextArea;

            public ProblemViewPanel(Problem problem) {
                super(new GridLayout(10, 1, 10, 10));
                setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                setPreferredSize(new Dimension(300, 600));
                setBackground(Color.gray);
                this.problem = problem;
                initTextArea();
                initSolved();
                initBack();
            }

            private void initSolved() {
                JButton solvedButton = new JButton("Solved");
                add(solvedButton);
                solvedButton.setPreferredSize(new Dimension(280,40));
                solvedButton.setFont(new Font("Arial", Font.BOLD, 20));
                solvedButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        problem.solve(jTextArea.getText());
                        initPanels();
                        eastPanelManager.show("Problems View Panel");
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
                        problem.setSolution(jTextArea.getText());
                        eastPanelManager.show("Problems View Panel");
                    }
                });
            }

            private void initTextArea() {
                jTextArea = new JTextArea();
                jTextArea.setVisible(true);
                jTextArea.setSize(new Dimension(280, 400));
                jTextArea.setBackground(Color.white);
                jTextArea.setLineWrap(true);
                jTextArea.setFont(new Font("Arial", Font.BOLD, 16));
                jTextArea.setText(problem.getSolution());
                jTextArea.setEditable(true);
                JScrollPane scrollPane = new JScrollPane(jTextArea);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                add(scrollPane, BorderLayout.CENTER);
                scrollPane.setPreferredSize(new Dimension(280, 90));
                scrollPane.setVisible(true);
            }
        }
    }

    private class ProblemsRemovePanel extends JPanel {

        public ProblemsRemovePanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.gray);
            initLabel();
            initStats();
            initProblem();
            initToggleRemove();
            initBack();
        }

        private void initStats() {
            JLabel jLabel = new JLabel("Completed: " + solutions.getCompletedTasks() + " / " + solutions.getProblemList().size());
            jLabel.setHorizontalAlignment(SwingConstants.LEFT);
            JLabel jLabel2 = new JLabel("Pending: " + solutions.getPendingTasks() + " / " + solutions.getProblemList().size());
            add(jLabel);
            add(jLabel2);
        }

        private void initToggleRemove() {
            JButton removeButton = new JButton("Toggle Remove");
            add(removeButton);
            removeButton.setPreferredSize(new Dimension(280,40));
            removeButton.setFont(new Font("Arial", Font.BOLD, 20));
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("Problems View Panel");
                }
            });
        }

        private void initProblem() {

            for (Problem problem: solutions.getProblemList()) {
                JButton nextLabel = new JButton(problem.getProblem());
                JCheckBox checkBox = new JCheckBox();
                nextLabel.add(checkBox);
                if (problem.isSolved()) {
                    checkBox.setSelected(true);
                } else {
                    checkBox.setSelected(false);
                }
                nextLabel.setBackground(Color.red);
                nextLabel.setFont(new Font("Arial", Font.BOLD, 15));
                nextLabel.setPreferredSize(new Dimension(285, 30));
                nextLabel.setForeground(Color.white);
                add(nextLabel);
                nextLabel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        solutions.removeProblem(problem);
                        initPanels();
                        eastPanelManager.show("Problems View Panel");
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
                    eastPanelManager.show("Solutions Menu");
                }
            });
        }

        private void initLabel() {
            JTextField solutionsLabel = new JTextField("Problems");
            solutionsLabel.setBackground(Color.darkGray);
            solutionsLabel.setFont(new Font("Arial", Font.BOLD, 20));
            solutionsLabel.setPreferredSize(new Dimension(285, 50));
            solutionsLabel.setForeground(Color.white);
            solutionsLabel.setEditable(false);
            solutionsLabel.setHorizontalAlignment(JTextField.CENTER);
            add(solutionsLabel);
        }
    }
}
