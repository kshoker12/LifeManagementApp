package Model;

import org.json.JSONObject;

import java.util.Objects;

public class Prompt {
    private String question;
    private String answers;
    private Boolean complete;
    private String day;
    private int id;

    public int getId() {
        return id;
    }

    public Prompt(String question, String day, int id) {
        this.question = question;
        this.answers = "";
        this.day = day;
        this.id = id;
        this.complete = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prompt)) return false;
        Prompt prompt = (Prompt) o;
        return Objects.equals(question, prompt.question) && Objects.equals(day, prompt.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, day);
    }

    public String getDay() {
        return day;
    }

    public Boolean getComplete() {
        return complete;
    }

    public String getAnswers() {
        return answers;
    }

    public String getQuestion() {
        return question;
    }

    public void reset() {
        complete = false;
        answers = "";
    }

    public void addAnswer(String answer) {
        this.answers = answer;
        complete = true;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("question", question);
        json.put("answers", answers);
        json.put("day", day);
        json.put("complete", complete);
        json.put("id", id);
        return json;
    }
}
