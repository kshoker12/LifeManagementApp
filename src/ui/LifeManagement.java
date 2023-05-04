package ui;

import Model.*;
import Persistence.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LifeManagement extends JFrame {
    private static final String Ratings_JSON = "./Data/ratings.json";
    private static final String App_JSON = "./Data/beastmode.json";
    private static final String Prompts_JSON = "./Data/prompts.json";
    private static final String Compass_JSON = "./Data/compass.json";
    private static final String Solutions_JSON = "./Data/solutions.json";
    private RatingSystem ratingSystem;
    private EastPanelManager eastPanelManager;
    private WestPanelManager westPanelManager;
    private PromptsSystem promptsSystem;
    private CompassReader compassReader;
    private CompassWriter compassWriter;
    private RatingsWriter jsonWriter;
    private RatingsReader jsonReader;
    private PromptsReader promptsReader;
    private PromptsWriter promptsWriter;
    private SolutionsReader solutionsReader;
    private SolutionsWriter solutionsWriter;
    private App app;
    private AppWriter jsonWriter2;
    private AppReader jsonReader2;
    private PromptsManager promptsManager;
    private WeeklyCompass weeklyCompass;
    private WeeklyCompassManager weeklyCompassManager;
    private Solutions solutions;
    private SolutionsManager solutionsManager;

    public LifeManagement() {
        super("Life Management App");
        ratingSystem = new RatingSystem("Karn's Ratings");
        jsonReader = new RatingsReader(Ratings_JSON);
        jsonWriter = new RatingsWriter(Ratings_JSON);
        jsonWriter2 = new AppWriter(App_JSON);
        jsonReader2 = new AppReader(App_JSON);
        promptsReader = new PromptsReader(Prompts_JSON);
        promptsWriter = new PromptsWriter(Prompts_JSON);
        compassReader = new CompassReader(Compass_JSON);
        compassWriter = new CompassWriter(Compass_JSON);
        solutionsReader = new SolutionsReader(Solutions_JSON);
        solutionsWriter = new SolutionsWriter(Solutions_JSON);
        this.app = loadApp();
        this.promptsSystem = loadPromptsSystem();
        this.solutions = loadSolutions();
        ratingSystem = loadRatingSystem();
        weeklyCompass = loadCompass();
        setupPanel();
        eastPanelManager = new EastPanelManager(ratingSystem, this);
        westPanelManager = new WestPanelManager(this, eastPanelManager, app);
        initPromptManager();
        JScrollPane scrollPane = new JScrollPane(eastPanelManager.getEastPanel());
        add(BorderLayout.EAST, scrollPane);
        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setPreferredSize(new Dimension(8, 600));
        scrollPane.setVerticalScrollBar(scrollBar);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        weeklyCompassManager = new WeeklyCompassManager(this, eastPanelManager, westPanelManager, weeklyCompass);
        solutionsManager = new SolutionsManager(solutions, eastPanelManager);
    }

    private void initPromptManager() {
        promptsManager = new PromptsManager(promptsSystem, eastPanelManager, westPanelManager);
    }

    public App getApp() {
        return app;
    }

    private RatingSystem loadRatingSystem() {
        RatingSystem ratingSystem = null;
        try {
            ratingSystem = jsonReader.read();
            System.out.println("Loaded " + ratingSystem.getName() + " from " + Ratings_JSON);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + Ratings_JSON);
        }
        return ratingSystem;
    }



    private PromptsSystem loadPromptsSystem() {
        PromptsSystem promptsSystem = null;
        try {
            promptsSystem = promptsReader.read();
            System.out.println("Loaded " + promptsSystem.getName() + " from " + Prompts_JSON);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + Prompts_JSON);
        }
        return promptsSystem;
    }

    private Solutions loadSolutions() {
        Solutions solutions = null;
        try {
            solutions = solutionsReader.read();
            System.out.println("Loaded " + solutions.getName() + " from " + Solutions_JSON);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + Ratings_JSON);
        }
        return solutions;
    }

    private App loadApp() {
        App app = null;
        try {
            app = jsonReader2.read();
            System.out.println("Loaded " + app.getName() + " from " + Ratings_JSON);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + Ratings_JSON);
        }
        return app;
    }

    private WeeklyCompass loadCompass() {
        WeeklyCompass weeklyCompass = null;
        try {
            weeklyCompass = compassReader.read();
            System.out.println("Loaded " + weeklyCompass.getName() + " from " + Compass_JSON);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + Ratings_JSON);
        }
        return weeklyCompass;
    }

    public void saveRatingSystem() {
        try {
            jsonWriter.open();
            jsonWriter.write(ratingSystem);
            jsonWriter.close();
            System.out.println("Saved " + ratingSystem.getName() + " to " + Ratings_JSON);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + Ratings_JSON);
        }
    }

    public void saveSolutions() {
        try {
            solutionsWriter.open();
            solutionsWriter.write(solutions);
            solutionsWriter.close();
            System.out.println("Saved " + solutions.getName() + " to " + Solutions_JSON);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + Ratings_JSON);
        }
    }

    public void saveCompass() {
        try {
            compassWriter.open();
            compassWriter.write(weeklyCompass);
            compassWriter.close();
            System.out.println("Saved " + weeklyCompass.getName() + " to " + Compass_JSON);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + Ratings_JSON);
        }
    }

    public void saveApp() {
        try {
            jsonWriter2.open();
            jsonWriter2.write(app);
            jsonWriter2.close();
            System.out.println("Saved " + app.getName() + " to " + App_JSON);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + App_JSON);
        }
    }

    public void savePromptsSystem() {
        try {
            promptsWriter.open();
            promptsWriter.write(promptsSystem);
            promptsWriter.close();
            System.out.println("Saved " + promptsSystem.getName() + " to " + Prompts_JSON);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + Prompts_JSON);
        }
    }


    // EFFECTS: sets up the panels configurations
    private void setupPanel() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(615, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setLayout(new BorderLayout(10, 10));
    }

    @Override
    //EFFECTS: prints out the event logs
    public void dispose() {
        super.dispose();
    }
}
