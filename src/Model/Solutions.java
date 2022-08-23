package Model;

import Persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Solutions implements Writable {
    private String name;
    private List<Problem> problemList;

    public Solutions(String name) {
        this.name = name;
        this.problemList = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProblemList(List<Problem> problemList) {
        this.problemList = problemList;
    }

    public String getName() {
        return name;
    }

    public List<Problem> getProblemList() {
        return problemList;
    }

    public void addProblem(Problem problem) {
        this.problemList.add(problem);
    }

    public void removeProblem(Problem problem) {
        this.problemList.remove(problem);
    }

    public int getPendingTasks() {
        int pending = 0;
        for (Problem problem: problemList) {
            if (!problem.isSolved()) {
                pending+=1;
            }
        }
        return pending;
    }

    public int getCompletedTasks() {
        int completed = 0;
        for (Problem problem: problemList) {
            if (problem.isSolved()) {
                completed+=1;
            }
        }
        return completed;
    }

    public void clear() {
        Iterator<Problem> problemIterator = problemList.iterator();
        while(problemIterator.hasNext()) {
            if (problemIterator.next().isSolved()) {
                problemIterator.remove();
            }
        }

    }


    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("problemList", problemListToJson());
        return jsonObject;
    }

    private JSONArray problemListToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Problem problem: problemList) {
            jsonArray.put(problem.toJson());
        }
        return jsonArray;
    }
}
