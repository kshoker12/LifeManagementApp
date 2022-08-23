package Persistence;

import Model.Log;
import Model.Prompt;
import Model.PromptsSystem;
import Model.WeeklyPrompt;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PromptsReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public PromptsReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PromptsSystem read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePromptsSystem(jsonObject);
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private PromptsSystem parsePromptsSystem(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        PromptsSystem promptsSystem = new PromptsSystem(name);
        addWeeklyPrompt(promptsSystem, jsonObject);
        addPromptQuestions(promptsSystem, jsonObject);
        addLogList(promptsSystem, jsonObject);
        return promptsSystem;
    }

    private void addPromptQuestions(PromptsSystem promptsSystem, JSONObject jsonObject) {
        JSONArray array = jsonObject.getJSONArray("promptQuestions");
        for (Object obj: array) {
            promptsSystem.addPromptQuestion(obj.toString());
        }
    }

    private void addLogList(PromptsSystem promptsSystem, JSONObject jsonObject) {
        JSONArray array = jsonObject.getJSONArray("log");
        for (Object obj: array) {
            JSONObject next = (JSONObject) obj;
            promptsSystem.addLog(readlog(promptsSystem.getPromptQuestions(), next));
        }
    }

    private Log readlog(List<String> promptQuestions, JSONObject next) {
        String day = next.getString("day");
        Boolean complete = next.getBoolean("complete");
        Log log = new Log(day, promptQuestions);
        log.setComplete(complete);
        return log;
    }

    private void addWeeklyPrompt(PromptsSystem promptsSystem, JSONObject jsonObject) {
        JSONObject weeklyJson = jsonObject.getJSONObject("weeklyPrompt");
        WeeklyPrompt weeklyPrompt = new WeeklyPrompt();
        weeklyPrompt.setCurrId(weeklyJson.getInt("currId"));
        addPrompts(weeklyPrompt, weeklyJson);
        promptsSystem.setWeeklyPrompt(weeklyPrompt);
    }

    private void addPrompts(WeeklyPrompt weeklyPrompt, JSONObject weeklyJson) {
        JSONArray array = weeklyJson.getJSONArray("promptList");
        List<Prompt> promptList = new ArrayList<>();
        for (Object obj: array) {
            JSONObject next = (JSONObject) obj;
            promptList.add(readPrompt(next));
        }
        weeklyPrompt.setPromptList(promptList);
    }

    private Prompt readPrompt(JSONObject next) {
        String question = next.getString("question");
        String day = next.getString("day");
        int id = next.getInt("id");
        Prompt prompt = new Prompt(question, day, id);
        String answer = next.getString("answers");
        prompt.setAnswers(answer);
        Boolean complete = next.getBoolean("complete");
        prompt.setComplete(complete);
        return prompt;
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
