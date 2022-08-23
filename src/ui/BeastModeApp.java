package ui;

import Model.*;
import Persistence.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BeastModeApp extends JFrame {
    private RatingSystem ratingSystem;
    private EastPanelManager eastPanelManager;
    private WestPanelManager westPanelManager;
    private PromptsSystem promptsSystem;
    private static final String JSON_STORE = "./Data/ratings.json";
    private static final String JSON_STORE_2 = "./Data/beastmode.json";
    private static final String JSON_STORE_3 = "./Data/prompts.json";
    private static final String JSON_STORE_4 = "./Data/traits.json";
    private static final String JSON_STORE_5 = "./Data/compass.json";
    private static final String JSON_STORE_6 = "./Data/solutions.json";
    private CompassReader compassReader;
    private CompassWriter compassWriter;
    private RatingsWriter jsonWriter;
    private RatingsReader jsonReader;
    private PromptsReader promptsReader;
    private PromptsWriter promptsWriter;
    private TraitReader traitReader;
    private TraitWriter traitWriter;
    private SolutionsReader solutionsReader;
    private SolutionsWriter solutionsWriter;
    private Beastmode beastmode;
    private BeastmodeWriter jsonWriter2;
    private BeastmodeReader jsonReader2;
    private PromptsManager promptsManager;
    private TraitManager traitManager;
    private WeeklyCompass weeklyCompass;
    private SelfImage selfImage;
    private WeeklyCompassManager weeklyCompassManager;
    private Solutions solutions;
    private SolutionsManager solutionsManager;

    public BeastModeApp() {
        super("Beastmode");
        ratingSystem = new RatingSystem("Karn's Ratings");
        jsonReader = new RatingsReader(JSON_STORE);
        jsonWriter = new RatingsWriter(JSON_STORE);
        jsonWriter2 = new BeastmodeWriter(JSON_STORE_2);
        jsonReader2 = new BeastmodeReader(JSON_STORE_2);
        promptsReader = new PromptsReader(JSON_STORE_3);
        promptsWriter = new PromptsWriter(JSON_STORE_3);
        traitReader = new TraitReader(JSON_STORE_4);
        traitWriter = new TraitWriter(JSON_STORE_4);
        compassReader = new CompassReader(JSON_STORE_5);
        compassWriter = new CompassWriter(JSON_STORE_5);
        solutionsReader = new SolutionsReader(JSON_STORE_6);
        solutionsWriter = new SolutionsWriter(JSON_STORE_6);
        this.beastmode = loadRoutine1();
        this.promptsSystem = loadRoutine3();
        this.selfImage = loadSelfImage();
        this.solutions = loadSolutions();
        initAttributes();
        ratingSystem = loadRoutine();
        weeklyCompass = loadCompass();
        setupPanel();
        eastPanelManager = new EastPanelManager(ratingSystem, this);
        westPanelManager = new WestPanelManager(this, eastPanelManager, beastmode);
        initPromptManager();
        initTraitManager();
        JScrollPane scrollPane = new JScrollPane(eastPanelManager.getEastPanel());
        add(BorderLayout.EAST, scrollPane);
        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setPreferredSize(new Dimension(8, 600));
        scrollPane.setVerticalScrollBar(scrollBar);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        weeklyCompassManager = new WeeklyCompassManager(this, eastPanelManager, westPanelManager, weeklyCompass);
        solutionsManager = new SolutionsManager(solutions, eastPanelManager);
    }

    private void initTraitManager() {
        traitManager = new TraitManager(eastPanelManager, westPanelManager, selfImage);
    }

    private void initPromptManager() {
        promptsManager = new PromptsManager(promptsSystem, eastPanelManager, westPanelManager);
    }

    public Beastmode getBeastmode() {
        return beastmode;
    }

    private RatingSystem loadRoutine() {
        RatingSystem ratingSystem = null;
        try {
            ratingSystem = jsonReader.read();
            System.out.println("Loaded " + ratingSystem.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        return ratingSystem;
    }

    private SelfImage loadSelfImage() {
        SelfImage selfImage = null;
        try {
            selfImage = traitReader.read();
            System.out.println("Loaded " + selfImage.getName() + " from " + JSON_STORE_4);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_4);
        }
        return selfImage;
    }

    private PromptsSystem loadRoutine3() {
        PromptsSystem promptsSystem = null;
        try {
            promptsSystem = promptsReader.read();
            System.out.println("Loaded " + promptsSystem.getName() + " from " + JSON_STORE_3);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_3);
        }
        return promptsSystem;
    }

    private Solutions loadSolutions() {
        Solutions solutions = null;
        try {
            solutions = solutionsReader.read();
            System.out.println("Loaded " + solutions.getName() + " from " + JSON_STORE_6);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        return solutions;
    }

    private Beastmode loadRoutine1() {
        Beastmode beastmode = null;
        try {
            beastmode = jsonReader2.read();
            System.out.println("Loaded " + beastmode.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        return beastmode;
    }

    private WeeklyCompass loadCompass() {
        WeeklyCompass weeklyCompass = null;
        try {
            weeklyCompass = compassReader.read();
            System.out.println("Loaded " + weeklyCompass.getName() + " from " + JSON_STORE_5);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        return weeklyCompass;
    }

    public void saveRoutine() {
        try {
            jsonWriter.open();
            jsonWriter.write(ratingSystem);
            jsonWriter.close();
            System.out.println("Saved " + ratingSystem.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    public void saveSolutions() {
        try {
            solutionsWriter.open();
            solutionsWriter.write(solutions);
            solutionsWriter.close();
            System.out.println("Saved " + solutions.getName() + " to " + JSON_STORE_6);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    public void saveCompass() {
        try {
            compassWriter.open();
            compassWriter.write(weeklyCompass);
            compassWriter.close();
            System.out.println("Saved " + weeklyCompass.getName() + " to " + JSON_STORE_5);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    public void saveSelfImage() {
        try {
            traitWriter.open();
            traitWriter.write(selfImage);
            traitWriter.close();
            System.out.println("Saved " + selfImage.getName() + " to " + JSON_STORE_4);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_4);
        }
    }

    public void saveRoutine1() {
        try {
            jsonWriter2.open();
            jsonWriter2.write(beastmode);
            jsonWriter2.close();
            System.out.println("Saved " + beastmode.getName() + " to " + JSON_STORE_2);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_2);
        }
    }

    public void saveRoutine2() {
        try {
            promptsWriter.open();
            promptsWriter.write(promptsSystem);
            promptsWriter.close();
            System.out.println("Saved " + promptsSystem.getName() + " to " + JSON_STORE_3);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_3);
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

    private void initAttributes() {
        initMentalToughness();
        initMindset();
        initWorkEthic();
        initSocialSkills();
    }


    private void initSocialSkills() {
        Category socialSkills = new Category("Social Skills");
        socialSkills.addAttribute(new Attribute("Honesty", 37.8, socialSkills.getName()));
        socialSkills.addAttribute(new Attribute("Kindness", 38.2, socialSkills.getName()));
        socialSkills.addAttribute(new Attribute("Respect", 39.7, socialSkills.getName()));
        socialSkills.addAttribute(new Attribute("Charisma", 39.6, socialSkills.getName()));
        socialSkills.addAttribute(new Attribute("Humour", 40.1, socialSkills.getName()));
        socialSkills.addAttribute(new Attribute("Listening", 38.4, socialSkills.getName()));
        socialSkills.addAttribute(new Attribute("Game", 0, socialSkills.getName()));
        socialSkills.addAttribute(new Attribute("Leadership", 38.2, socialSkills.getName()));
        socialSkills.addAttribute(new Attribute("Social Interactions", 38.3, socialSkills.getName()));
        socialSkills.addAttribute(new Attribute("Influence", 39.9, socialSkills.getName()));
        ratingSystem.addCategory(socialSkills);
    }

    private void initWorkEthic() {
        Category workEthic = new Category("Work Ethic");
        workEthic.addAttribute(new Attribute("Productivity", 38.2, workEthic.getName()));
        workEthic.addAttribute(new Attribute("Academic Skills", 37.4, workEthic.getName()));
        workEthic.addAttribute(new Attribute("Eating", 38.1, workEthic.getName()));
        workEthic.addAttribute(new Attribute("Integrity", 38.3, workEthic.getName()));
        workEthic.addAttribute(new Attribute("Training", 0, workEthic.getName()));
        workEthic.addAttribute(new Attribute("Discpline", 38.3, workEthic.getName()));
        workEthic.addAttribute(new Attribute("Meditation", 24.1, workEthic.getName()));
        workEthic.addAttribute(new Attribute("Values", 28.6, workEthic.getName()));
        workEthic.addAttribute(new Attribute("Hustle", 37.8, workEthic.getName()));
        ratingSystem.addCategory(workEthic);
    }

    private void initMindset() {
        Category mindset = new Category("Mindset");
        mindset.addAttribute(new Attribute("Focus", 38.1, mindset.getName()));
        mindset.addAttribute(new Attribute("Positive Thoughts", 40.2, mindset.getName()));
        mindset.addAttribute(new Attribute("Motivation", 39.3, mindset.getName()));
        mindset.addAttribute(new Attribute("Decision Making", 37.9, mindset.getName()));
        mindset.addAttribute(new Attribute("Confidence", 39.6, mindset.getName()));
        mindset.addAttribute(new Attribute("Self Talk", 39.5, mindset.getName()));
        mindset.addAttribute(new Attribute("Loyalty", 39.0, mindset.getName()));
        ratingSystem.addCategory(mindset);
    }

    private void initMentalToughness() {
        Category mentalToughness = new Category("Mental Toughness");
        mentalToughness.addAttribute(new Attribute("Determination", 40.2, mentalToughness.getName()));
        mentalToughness.addAttribute(new Attribute("Fearlessness", 40.1, mentalToughness.getName()));
        mentalToughness.addAttribute(new Attribute("Courage", 40.3, mentalToughness.getName()));
        mentalToughness.addAttribute(new Attribute("Emotional Intelligence", 37.6, mentalToughness.getName()));
        ratingSystem.addCategory(mentalToughness);
    }
}
