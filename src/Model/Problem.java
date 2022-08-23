package Model;

import org.json.JSONObject;

public class Problem {
    private String problem;
    private String solution;
    private boolean solved;

    public Problem(String problem) {
        this.problem = problem;
        this.solution = "";
        this.solved = false;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public boolean isSolved() {
        return solved;
    }

    public String getSolution() {
        return solution;
    }

    public String getProblem() {
        return problem;
    }


    public void solve(String solution) {
        this.solution = solution;
        this.solved = true;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("problem", problem);
        jsonObject.put("solution", solution);
        jsonObject.put("solved", solved);
        return jsonObject;
    }
}
