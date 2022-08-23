package Persistence;

import Model.Destination;
import Model.WeeklyCompass;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CompassReader {
    private String source;

    public CompassReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WeeklyCompass read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWeeklyCompass(jsonObject);
    }

    private WeeklyCompass parseWeeklyCompass(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        WeeklyCompass weeklyCompass = new WeeklyCompass(name);
        weeklyCompass.setTotalPoints(jsonObject.getInt("totalPoints"));
        weeklyCompass.setEarnedPoints(jsonObject.getInt("earnedPoints"));
        weeklyCompass.setPercentage(jsonObject.getInt("percentage"));
        addDestinations(weeklyCompass, jsonObject);
        return weeklyCompass;
    }

    private void addDestinations(WeeklyCompass weeklyCompass, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("destinationList");
        for (Object obj: jsonArray) {
            JSONObject next = (JSONObject) obj;
            Destination destination = addDestination(next);
            weeklyCompass.addDestination(destination);
        }
    }

    private Destination addDestination(JSONObject next) {
        Destination destination = new Destination(next.getInt("point"));
        destination.setName(next.getString("name"));
        destination.setFinish(next.getBoolean("finish"));
        destination.setEarned(next.getInt("earned"), "s");
        return destination;
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
