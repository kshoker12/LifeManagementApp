package Model;

import Persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Responsibility implements Writable {
    private int hours;
    private String name;
    private List<Task> tasks;
    private List<Task> repeatedTasks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Responsibility)) return false;
        Responsibility that = (Responsibility) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Responsibility(String name) {
        this.name = name;
        this.hours = 0;
        this.tasks = new ArrayList<>();
        repeatedTasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        task.setResponsibility(this.name);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
        task.setResponsibility(null);
    }

    public int getNumTasks() {
        return tasks.size();
    }

    public int getNumCompletedTasks() {
        int temp = 0;
        for (Task task: tasks) {
            if (task.getIsCompleted()) {
                temp+= 1;
            }
        }
        return temp;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String getName() {
        return name;
    }

    public void incrementHours() {
        hours+= 1;
    }

    public int getHours() {
        return hours;
    }

    public List<Task> getRepeatedTasks() {
        return repeatedTasks;
    }

    public void clear() {
        for (Task next: tasks) {
            if (next.getIsRepeated()) {
                next.undo();
                repeatedTasks.add(next);
            }
        }
        for (Task next: tasks) {
            if (!next.getIsCompleted()) {
                if (!repeatedTasks.contains(next)) {
                    repeatedTasks.add(next);
                }
            }
        }
        tasks.clear();
        reset();
    }

    public void reset() {
        this.hours = 0;
        this.tasks.clear();
        addRepeatedTasks();
    }

    private void addRepeatedTasks() {
        for (Task next: repeatedTasks) {
            tasks.add(next);
            next.complete();
            if (!next.getIsRepeated()) {
                next.clearSubTask();
            }
        }
        repeatedTasks.clear();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("hours", hours);
        json.put("name", name);
        json.put("repeatedTasks", repeatedTasksToJson());
        json.put("tasks", tasksToJson());
        return json;
    }

    private JSONArray repeatedTasksToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Task task: repeatedTasks) {
            jsonArray.put(task.toJson());
        }
        return jsonArray;
    }

    public void setRepeatedTasks(List<Task> repeatedTasks) {
        this.repeatedTasks = repeatedTasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    private JSONArray tasksToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Task task: tasks) {
            jsonArray.put(task.toJson());
        }
        return jsonArray;
    }

}
