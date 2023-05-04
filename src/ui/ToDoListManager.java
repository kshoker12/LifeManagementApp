package ui;

import Model.App;
import Model.Responsibility;
import Model.SubTask;
import Model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ToDoListManager {
    private EastPanelManager eastPanelManager;
    private App beastMode;
    private WestPanelManager westPanelManager;


    public ToDoListManager(EastPanelManager eastPanelManager, App beastmode, WestPanelManager westPanelManager) {
        this.eastPanelManager = eastPanelManager;
        this.beastMode = beastmode;
        this.westPanelManager = westPanelManager;
        initPanels0();

    }


    public void initPanels0() {
        initMainToDoListPanel();
        initChooseResponsibilityPanel();
        initChooseTaskPanel();
        initRemoveTaskPanel();
    }

    public void initPanels() {
        initMainToDoListPanel();
        initChooseResponsibilityPanel();
        initChooseTaskPanel();
        initRemoveTaskPanel();
        westPanelManager.initStartHourPanel();
    }

    private void initRemoveTaskPanel() {
        ToDoRemovePanel toDoRemovePanel = new ToDoRemovePanel();
        eastPanelManager.addPanel(toDoRemovePanel, "ToDo Remove Panel");
    }

    private void initChooseTaskPanel() {
        for (Responsibility responsibility: beastMode.getResponsibilities()) {
            ToDoAddTaskPanel toDoAddTaskPanel = new ToDoAddTaskPanel(responsibility);
            eastPanelManager.addPanel(toDoAddTaskPanel, responsibility.getName() + " ToDo Add");
        }
    }

    private void initChooseResponsibilityPanel() {
        ToDoChoosePanel addTaskPanel = new ToDoChoosePanel(beastMode.getResponsibilities());
        eastPanelManager.addPanel(addTaskPanel, "ToDo Choose Panel");
    }

//    public List<Task> getTodoList() {
//        return todoList;
//    }

    private void initMainToDoListPanel() {
        ToDoListPanel toDoListPanel = new ToDoListPanel();
        eastPanelManager.addPanel(toDoListPanel, "ToDo Panel");
    }

    private class ToDoListPanel extends JPanel {

        public ToDoListPanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.white);
            initToDoLabel();
            initCompletedLabel();
            initTasks();
            initAddTask();
            initRemoveTask();
            initReset();
            initBack();
        }

        private void initReset() {
            JButton resetButton = new JButton("Sign Off");
            add(resetButton);
            resetButton.setPreferredSize(new Dimension(280,40));
            resetButton.setFont(new Font("Arial", Font.BOLD, 20));
            resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    beastMode.getHourNum().resetHour();
                    beastMode.getTodoList().clear();
                    initPanels();
                    eastPanelManager.show("ToDo Panel");
                }
            });
        }

        private void initCompletedLabel() {
            int completed = 0;
            for (SubTask task: beastMode.getTodoList()) {
                if (task.getIsCompleted()) {
                    completed+= 1;
                }
            }

            add(new JLabel("Tasks completed: " + completed + " / " + beastMode.getTodoList().size()));
        }

        private void initRemoveTask() {
            JButton removeButton = new JButton("Toggle Remove Task");
            add(removeButton);
            removeButton.setPreferredSize(new Dimension(280,40));
            removeButton.setFont(new Font("Arial", Font.BOLD, 20));
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("ToDo Remove Panel");
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
                    eastPanelManager.show("ToDo Choose Panel");
                }
            });
        }

        private void initTasks() {
            for (SubTask next: beastMode.getTodoList()) {
                JButton taskLabel = new JButton(next.getName());
                add(taskLabel);
                taskLabel.setPreferredSize(new Dimension(280,30));
                taskLabel.setFont(new Font("Arial", Font.BOLD, 20));
                taskLabel.setBackground(Color.green);
                JCheckBox checkBox = new JCheckBox();
                taskLabel.add(checkBox);
                checkBox.setEnabled(false);
                if (next.getIsCompleted()) {
                    checkBox.setSelected(true);
                }
//                ToDoTaskPanel toDoTaskPanel = new ToDoTaskPanel(next);
//                eastPanelManager.addPanel(toDoTaskPanel, next.getName() + " ToDo Panel");
                taskLabel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastPanelManager.show(next.getName() + " ToDo Panel");
                    }
                });
            }
        }

        private void initToDoLabel() {
            JTextField toDoLabel = new JTextField("ToDo List");
            toDoLabel.setBackground(Color.cyan);
            toDoLabel.setFont(new Font("Arial", Font.BOLD, 20));
            toDoLabel.setPreferredSize(new Dimension(280, 50));
            toDoLabel.setForeground(Color.black);
            toDoLabel.setEditable(false);
            toDoLabel.setHorizontalAlignment(JTextField.CENTER);
            add(toDoLabel);
        }

//        private class ToDoTaskPanel extends JPanel {
//            private SubTask task;
//
//            public ToDoTaskPanel(Task next) {
//                super(new GridLayout(10, 1, 10, 10));
//                setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
//                setPreferredSize(new Dimension(300, 600));
//                setBackground(Color.white);
//                this.task = next;
//                initTaskLabel();
//                initDurationLabel();
//                initSubTasks();
//                initBack();
//            }
//
//            private void initSubTasks() {
//                for (SubTask next: task.getSubTasks()) {
//                    JButton nextLabel = new JButton(next.getName());
//                    add(nextLabel);
//                    nextLabel.setBackground(Color.green);
//                    nextLabel.setFont(new Font("Arial", Font.ITALIC, 16));
//                    nextLabel.setPreferredSize(new Dimension(280, 30));
//                    nextLabel.setForeground(Color.black);
//                    JCheckBox checkBox = new JCheckBox();
//                    nextLabel.add(checkBox);
//                    checkBox.setEnabled(false);
//                    if (task.getIsCompleted()) {
//                        checkBox.setSelected(true);
//                    }
////                    nextLabel.setEditable(false);
//                }
//            }
//
//            private void initDurationLabel() {
//                JLabel durationLabel = new JLabel("Duration: " + task.getDuration() + "        Sub-Tasks completed: " + task.getNumCompletedTasks() + " / " + task.getNumTasks());
//                durationLabel.setBackground(Color.cyan);
//                durationLabel.setFont(new Font("Arial", Font.BOLD, 12));
//                durationLabel.setPreferredSize(new Dimension(276, 35));
//                durationLabel.setBackground(Color.cyan);
//                durationLabel.setForeground(Color.black);
//                add(durationLabel);
//            }
//
//            private void initTaskLabel() {
//                JTextField nextLabel = new JTextField(task.getName());
//                add(nextLabel);
//                nextLabel.setBackground(Color.cyan);
//                nextLabel.setFont(new Font("Arial", Font.BOLD, 20));
//                nextLabel.setPreferredSize(new Dimension(280, 50));
//                nextLabel.setForeground(Color.black);
//                nextLabel.setHorizontalAlignment(JTextField.CENTER);
//                nextLabel.setEditable(false);
//            }
//
//            private void initBack() {
//                JButton backButton = new JButton("Back");
//                add(backButton);
//                backButton.setPreferredSize(new Dimension(280,40));
//                backButton.setFont(new Font("Arial", Font.BOLD, 20));
//                backButton.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        eastPanelManager.show("ToDo Panel");
//                    }
//                });
//            }
//        }
    }

    private class ToDoChoosePanel extends JPanel {
        private List<Responsibility> responsibilities;

        public ToDoChoosePanel(List<Responsibility> responsibilities) {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.white);
            this.responsibilities = responsibilities;
            initResponsibilityLabel();
            initResponsibilities();
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
                    eastPanelManager.show("ToDo Panel");
                }
            });
        }

        private void initResponsibilities() {
            for (Responsibility next: responsibilities) {
                JButton nextButton = new JButton(next.getName());
                add(nextButton);
                nextButton.setPreferredSize(new Dimension(280,30));
                nextButton.setFont(new Font("Arial", Font.BOLD, 20));
                nextButton.setBackground(Color.green);
                nextButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastPanelManager.show(next.getName() + " ToDo Add");
                    }
                });
            }

        }

        private void initResponsibilityLabel() {
            JTextField nextLabel = new JTextField("Responsibilities");
            add(nextLabel);
            nextLabel.setBackground(Color.cyan);
            nextLabel.setFont(new Font("Arial", Font.BOLD, 20));
            nextLabel.setPreferredSize(new Dimension(280, 50));
            nextLabel.setForeground(Color.black);
            nextLabel.setEditable(false);
            nextLabel.setHorizontalAlignment(JTextField.CENTER);
        }
    }

    private class ToDoAddTaskPanel extends JPanel {
        private Responsibility responsibility;

        public ToDoAddTaskPanel(Responsibility responsibility) {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.white);
            this.responsibility = responsibility;
            initResponsibilityLabel();
            initTaskButton();
            initBack();
        }

        private void initTaskButton() {
            for (Task task: responsibility.getTasks()) {
                if (!task.getIsCompleted()) {
                    JButton taskLabel = new JButton(task.getName());
                    add(taskLabel);
                    taskLabel.setPreferredSize(new Dimension(280,30));
                    taskLabel.setFont(new Font("Arial", Font.BOLD, 20));
                    taskLabel.setBackground(Color.green);
                    AddSubTaskPanel subTaskPanel = new AddSubTaskPanel(task);
                    eastPanelManager.addPanel(subTaskPanel, task.getName() + " SubTask Panel");
                    taskLabel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            eastPanelManager.show(task.getName() + " SubTask Panel");
                        }
                    });
                } else {
                    JButton taskLabel = new JButton(task.getName());
                    add(taskLabel);
                    taskLabel.setPreferredSize(new Dimension(280,30));
                    taskLabel.setFont(new Font("Arial", Font.BOLD, 20));
                    taskLabel.setBackground(Color.red);
                }
            }
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

        private void initBack() {
            JButton backButton = new JButton("Back");
            add(backButton);
            backButton.setPreferredSize(new Dimension(280,40));
            backButton.setFont(new Font("Arial", Font.BOLD, 20));
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("ToDo Choose Panel");
                }
            });
        }

        private class AddSubTaskPanel extends JPanel {
            private Task task;

            public AddSubTaskPanel(Task task) {
                super(new GridLayout(10, 1, 10, 10));
                setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                setPreferredSize(new Dimension(300, 600));
                setBackground(Color.white);
                this.task = task;
                initTaskLabel();
                initSubTasks();
                initBackLabel();
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
                        eastPanelManager.show(eastPanelManager.getLifeRatingApp().getApp().findResponsibility(task.getResponsibility()).getName() + " ToDo Add");
                    }
                });
            }

            private void initSubTasks() {
                for (SubTask next: task.getSubTasks()) {
                    if (!next.getIsCompleted()) {
                        JButton nextLabel = new JButton(next.getName());
                        add(nextLabel);
                        nextLabel.setBackground(Color.green);
                        nextLabel.setFont(new Font("Arial", Font.ITALIC, 16));
                        nextLabel.setPreferredSize(new Dimension(280, 30));
                        nextLabel.setForeground(Color.black);
                        nextLabel.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                beastMode.addTask(next);
                                initPanels();
                                eastPanelManager.show("ToDo Panel");
                            }
                        });
                    } else {
                        JButton nextLabel = new JButton(next.getName());
                        add(nextLabel);
                        nextLabel.setBackground(Color.red);
                        nextLabel.setFont(new Font("Arial", Font.ITALIC, 16));
                        nextLabel.setPreferredSize(new Dimension(280, 30));
                        nextLabel.setForeground(Color.black);
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
        }
    }

    private class ToDoRemovePanel extends JPanel {

        public ToDoRemovePanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.white);
            initToDoLabel();
            initCompletedLabel();
            initTasks();
            initAddTask();
            initRemoveTask();
            initReset();
            initBack();
        }

        private void initReset() {
            JButton resetButton = new JButton("Sign Off");
            add(resetButton);
            resetButton.setPreferredSize(new Dimension(280,40));
            resetButton.setFont(new Font("Arial", Font.BOLD, 20));
            resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    beastMode.getHourNum().resetHour();
                    beastMode.getTodoList().clear();
                    initPanels();
                    eastPanelManager.show("ToDo Panel");
                }
            });
        }

        private void initCompletedLabel() {
            int completed = 0;
            for (SubTask task: beastMode.getTodoList()) {
                if (task.getIsCompleted()) {
                    completed+= 1;
                }
            }

            add(new JLabel("Tasks completed: " + completed + " / " + beastMode.getTodoList().size()));
        }

        private void initRemoveTask() {
            JButton removeButton = new JButton("Toggle Remove Task");
            add(removeButton);
            removeButton.setPreferredSize(new Dimension(280,40));
            removeButton.setFont(new Font("Arial", Font.BOLD, 20));
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("ToDo Panel");
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
                    eastPanelManager.show("ToDo Choose Panel");
                }
            });
        }

        private void initTasks() {
            for (SubTask next: beastMode.getTodoList()) {
                JButton taskLabel = new JButton(next.getName());
                add(taskLabel);
                taskLabel.setPreferredSize(new Dimension(280,30));
                taskLabel.setFont(new Font("Arial", Font.BOLD, 20));
                taskLabel.setBackground(Color.red);
                taskLabel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        beastMode.removeTask(next);
                        initPanels();
                        eastPanelManager.show("ToDo Panel");
                    }
                });
            }
        }

        private void initToDoLabel() {
            JTextField toDoLabel = new JTextField("ToDo List");
            toDoLabel.setBackground(Color.cyan);
            toDoLabel.setFont(new Font("Arial", Font.BOLD, 20));
            toDoLabel.setPreferredSize(new Dimension(280, 50));
            toDoLabel.setForeground(Color.black);
            toDoLabel.setEditable(false);
            toDoLabel.setHorizontalAlignment(JTextField.CENTER);
            add(toDoLabel);
        }
    }
}
