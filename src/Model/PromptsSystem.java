package Model;

import Persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PromptsSystem implements Writable {
    private String name;
    private List<Log> logList;
    private List<String> promptQuestions;
    private WeeklyPrompt weeklyPrompt;

    public PromptsSystem(String name) {
        this.name = name;
        this.weeklyPrompt = new WeeklyPrompt();
        promptQuestions = new ArrayList<>();
        logList = new ArrayList<>();
    }

    public List<String> getPromptQuestions() {
        return promptQuestions;
    }

    public List<Log> getLogList() {
        return logList;
    }

    public String getName() {
        return name;
    }

    public Log searchLog(String day) {
        Log log = null;
        for (Log next: logList) {
            if (next.getDay().equals(day)) {
                log = next;
                break;
            }
        }
        return log;
    }

    public void addLog(Log log) {
        logList.add(log);
    }

    public WeeklyPrompt getWeeklyPrompt() {
        return weeklyPrompt;
    }

    public void addPromptQuestion(String question) {
        this.promptQuestions.add(question);
        for (Log log: logList) {
            log.initPromptQuestions(promptQuestions);
        }
    }

    public Log getFirstIncomplete() {
        Log returnable = null;
        for (Log next: logList) {
            if (!next.getComplete()) {
                returnable = next;
                break;
            }
        }
        if (returnable == null) {
            resetPrompts();
            returnable = logList.get(0);
        }
        return returnable;
    }


    public void initDays() {
        logList.add(new Log("Monday", promptQuestions));
        logList.add(new Log("Tuesday", promptQuestions));
        logList.add(new Log("Wednesday", promptQuestions));
        logList.add(new Log("Thursday", promptQuestions));
        logList.add(new Log("Friday", promptQuestions));
        logList.add(new Log("Saturday", promptQuestions));
        logList.add(new Log("Sunday", promptQuestions));
    }

    public void setWeeklyPrompt(WeeklyPrompt weeklyPrompt) {
        this.weeklyPrompt = weeklyPrompt;
    }

    public void resetPrompts() {
        weeklyPrompt.reset();
        for (Log next: logList) {
            next.reset();
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("weeklyPrompt", weeklyPrompt.toJson());
        json.put("promptQuestions", promptQuestionsToJson());
        json.put("log", logListToJson());
        return json;
    }

    private JSONArray logListToJson() {
        JSONArray array = new JSONArray();
        for (Log log: logList) {
            array.put(log.toJson());
        }
        return array;
    }

    private JSONArray promptQuestionsToJson() {
        JSONArray array = new JSONArray();
        for (String questions: promptQuestions) {
            array.put(questions);
        }
        return array;
    }
}
