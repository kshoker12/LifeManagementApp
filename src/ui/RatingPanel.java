package ui;

import Model.Attribute;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class RatingPanel extends JPanel {
    private Attribute attribute;
    private JTextField monday;
    private JTextField tuesday;
    private JTextField wednesday;
    private JTextField thursday;
    private JTextField friday;
    private JTextField saturday;
    private JTextField sunday;
    private JTextField rating;
    private EastPanelManager panelManager;

    public RatingPanel(Attribute attribute, EastPanelManager panelManager) {
        super(new GridLayout(8, 1, 10, 10));
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        setPreferredSize(new Dimension(300, 600));
        setBackground(Color.YELLOW);
        this.attribute = attribute;
        this.panelManager = panelManager;
        initLabels();
        initButton();
    }

    private void initButton() {
        JButton update = new JButton("Back");
        add(update);
        update.setPreferredSize(new Dimension(280,40));
        update.setFont(new Font("Arial", Font.BOLD, 20));
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.show(attribute.getCategory() + " Category Panel");
            }
        });
    }

    private void initLabels() {
        initRating();
        initMonday();
        initTuesday();
        initWednesday();
        initThursday();
        initFriday();
        initSaturday();
        initSunday();
    }

    private void initRating() {
        DecimalFormat numberFormat = new DecimalFormat("#.0");
        rating = new JTextField(attribute.getName() + ": " + numberFormat.format(attribute.getRating()));
        rating.setBackground(Color.cyan);
        rating.setFont(new Font("Arial", Font.BOLD, 16));
        rating.setPreferredSize(new Dimension(280, 50));
        rating.setBackground(Color.cyan);
        rating.setForeground(Color.black);
        rating.setEditable(false);
        add(rating);
    }

    private void initSunday() {
        sunday = new JTextField("Sunday: " + attribute.obtainRating(6));
        sunday.setBackground(Color.green);
        sunday.setFont(new Font("Arial", Font.ITALIC, 16));
        sunday.setPreferredSize(new Dimension(280, 30));
        sunday.setForeground(Color.black);
        sunday.setEditable(false);
        add(sunday);
    }

    private void initSaturday() {
        saturday = new JTextField("Saturday: " + attribute.obtainRating(5));
        saturday.setBackground(Color.green);
        saturday.setFont(new Font("Arial", Font.ITALIC, 16));
        saturday.setPreferredSize(new Dimension(280, 30));
        saturday.setForeground(Color.black);
        saturday.setEditable(false);
        add(saturday);
    }

    private void initFriday() {
        friday = new JTextField("Friday: " + attribute.obtainRating(4));
        friday.setBackground(Color.green);
        friday.setFont(new Font("Arial", Font.ITALIC, 16));
        friday.setPreferredSize(new Dimension(280, 30));
        friday.setForeground(Color.black);
        friday.setEditable(false);
        add(friday);
    }

    private void initThursday() {
        thursday = new JTextField("Thursday: " + attribute.obtainRating(3));
        thursday.setBackground(Color.green);
        thursday.setFont(new Font("Arial", Font.ITALIC, 16));
        thursday.setPreferredSize(new Dimension(280, 30));
        thursday.setForeground(Color.black);
        thursday.setEditable(false);
        add(thursday);
    }

    private void initWednesday() {
        wednesday = new JTextField("Wednesday: " + attribute.obtainRating(2));
        wednesday.setBackground(Color.green);
        wednesday.setFont(new Font("Arial", Font.ITALIC, 16));
        wednesday.setPreferredSize(new Dimension(280, 30));
        wednesday.setForeground(Color.black);
        wednesday.setEditable(false);
        add(wednesday);
    }

    private void initTuesday() {
        tuesday = new JTextField("Tuesday: " + attribute.obtainRating(1));
        tuesday.setBackground(Color.green);
        tuesday.setFont(new Font("Arial", Font.ITALIC, 16));
        tuesday.setPreferredSize(new Dimension(280, 30));
        tuesday.setForeground(Color.black);
        tuesday.setEditable(false);
        add(tuesday);
    }

    private void initMonday() {
        monday = new JTextField("Monday: " + attribute.obtainRating(0));
        monday.setBackground(Color.green);
        monday.setFont(new Font("Arial", Font.ITALIC, 16));
        monday.setPreferredSize(new Dimension(280, 30));
        monday.setForeground(Color.black);
        monday.setEditable(false);
        add(monday);
    }
}
