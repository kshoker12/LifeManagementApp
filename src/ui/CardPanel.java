package ui;

import Model.Category;
import Model.RatingSystem;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class CardPanel extends JPanel {
    private RatingSystem ratingSystem;
    private EastPanelManager panelManager;
    
    public CardPanel(RatingSystem ratingSystem, EastPanelManager panelManager) {
        super(new GridLayout(10, 1, 10, 10));
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        setPreferredSize(new Dimension(300, 600));
        setBackground(Color.black);
        this.ratingSystem = ratingSystem;
        this.panelManager = panelManager;
        initOverallRatingLabel();
        initImage();
        initRatingLabels();
    }

    private void initOverallRatingLabel() {
        DecimalFormat numberFormat = new DecimalFormat("#.0");
        JTextField rating = new JTextField(ratingSystem.getName() + ": " + numberFormat.format(ratingSystem.getRating()));
        rating.setBackground(Color.cyan);
        rating.setFont(new Font("Arial", Font.BOLD, 16));
        rating.setPreferredSize(new Dimension(276, 50));
        rating.setBackground(Color.cyan);
        rating.setForeground(Color.black);
        rating.setEditable(false);
        add(rating);
    }


    private void initRatingLabels() {
        for (Category next: ratingSystem.getCategories()) {
            initRatingLabel(next);
        }
    }

    private void initRatingLabel(Category next) {
        DecimalFormat numberFormat = new DecimalFormat("#.0");
        JTextField rating = new JTextField(next.getName() + ": " + numberFormat.format(next.getRating()));
        rating.setBackground(Color.cyan);
        rating.setFont(new Font("Arial", Font.BOLD, 16));
        rating.setPreferredSize(new Dimension(276, 35));
        rating.setBackground(Color.cyan);
        rating.setForeground(Color.black);
        rating.setEditable(false);
        add(rating);
    }

    private void initImage() {
        ImageIcon image = new ImageIcon("./Data/selfpic.jpg");
        JLabel imagePanel = new JLabel();
        imagePanel.setIcon(image);
        imagePanel.setIconTextGap(10);
        add(imagePanel);
    }


}
