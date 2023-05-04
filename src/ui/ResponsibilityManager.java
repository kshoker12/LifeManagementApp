package ui;

import Model.App;
import Model.Responsibility;
import Model.SubTask;
import Model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResponsibilityManager {
    private App beastmode;
    private EastPanelManager eastPanelManager;
    private JPanel responsibilityPanel;
    private JPanel responsibilityManagePanel;
    private JTextField textField;
    private ToDoListManager toDoListManager;

    public ResponsibilityManager(App beastmode, EastPanelManager eastManager, ToDoListManager toDoListManager) {
        this.beastmode = beastmode;
        this.eastPanelManager = eastManager;
        this.toDoListManager = toDoListManager;
        initPanels0();
    }

    public void initPanels0() {
        initResponsibilityPanel();
        initResponsibilityManagePanel();
        initResponsibilityViewPanel();
        initResponsibilityAddTaskPanel();
        toDoListManager.initPanels0();
    }

    public void initPanels() {
        initResponsibilityPanel();
        initResponsibilityManagePanel();
        initResponsibilityViewPanel();
        initResponsibilityAddTaskPanel();
        toDoListManager.initPanels();
    }

    private void initResponsibilityAddTaskPanel() {
        for (Responsibility next: beastmode.getResponsibilities()) {
            ResponsibilityAddTaskPanel responsibilityAddTaskPanel = new ResponsibilityAddTaskPanel(next);
            eastPanelManager.addPanel(responsibilityAddTaskPanel, next.getName() + " Add Panel");
        }
    }

    private void initResponsibilityViewPanel() {
        for (Responsibility next: beastmode.getResponsibilities()) {
            ResponsibilityViewPanel responsibilityViewPanel = new ResponsibilityViewPanel(next);
            eastPanelManager.addPanel(responsibilityViewPanel, next.getName() + " View Panel");
        }
    }

    private void initResponsibilityManagePanel() {
        responsibilityManagePanel = new JPanel(new GridLayout(10, 1, 10, 10));
        eastPanelManager.addPanel(responsibilityManagePanel, "Responsibility Manage Panel");
        responsibilityManagePanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        responsibilityManagePanel.setPreferredSize(new Dimension(300, 600));
        responsibilityManagePanel.setBackground(Color.white);
        setupResponsibilityManagePanel();
    }

    private void setupResponsibilityManagePanel() {
        for (Responsibility next: beastmode.getResponsibilities()) {
            JTextField nextLabel = new JTextField(next.getName());
            responsibilityManagePanel.add(nextLabel);
            nextLabel.setBackground(Color.green);
            nextLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            nextLabel.setPreferredSize(new Dimension(280, 30));
            nextLabel.setForeground(Color.black);
            nextLabel.setEditable(false);
        }
        initEntryField();
        initConfirm();
    }

    private void initEntryField() {
        responsibilityManagePanel.add(new JLabel("Enter Responsibility Name"));
        textField = new JTextField(20);
        textField.setPreferredSize(new Dimension(100, 30));
        textField.setFont(new Font("Arial", Font.ITALIC, 16));
        responsibilityManagePanel.add(textField);
    }

    // MODIFIES: east, managerPanel
    // EFFECTS: creates the confirm button as well as its behaviour
    private void initConfirm() {
        JButton confirm = new JButton("Confirm");
        confirm.setPreferredSize(new Dimension(280, 30));
        responsibilityManagePanel.add(confirm);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().isEmpty()) {
                    beastmode.addResponsibility(new Responsibility(textField.getText()));
                    textField.setText("");
                }
                initPanels();
                eastPanelManager.initBlankPanel();
                eastPanelManager.show("R Panel");
            }
        });
    }


    private void initResponsibilityPanel() {
        responsibilityPanel = new ResponsibilityPanel();
        eastPanelManager.addPanel(responsibilityPanel, "R Panel");
    }


    private class ResponsibilityPanel extends JPanel {

        public ResponsibilityPanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.white);
            JTextField nextLabel = new JTextField("Responsibilities");
            add(nextLabel);
            nextLabel.setBackground(Color.cyan);
            nextLabel.setFont(new Font("Arial", Font.BOLD, 20));
            nextLabel.setPreferredSize(new Dimension(280, 50));
            nextLabel.setForeground(Color.black);
            nextLabel.setEditable(false);
            nextLabel.setHorizontalAlignment(JTextField.CENTER);
            add(new JLabel("Tasks completed: " + beastmode.getNumCompletedTasks() + " / " + beastmode.getNumTasks()));
            initResponsibility();
            initAddResponsibility();
            initRemoveResponsibility();
            initResetResponsibility();
            initBackButton();
        }

        private void initResetResponsibility() {
            JButton resetButton = new JButton("Weekly Reset");
            add(resetButton);
            resetButton.setPreferredSize(new Dimension(280,40));
            resetButton.setFont(new Font("Arial", Font.BOLD, 20));
            resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    for (Responsibility next: beastmode.getResponsibilities()) {
//                        next.clear();
//                        eastPanelManager.getLifeRatingApp().getBeastmode().reset();
//                        initPanels();
//                        eastPanelManager.initBlankPanel();
//                        eastPanelManager.show("R Panel");
//                    }
                    beastmode.reset();
                    beastmode.getTodoList().clear();
                    initPanels();
                    eastPanelManager.initBlankPanel();
                    eastPanelManager.show("R Panel");
                }
            });
        }

        private void initRemoveResponsibility() {
            JButton removeResponsibility = new JButton("Toggle Remove");
            add(removeResponsibility);
            removeResponsibility.setPreferredSize(new Dimension(280,40));
            removeResponsibility.setFont(new Font("Arial", Font.BOLD, 20));
            eastPanelManager.addPanel(new ResponsibilityRemovePanel(beastmode), "R Remove Panel");
            removeResponsibility.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("R Remove Panel");
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
                    eastPanelManager.show("Blank Panel");
                }
            });
        }

        private void initResponsibility() {
            for (Responsibility next: beastmode.getResponsibilities()) {
                JButton nextButton = new JButton(next.getName());
                add(nextButton);
                nextButton.setPreferredSize(new Dimension(280,30));
                nextButton.setFont(new Font("Arial", Font.BOLD, 20));
                nextButton.setBackground(Color.green);
                nextButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastPanelManager.show(next.getName() + " View Panel");
                    }
                });
            }
        }

        private void initAddResponsibility() {
            JButton addButton = new JButton("Add Responsibility");
            add(addButton);
            addButton.setPreferredSize(new Dimension(280,40));
            addButton.setFont(new Font("Arial", Font.BOLD, 20));
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("Responsibility Manage Panel");
                }
            });
        }

        private class ResponsibilityRemovePanel extends JPanel {
            private App beastmode;

            public ResponsibilityRemovePanel(App beastmode) {
                super(new GridLayout(10, 1, 10, 10));
                setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                setPreferredSize(new Dimension(300, 600));
                setBackground(Color.white);
                JTextField nextLabel = new JTextField("Responsibilities");
                add(nextLabel);
                this.beastmode = beastmode;
                nextLabel.setBackground(Color.cyan);
                nextLabel.setFont(new Font("Arial", Font.BOLD, 20));
                nextLabel.setPreferredSize(new Dimension(280, 50));
                nextLabel.setForeground(Color.black);
                nextLabel.setEditable(false);
                nextLabel.setHorizontalAlignment(JTextField.CENTER);
                add(new JLabel("Tasks completed: " + beastmode.getNumCompletedTasks() + " / " + beastmode.getNumTasks()));
                initResponsibility();
                initAddResponsibility();
                initRemoveResponsibility();
                initResetResponsibility();
                initBackButton();
            }

            private void initResetResponsibility() {
                JButton resetButton = new JButton("Weekly Reset");
                add(resetButton);
                resetButton.setPreferredSize(new Dimension(280,40));
                resetButton.setFont(new Font("Arial", Font.BOLD, 20));
                resetButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (Responsibility next: beastmode.getResponsibilities()) {
                            next.reset();
                        }
                    }
                });
            }

            private void initRemoveResponsibility() {
                JButton removeResponsibility = new JButton("Toggle Remove");
                add(removeResponsibility);
                removeResponsibility.setPreferredSize(new Dimension(280,40));
                removeResponsibility.setFont(new Font("Arial", Font.BOLD, 20));
                removeResponsibility.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastPanelManager.show("R Panel");
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
                        eastPanelManager.show("Blank Panel");
                    }
                });
            }

            private void initResponsibility() {
                for (Responsibility next: beastmode.getResponsibilities()) {
                    JButton nextButton = new JButton(next.getName());
                    add(nextButton);
                    nextButton.setPreferredSize(new Dimension(280,30));
                    nextButton.setFont(new Font("Arial", Font.BOLD, 20));
                    nextButton.setBackground(Color.red);
                    nextButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            beastmode.getResponsibilities().remove(next);
                            eastPanelManager.initBlankPanel();
                            initPanels();
                            eastPanelManager.show("R Panel");
                        }
                    });
                }
            }

            private void initAddResponsibility() {
                JButton addButton = new JButton("Add Responsibility");
                add(addButton);
                addButton.setPreferredSize(new Dimension(280,40));
                addButton.setFont(new Font("Arial", Font.BOLD, 20));
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastPanelManager.show("Responsibility Manage Panel");
                    }
                });
            }
        }
    }

    private class ResponsibilityViewPanel extends JPanel {
        private Responsibility responsibility;

        public ResponsibilityViewPanel(Responsibility responsibility) {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.white);
            this.responsibility = responsibility;
            initRemoveTaskPanel();
            initResponsibilityLabel();
            initHoursLabel();
            initTaskLabel();
            initAddTask();
            initDeleteResponsibility();
            initBackLabel();
        }



        private void initRemoveTaskPanel() {
            eastPanelManager.addPanel(new ResponsibilityRemoveTaskPanel(responsibility), responsibility.getName() + " Remove Panel");
        }

        private void initDeleteResponsibility() {
            JButton deleteResponsibility = new JButton("Toggle Remove Task");
            add(deleteResponsibility);
            deleteResponsibility.setPreferredSize(new Dimension(280,40));
            deleteResponsibility.setFont(new Font("Arial", Font.BOLD, 20));
            deleteResponsibility.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show(responsibility.getName() + " Remove Panel");
                }
            });
        }

        private void initBackLabel() {
            JButton backButton = new JButton("Back");
            add(backButton);
            backButton.setPreferredSize(new Dimension(280,40));
            backButton.setFont(new Font("Arial", Font.BOLD, 20));
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("R Panel");
                }
            });
        }

        private void initAddTask() {
            JButton addButton = new JButton("Add Task");
            add(addButton);
            addButton.setPreferredSize(new Dimension(280,40));
            addButton.setFont(new Font("Arial", Font.BOLD, 20));
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show(responsibility.getName() + " Add Panel");
                }
            });
        }

        private void initTaskLabel() {
            for (Task task: responsibility.getTasks()) {
                JButton taskLabel = new JButton(task.getName());
                add(taskLabel);
                taskLabel.setPreferredSize(new Dimension(280,30));
                taskLabel.setFont(new Font("Arial", Font.BOLD, 20));
                taskLabel.setBackground(Color.green);
                TaskPanel taskPanel = new TaskPanel(task);
                JCheckBox checkBox = new JCheckBox();
                taskLabel.add(checkBox);
                checkBox.setEnabled(false);
                if (task.getIsCompleted()) {
                    checkBox.setSelected(true);
                }
                eastPanelManager.addPanel(taskPanel, task.getName() + " Task Panel");
                taskLabel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastPanelManager.show(task.getName() + " Task Panel");
                    }
                });
            }
        }


        private void initHoursLabel() {
            JLabel hoursLabel = new JLabel("Hours: " + responsibility.getHours() + "        Tasks completed: " + responsibility.getNumCompletedTasks() + " / " + responsibility.getNumTasks());
            hoursLabel.setBackground(Color.cyan);
            hoursLabel.setFont(new Font("Arial", Font.BOLD, 12));
            hoursLabel.setPreferredSize(new Dimension(276, 35));
            hoursLabel.setBackground(Color.cyan);
            hoursLabel.setForeground(Color.black);
            add(hoursLabel);
        }

        private void initResponsibilityLabel() {
            JTextField responsibilityLabel = new JTextField(responsibility.getName());
            responsibilityLabel.setBackground(Color.cyan);
            responsibilityLabel.setFont(new Font("Arial", Font.BOLD, 20));
            responsibilityLabel.setPreferredSize(new Dimension(280, 50));
            responsibilityLabel.setForeground(Color.black);
            responsibilityLabel.setEditable(false);
            responsibilityLabel.setHorizontalAlignment(JTextField.CENTER);
            add(responsibilityLabel);
        }

        private class TaskPanel extends JPanel {
            private Task task;

            public TaskPanel(Task task) {
                super(new GridLayout(10, 1, 10, 10));
                setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                setPreferredSize(new Dimension(300, 600));
                setBackground(Color.white);
                this.task = task;
                initTaskLabel();
                initDurationLabel();
                initSubTasks();
                initAddSubTask();
                initRemoveSubTask();
                initBackLabel();
                initAddSubTaskPanel();
                initRemoveSubTaskPanel();

            }

            private void initRemoveSubTaskPanel() {
                eastPanelManager.addPanel(new RemoveSubTaskPanel(task), task.getName() + " Remove Sub-Task Panel");
            }

            private void initAddSubTaskPanel() {
                eastPanelManager.addPanel(new AddSubTaskPanel(task), task.getName() + " Add Sub-Task Panel");
            }

            private void initBackLabel() {
                JButton backButton = new JButton("Back");
                add(backButton);
                backButton.setPreferredSize(new Dimension(280,40));
                backButton.setFont(new Font("Arial", Font.BOLD, 20));
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
//                        eastPanelManager.show(task.getResponsibility().getName() + " View Panel");
                        eastPanelManager.show(beastmode.findResponsibility(task.getResponsibility()).getName() + " View Panel");
                    }
                });
            }



            private void initRemoveSubTask() {
                JButton removeSubTask = new JButton("Toggle Remove Sub-Task");
                add(removeSubTask);
                removeSubTask.setPreferredSize(new Dimension(280,40));
                removeSubTask.setFont(new Font("Arial", Font.BOLD, 20));
                removeSubTask.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastPanelManager.show(task.getName() + " Remove Sub-Task Panel");
                    }
                });
            }

            private void initAddSubTask() {
                JButton addButton = new JButton("Add Sub-Task");
                add(addButton);
                addButton.setPreferredSize(new Dimension(280,40));
                addButton.setFont(new Font("Arial", Font.BOLD, 20));
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastPanelManager.show(task.getName() + " Add Sub-Task Panel");
                    }
                });
            }

            private void initSubTasks() {
                for (SubTask next: task.getSubTasks()) {
                    JButton nextLabel = new JButton(next.getName());
                    add(nextLabel);
                    nextLabel.setBackground(Color.green);
                    nextLabel.setFont(new Font("Arial", Font.ITALIC, 16));
                    nextLabel.setPreferredSize(new Dimension(280, 30));
                    nextLabel.setForeground(Color.black);
                    JCheckBox checkBox = new JCheckBox();
                    nextLabel.add(checkBox);
                    checkBox.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            next.complete();
                            beastmode.searchSubTask(next);
                            initPanels();
                            eastPanelManager.show(task.getName() + " Task Panel");
                        }
                    });
                    if (next.getIsCompleted()) {
                        checkBox.setSelected(true);
                    }
//                    nextLabel.setEditable(false);
                }
            }

            private void initTaskLabel() {
                JTextField nextLabel = new JTextField(task.getName());
                add(nextLabel);
                nextLabel.setBackground(Color.cyan);
                nextLabel.setFont(new Font("Arial", Font.BOLD, 20));
                nextLabel.setPreferredSize(new Dimension(280, 50));
                nextLabel.setForeground(Color.black);
                nextLabel.setHorizontalAlignment(JTextField.CENTER);
                nextLabel.setEditable(false);
            }

            private void initDurationLabel() {
                JLabel durationLabel = new JLabel("Duration: " + task.getDuration() + "        Sub-Tasks completed: " + task.getNumCompletedTasks() + " / " + task.getNumTasks());
                durationLabel.setBackground(Color.cyan);
                durationLabel.setFont(new Font("Arial", Font.BOLD, 12));
                durationLabel.setPreferredSize(new Dimension(276, 35));
                durationLabel.setBackground(Color.cyan);
                durationLabel.setForeground(Color.black);
                add(durationLabel);
            }

            private class AddSubTaskPanel extends JPanel {
                private Task task;
                private JTextField text;
                private JTextField duration;

                public AddSubTaskPanel(Task task) {
                    super(new GridLayout(10, 1, 10, 10));
                    setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                    setPreferredSize(new Dimension(300, 600));
                    setBackground(Color.white);
                    this.task = task;
                    initTaskLabel();
                    initTextField();
                    initConfirm();
                    initBackButton();
                }

                private void initConfirm() {
                    JButton confirm = new JButton("Confirm");
                    add(confirm);
                    confirm.setPreferredSize(new Dimension(280,40));
                    confirm.setFont(new Font("Arial", Font.BOLD, 20));
                    confirm.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            task.addSubtask(new SubTask(text.getText(), Integer.parseInt(duration.getText()), task.getName()));
                            text.setText("");
                            duration.setText("");
                            initPanels();
                            eastPanelManager.show(task.getName() + " Task Panel");
                        }
                    });
                }

                private void initTextField() {
                    add(new JLabel("Enter Sub-Task Name"));
                    text = new JTextField(20);
                    text.setPreferredSize(new Dimension(100, 30));
                    text.setFont(new Font("Arial", Font.ITALIC, 16));
                    add(text);
                    add(new JLabel("Enter Duration"));
                    duration = new JTextField(20);
                    duration.setPreferredSize(new Dimension(100, 30));
                    duration.setFont(new Font("Arial", Font.ITALIC, 16));
                    add(duration);
                }

                private void initTaskLabel() {
                    JTextField taskLabel = new JTextField(task.getName());
                    taskLabel.setBackground(Color.cyan);
                    taskLabel.setFont(new Font("Arial", Font.BOLD, 20));
                    taskLabel.setPreferredSize(new Dimension(280, 50));
                    taskLabel.setBackground(Color.cyan);
                    taskLabel.setForeground(Color.black);
                    taskLabel.setHorizontalAlignment(JTextField.CENTER);
                    taskLabel.setEditable(false);
                    add(taskLabel);
                }

                private void initBackButton() {
                    JButton confirm = new JButton("Back");
                    add(confirm);
                    confirm.setPreferredSize(new Dimension(280,40));
                    confirm.setFont(new Font("Arial", Font.BOLD, 20));
                    confirm.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            eastPanelManager.show(task.getName() + " Task Panel");
                        }
                    });
                }
            }

            private class RemoveSubTaskPanel extends JPanel {
                private Task task;

                public RemoveSubTaskPanel(Task task) {
                    super(new GridLayout(10, 1, 10, 10));
                    setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                    setPreferredSize(new Dimension(300, 600));
                    setBackground(Color.white);
                    this.task = task;
                    initTaskLabel();
                    initDurationLabel();
                    initSubTasks();
                    initAddSubTask();
                    initRemoveSubTask();
                    initBackLabel();
                }

                private void initRemoveSubTask() {
                    JButton removeSubTask = new JButton("Toggle Remove Sub-Task");
                    add(removeSubTask);
                    removeSubTask.setPreferredSize(new Dimension(280,40));
                    removeSubTask.setFont(new Font("Arial", Font.BOLD, 20));
                    removeSubTask.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            eastPanelManager.show(task.getName() + " Task Panel");
                        }
                    });
                }

                private void initAddSubTask() {
                    JButton addButton = new JButton("Add Sub-Task");
                    add(addButton);
                    addButton.setPreferredSize(new Dimension(280,40));
                    addButton.setFont(new Font("Arial", Font.BOLD, 20));
                    addButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            eastPanelManager.show(task.getName() + " Add Sub-Task Panel");
                        }
                    });
                }

                private void initTaskLabel() {
                    JTextField nextLabel = new JTextField(task.getName());
                    add(nextLabel);
                    nextLabel.setBackground(Color.cyan);
                    nextLabel.setFont(new Font("Arial", Font.BOLD, 20));
                    nextLabel.setPreferredSize(new Dimension(280, 50));
                    nextLabel.setForeground(Color.black);
                    nextLabel.setHorizontalAlignment(JTextField.CENTER);
                    nextLabel.setEditable(false);
                }

                private void initDurationLabel() {
                    JLabel durationLabel = new JLabel("Duration: " + task.getDuration() + "        Sub-Tasks completed: " + task.getNumCompletedTasks() + " / " + task.getNumTasks());
                    durationLabel.setBackground(Color.cyan);
                    durationLabel.setFont(new Font("Arial", Font.BOLD, 12));
                    durationLabel.setPreferredSize(new Dimension(276, 35));
                    durationLabel.setBackground(Color.cyan);
                    durationLabel.setForeground(Color.black);
                    add(durationLabel);
                }

                private void initBackLabel() {
                    JButton backButton = new JButton("Back");
                    add(backButton);
                    backButton.setPreferredSize(new Dimension(280,40));
                    backButton.setFont(new Font("Arial", Font.BOLD, 20));
                    backButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
//                            eastPanelManager.show(task.getResponsibility().getName() + " View Panel");
                            eastPanelManager.show(beastmode.findResponsibility(task.getResponsibility()).getName() + " View Panel");
                        }
                    });
                }

                private void initSubTasks() {
                    for (SubTask next: task.getSubTasks()) {
                        JButton nextLabel = new JButton(next.getName());
                        add(nextLabel);
                        nextLabel.setBackground(Color.red);
                        nextLabel.setFont(new Font("Arial", Font.ITALIC, 16));
                        nextLabel.setPreferredSize(new Dimension(280, 30));
                        nextLabel.setForeground(Color.black);
                        nextLabel.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                task.removeSubtask(next);
                                initPanels();
                                eastPanelManager.show(task.getName() + " Task Panel");
                            }
                        });
                    }
                }
            }
        }

        private class ResponsibilityRemoveTaskPanel extends JPanel {
            private Responsibility responsibility;

            public ResponsibilityRemoveTaskPanel(Responsibility responsibility) {
                super(new GridLayout(10, 1, 10, 10));
                setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                setPreferredSize(new Dimension(300, 600));
                setBackground(Color.white);
                this.responsibility = responsibility;
                initResponsibilityLabel();
                initHoursLabel();
                initTaskLabel();
                initAddTask();
                initDeleteResponsibility();
                initBackLabel();
            }

            private void initDeleteResponsibility() {
                JButton deleteResponsibility = new JButton("Toggle Remove Task");
                add(deleteResponsibility);
                deleteResponsibility.setPreferredSize(new Dimension(280,40));
                deleteResponsibility.setFont(new Font("Arial", Font.BOLD, 20));
                deleteResponsibility.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastPanelManager.show(responsibility.getName() + " View Panel");
                    }
                });
            }

            private void initBackLabel() {
                JButton backButton = new JButton("Back");
                add(backButton);
                backButton.setPreferredSize(new Dimension(280,40));
                backButton.setFont(new Font("Arial", Font.BOLD, 20));
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastPanelManager.show("R Panel");
                    }
                });
            }

            private void initAddTask() {
                JButton addButton = new JButton("Add Task");
                add(addButton);
                addButton.setPreferredSize(new Dimension(280,40));
                addButton.setFont(new Font("Arial", Font.BOLD, 20));
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastPanelManager.show(responsibility.getName() + " Add Panel");
                    }
                });
            }

            private void initTaskLabel() {
                for (Task task: responsibility.getTasks()) {
                    JButton taskLabel = new JButton(task.getName());
                    add(taskLabel);
                    taskLabel.setPreferredSize(new Dimension(280,30));
                    taskLabel.setFont(new Font("Arial", Font.BOLD, 20));
                    TaskPanel taskPanel = new TaskPanel(task);
                    taskLabel.setBackground(Color.red);
                    eastPanelManager.addPanel(taskPanel, task.getName() + " Task Panel");
                    taskLabel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            beastmode.findResponsibility(task.getResponsibility()).getTasks().remove(task);
//                            task.getResponsibility().getTasks().remove(task);
                            initPanels();
//                            eastPanelManager.show(task.getResponsibility().getName() + " View Panel");
                            eastPanelManager.show(beastmode.findResponsibility(task.getResponsibility()).getName() + " View Panel");
                        }
                    });
                }
            }


            private void initHoursLabel() {
                JLabel hoursLabel = new JLabel("Hours: " + responsibility.getHours() + "      Tasks completed: " + responsibility.getNumCompletedTasks() + " / " + responsibility.getNumTasks());
                hoursLabel.setBackground(Color.cyan);
                hoursLabel.setFont(new Font("Arial", Font.BOLD, 12));
                hoursLabel.setPreferredSize(new Dimension(276, 35));
                hoursLabel.setBackground(Color.cyan);
                hoursLabel.setForeground(Color.black);
                add(hoursLabel);
            }

            private void initResponsibilityLabel() {
                JTextField responsibilityLabel = new JTextField(responsibility.getName());
                responsibilityLabel.setBackground(Color.cyan);
                responsibilityLabel.setFont(new Font("Arial", Font.BOLD, 20));
                responsibilityLabel.setPreferredSize(new Dimension(280, 50));
                responsibilityLabel.setBackground(Color.cyan);
                responsibilityLabel.setForeground(Color.black);
                responsibilityLabel.setEditable(false);
                responsibilityLabel.setHorizontalAlignment(JTextField.CENTER);
                add(responsibilityLabel);
            }


        }
    }

    private class ResponsibilityAddTaskPanel extends JPanel {
        private Responsibility responsibility;
        private JTextField text;
        private JCheckBox checkBox;

        public ResponsibilityAddTaskPanel(Responsibility next) {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.white);
            this.responsibility = next;
            initResponsibilityLabel();
            initTextField1();
            initRepeatable();
            initConfirm();
            initBackButton();
        }

        private void initBackButton() {
            JButton confirm = new JButton("Back");
            add(confirm);
            confirm.setPreferredSize(new Dimension(280,40));
            confirm.setFont(new Font("Arial", Font.BOLD, 20));
            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show(responsibility.getName() + " View Panel");
                }
            });
        }

        private void initConfirm() {
            JButton confirm = new JButton("Confirm");
            add(confirm);
            confirm.setPreferredSize(new Dimension(280,40));
            confirm.setFont(new Font("Arial", Font.BOLD, 20));
            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    responsibility.addTask(new Task(text.getText(), 0, checkBox.isSelected()));
                    text.setText("");
                    checkBox.setSelected(false);
                    initPanels();
                    eastPanelManager.show(responsibility.getName() + " View Panel");
                }
            });
        }

        private void initRepeatable() {
            JLabel repeatableLabel = new JLabel("Repeatable");
            add(repeatableLabel);
            checkBox = new JCheckBox();
            checkBox.setPreferredSize(new Dimension(100, 30));
            checkBox.setFont(new Font("Arial", Font.ITALIC, 16));
            add(checkBox);
        }

        private void initTextField1() {
            add(new JLabel("Enter Task Name"));
            text = new JTextField(20);
            text.setPreferredSize(new Dimension(100, 30));
            text.setFont(new Font("Arial", Font.ITALIC, 16));
            add(text);
        }

        private void initResponsibilityLabel() {
            JTextField responsibilityLabel = new JTextField(responsibility.getName());
            responsibilityLabel.setBackground(Color.cyan);
            responsibilityLabel.setFont(new Font("Arial", Font.BOLD, 20));
            responsibilityLabel.setPreferredSize(new Dimension(280, 50));
            responsibilityLabel.setForeground(Color.black);
            responsibilityLabel.setEditable(false);
            responsibilityLabel.setHorizontalAlignment(JTextField.CENTER);
            add(responsibilityLabel);

        }

    }
}
