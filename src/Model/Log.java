package Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Log {
    private String day;
    private List<Prompt> promptList;
    private Boolean complete;

    public Log(String day, List<String> promptQuestions) {
        this.day = day;
        this.complete = false;
        initPromptQuestions(promptQuestions);
    }

    public void complete() {
        Boolean completed = true;
        for (Prompt prompt: promptList) {
            if (!prompt.getComplete()) {
                completed = false;
                break;
            }
        }
        complete = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Log)) return false;
        Log log = (Log) o;
        return Objects.equals(day, log.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day);
    }

    public void initPromptQuestions(List<String> promptQuestions) {
        this.promptList = new ArrayList<>();
        int i = 1;
        for (String next: promptQuestions) {
            this.promptList.add(new Prompt(next, day, i));
            i+=1;
        }
    }

    public void reset() {
        this.complete = false;
        for (Prompt next: promptList) {
            next.reset();
        }
    }

    public Boolean getComplete() {
        return complete;
    }

    public List<Prompt> getPromptList() {
        return promptList;
    }

    public String getDay() {
        return day;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", day);
        json.put("promptList", promptListToJson());
        json.put("complete", complete);
        return json;
    }

    private JSONArray promptListToJson() {
        JSONArray array = new JSONArray();
        for (Prompt prompt: promptList) {
            array.put(prompt.toJson());
        }
        return array;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}
