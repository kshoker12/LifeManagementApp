package Model;

import Persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HourlyList implements Iterable<SubTask>, Writable {
    private Task task;
    private List<SubTask> subTaskList;

    public HourlyList(Task task) {
        this.task = task;
        subTaskList = new ArrayList<>();
    }

    public List<SubTask> getSubTaskList() {
        return subTaskList;
    }

    public void initSubTaskList(List<SubTask> toDoList) {
        subTaskList = new ArrayList<>();
        for (SubTask subTask: toDoList) {
            if (!subTask.getIsCompleted()) {
                subTaskList.add(subTask);
            }
        }
    }



    public Task getTask() {
        return task;
    }


    @Override
    public Iterator<SubTask> iterator() {
        return new HourlyListIterator();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("task", task != null ? task.toJson(): null);
        json.put("subTaskList", subTaskListToJson());
        return json;
    }

    private JSONArray subTaskListToJson() {
        JSONArray jsonArray = new JSONArray();
        for (SubTask task: subTaskList) {
            jsonArray.put(task.toJson());
        }
        return jsonArray;
    }


    private class HourlyListIterator implements Iterator<SubTask> {
        private int limit;
        private int curr;
        private Iterator<SubTask> subTaskIterator;

        public HourlyListIterator() {
            limit = 60;
            curr = 0;
            subTaskIterator = subTaskList.iterator();
        }
        @Override
        public boolean hasNext() {
            return curr < limit && subTaskIterator.hasNext();
        }

        @Override
        public SubTask next() {
            SubTask subTask = subTaskIterator.next();
            curr += subTask.getDuration();
            return subTask;
        }
    }
}

