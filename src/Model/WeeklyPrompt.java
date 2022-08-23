package Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeeklyPrompt {
    private List<Prompt> promptList;
    private int currId;


    public WeeklyPrompt() {
        promptList = new ArrayList<>();
        currId = 1;
    }

    public void addPrompt(String question) {
        this.promptList.add(new Prompt(question, "Week", currId));
        currId+=1;
    }

    public void reset() {
        for (Prompt next: promptList) {
            next.reset();
        }
    }

    public List<Prompt> getPromptList() {
        return promptList;
    }

    public void setCurrId(int currId) {
        this.currId = currId;
    }

    public void setPromptList(List<Prompt> promptList) {
        this.promptList = promptList;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("currId", currId);
        jsonObject.put("promptList", promptListToJson());
        return jsonObject;
    }

    private JSONArray promptListToJson() {
        JSONArray array = new JSONArray();
        for (Prompt prompt: promptList) {
            array.put(prompt.toJson());
        }
        return array;
    }
}
