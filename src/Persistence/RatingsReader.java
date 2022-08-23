package Persistence;


import Model.Attribute;
import Model.Category;
import Model.RatingSystem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Created using the help of
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//By Paul Carter

//Represents a class which is used to read console data from a Json file
public class RatingsReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public RatingsReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public RatingSystem read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRatings(jsonObject);
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private RatingSystem parseRatings(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        RatingSystem r = new RatingSystem(name);
        Double rating = jsonObject.getDouble("rating");
        r.setRating(rating);
        addCategories(r, jsonObject);
        return r;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addCategories(RatingSystem r, JSONObject jsonObject) {
        JSONArray categories = jsonObject.getJSONArray("categories");
        for (Object w : categories) {
            JSONObject nextWorkout = (JSONObject) w;
            Category wr = addCategory(nextWorkout);
            r.addCategory(wr);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private Category addCategory(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Double rating = jsonObject.getDouble("rating");
        JSONArray attributes = jsonObject.getJSONArray("attributes");
        Category category = new Category(name);
        category.setRating(rating);
        for (Object e: attributes) {
            JSONObject nextExercise = (JSONObject) e;
            Attribute s = readExercise(nextExercise);
            category.getAttributes().add(s);
        }
        return category;
    }

    // EFFECTS: reads the exercise
    private Attribute readExercise(JSONObject e) {
        String name = e.getString("name");
        double ratings = e.getDouble("rating");
        String category = e.getString("category");
        Attribute attribute = new Attribute(name, ratings, category);
        JSONArray weeklyRatings = e.getJSONArray("weeklyRatings");
        for (Object i: weeklyRatings) {
            attribute.addRating((Integer)i);
        }
        return attribute;
    }



    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


}
