package ui;

import Model.Attribute;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class UpdatePanel extends JPanel {
    private Attribute attribute;
    private JTextField textField;
    private EastPanelManager panelManager;

    public UpdatePanel(Attribute attribute, EastPanelManager panelManager) {
        super(new GridLayout(14, 1, 10, 10));
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        setPreferredSize(new Dimension(300, 600));
        setBackground(Color.yellow);
        this.attribute = attribute;
        this.panelManager = panelManager;
        initRatingLabel();
        initAttributeLabel();
//        initEntryField();
    }

//    private void initEntryField() {
//        this.add(new JLabel("Enter Daily Rating"));
//        textField = new JTextField(20);
//        textField.setPreferredSize(new Dimension(100, 30));
//        textField.setFont(new Font("Arial", Font.ITALIC, 16));
//        this.add(textField);
//    }

    // MODIFIES: east, managerPanel
    // EFFECTS: creates the confirm button as well as its behaviour
    private void initConfirm(int rating) {
        attribute.addRating(rating);
        panelManager.removeUpdatePanel();
    }
//    // MODIFIES: east, managerPanel
//    // EFFECTS: creates the confirm button as well as its behaviour
//    private void initConfirm() {
//        JButton confirm = new JButton("Confirm");
//        confirm.setPreferredSize(new Dimension(280, 30));
//        this.add(confirm);
//        confirm.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Integer rating = Integer.parseInt(textField.getText());
//                attribute.addRating(rating);
//                textField.setText("");
//                panelManager.removeUpdatePanel();
//            }
//        });
//    }

    // EFFECTS: sets up all the text fields and instruction labels
    private void initTextFields() {

    }

    private void initRatingLabel() {
        DecimalFormat numberFormat = new DecimalFormat("#.0");
        JTextField rating = new JTextField(attribute.getName() + ": " + numberFormat.format(attribute.getRating()));
        rating.setBackground(Color.cyan);
        rating.setFont(new Font("Arial", Font.BOLD, 16));
        rating.setPreferredSize(new Dimension(280, 30));
        rating.setForeground(Color.black);
        rating.setEditable(false);
        add(rating);
    }

    private void initAttributeLabel() {
        initRatingButton("10 - Perfection", 10);
        initRatingButton("9 - Unbelievable", 9);
        initRatingButton("8 - Amazing", 8);
        initRatingButton("7 - Great", 7);
        initRatingButton("6 - Good", 6);
        initRatingButton("5 - Alright", 5);
        initRatingButton("4 - Needs Improvement", 4);
        initRatingButton("3 - Not Good", 3);
        initRatingButton("2 - Bad", 2);
        initRatingButton("1 - Terrible", 1);
        initRatingButton("0 - Non-existent", 0);
    }

    private void initRatingButton(String text, int num) {
        JButton rating = new JButton(text);
        rating.setPreferredSize(new Dimension(280,30));
        rating.setFont(new Font("Arial", Font.BOLD, 20));
        rating.setHorizontalAlignment(JButton.LEFT);
        rating.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initConfirm(num);
            }
        });
        add(rating);
    }
}
