package Model;

import org.json.JSONObject;

import java.util.Objects;

public class SubTask extends AbstractTask {
//    Task task;
    private String task;


//    public SubTask(String name, int duration, Task task) {
//        super(name, duration);
//        this.task = task;
//    }
    public SubTask(String name, int duration, String task) {
        super(name, duration);
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubTask)) return false;
        if (!super.equals(o)) return false;
        SubTask subTask = (SubTask) o;
        return Objects.equals(task, subTask.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), task);
    }

    @Override
    public void complete() {
        this.isCompleted = true;
//        task.complete();
    }

    public String getTask() {
        return task;
    }

    public void undo() {
        this.isCompleted = false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("task", task);
        json.put("duration", duration);
        json.put("name", name);
        json.put("isCompleted", isCompleted);
        return json;
    }
}
