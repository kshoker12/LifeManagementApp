package Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Task extends AbstractTask {
    private Boolean isRepeated;
    private List<SubTask> subTasks;
    private String responsibility;

    public Task(String name, int duration, Boolean isRepeated) {
        super(name, duration);
        this.duration = 0;
        this.isRepeated = isRepeated;
        this.subTasks = new ArrayList<>();
    }

    public int getNumTasks() {
        return subTasks.size();
    }

    public int getNumCompletedTasks() {
        int tasks = 0;
        for (SubTask subTask: subTasks) {
            if (subTask.getIsCompleted()) {
                tasks+= 1;
            }
        }
        return tasks;
    }

    public void clearSubTask() {
        Iterator<SubTask> subTaskIterator = subTasks.iterator();
        while (subTaskIterator.hasNext()) {
            SubTask subTask = subTaskIterator.next();
            if (subTask.isCompleted) {
                subTaskIterator.remove();
            }
        }
    }

    public void undo() {
        for (SubTask subTask: subTasks) {
            subTask.undo();
        }
    }
    public Boolean getIsRepeated() {
        return isRepeated;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void addSubtask(SubTask subTask) {
        subTasks.add(subTask);
        calculateDuration();
    }

    private void calculateDuration() {
        int temp = 0;
        for (SubTask subTask: subTasks) {
            temp += subTask.getDuration();
        }
        this.duration = temp;
    }

    public void removeSubtask(SubTask subTask) {
        subTasks.remove(subTask);
        calculateDuration();
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public Boolean getRepeated() {
        return isRepeated;
    }

    @Override
    public void complete() {
        Boolean completed = true;
        Iterator<SubTask> subTaskIterator = subTasks.iterator();
        while (subTaskIterator.hasNext()) {
            SubTask subTask = subTaskIterator.next();
            if (!subTask.getIsCompleted()) {
                completed = false;
            } else {
//                subTaskIterator.remove();
            }
        }
        if (subTasks.isEmpty()) {
            this.isCompleted = false;
        } else {
            this.isCompleted = completed;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("isRepeated" , isRepeated);
        json.put("subTasks", subTasksToJson());
        json.put("Responsibility", responsibility);
        json.put("duration", duration);
        json.put("name", name);
        json.put("isCompleted", isCompleted);
        return json;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public void setRepeated(Boolean repeated) {
        isRepeated = repeated;
    }

    private JSONArray subTasksToJson() {
        JSONArray jsonArray = new JSONArray();
        for (SubTask subTask: subTasks) {
            jsonArray.put(subTask.toJson());
        }

        return jsonArray;
    }
}
