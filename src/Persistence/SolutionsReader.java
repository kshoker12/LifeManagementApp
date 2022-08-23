package Persistence;

import Model.Problem;
import Model.Solutions;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class SolutionsReader {
    private String source;

    public SolutionsReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Solutions read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSolutions(jsonObject);
    }

    private Solutions parseSolutions(JSONObject jsonObject) {
        Solutions solutions = new Solutions(jsonObject.getString("name"));
        readProblems(solutions, jsonObject);
        return solutions;
    }

    private void readProblems(Solutions solutions, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("problemList");
        for (Object obj: jsonArray) {
            JSONObject next = (JSONObject) obj;
            solutions.addProblem(readProblem(next));
        }
    }

    private Problem readProblem(JSONObject next) {
        Problem problem = new Problem(next.getString("problem"));
        problem.setSolution(next.getString("solution"));
        problem.setSolved(next.getBoolean("solved"));
        return problem;
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
