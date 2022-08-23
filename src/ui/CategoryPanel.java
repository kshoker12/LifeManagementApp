package ui;

import Model.Attribute;
import Model.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CategoryPanel extends JPanel {
    private Category category;
    private EastPanelManager panelManager;

    public CategoryPanel(Category category, EastPanelManager panelManager) {
        super(new GridLayout(category.getAttributes().size() + 1, 1, 10, 10));
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        setPreferredSize(new Dimension(300, 600));
        setBackground(Color.yellow);
        this.category = category;
        this.panelManager = panelManager;
        initRating();
        initButtons();
        initButton();
    }

    private void initRating() {
        DecimalFormat numberFormat = new DecimalFormat("#.0");
        JTextField rating = new JTextField(category.getName() + ": " + numberFormat.format(category.getRating()));
        rating.setBackground(Color.cyan);
        rating.setFont(new Font("Arial", Font.BOLD, 16));
        rating.setPreferredSize(new Dimension(280, 50));
        rating.setBackground(Color.cyan);
        rating.setForeground(Color.black);
        rating.setEditable(false);
        add(rating);
    }

    private void initButton() {
        JButton update = new JButton("Back");
        add(update);
        update.setPreferredSize(new Dimension(280,40));
        update.setFont(new Font("Arial", Font.BOLD, 20));
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.show("Card Panel");
            }
        });
    }

    private void initButtons() {
        for (Attribute next: category.getAttributes()) {
            initButton(next);
        }
    }

    private void initButton(Attribute attribute) {
        JButton update = new JButton(attribute.getName());
        add(update);
        update.setPreferredSize(new Dimension(280,40));
        update.setFont(new Font("Arial", Font.BOLD, 20));
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.show(attribute.getName() + " Rating Panel");
            }
        });
    }


}
