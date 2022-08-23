package Model;

import Persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Beastmode implements Writable {
    private String name;
//    private List<Task> todoList;
    private List<SubTask> todoList;
    private List<Responsibility> responsibilities;
    private int hours;
    private Task task;
    private HourlyList hourlyList;
    private HourNum hourNum;

    public Beastmode(String name) {
        this.name = name;
        this.todoList = new ArrayList<>();
        this.responsibilities = new ArrayList<>();
        this.hours = 0;
        this.hourNum = new HourNum();
        initHourlyList();
    }

    public Responsibility findResponsibility(String responsibility) {
        Responsibility name = null;
        for (Responsibility next: responsibilities) {
            if (next.getName() == responsibility) {
                name = next;
                break;
            }
        }
        return name;
    }

    public HourNum getHourNum() {
        return hourNum;
    }

    public HourlyList getHourlyList() {
        return hourlyList;
    }

//    public void initHourlyList() {
//        Task task = null;
//        for (Task next: todoList) {
//            if (!next.getIsCompleted()) {
//                task = next;
//                break;
//            }
//        }
//        hourlyList = new HourlyList(task);
//        hourlyList.initSubTaskList(todoList);
//    }
    public void initHourlyList() {
        Task task = null;
        for (SubTask next: todoList) {
            if (!next.getIsCompleted()) {
                task = searchTask(next.getTask());
                break;
            }
        }
        hourlyList = new HourlyList(task);
        hourlyList.initSubTaskList(todoList);
    }

    public int getNumTasks() {
        int tasks = 0;
        for (Responsibility responsibility: responsibilities) {
            tasks += responsibility.getTasks().size();
        }

        return tasks;
    }

    public int getNumCompletedTasks() {
        int tasks = 0;
        for (Responsibility responsibility: responsibilities) {
            for (Task task: responsibility.getTasks()) {
                if (task.getIsCompleted()) {
                    tasks+= 1;
                }
            }
        }
        return tasks;
    }

    public void addResponsibility(Responsibility responsibility) {
        this.responsibilities.add(responsibility);
        calculateHours();
    }

    public void removeResponsibility(Responsibility responsibility) {
        this.responsibilities.remove(responsibility);
        calculateHours();
    }

    public void addTask(SubTask task) {
        this.todoList.add(task);
    }

    public void removeTask(SubTask task) {
        this.todoList.remove(task);
    }

    public int getHours() {
        return hours;
    }

    public List<Responsibility> getResponsibilities() {
        return responsibilities;
    }

    public String getName() {
        return name;
    }

    public List<SubTask> getTodoList() {
        return todoList;
    }

    public void clear() {
        this.todoList.clear();
    }

    private void calculateHours() {
        int tempHours = 0;
        for (Responsibility next: responsibilities) {
            tempHours += next.getHours();
        }
        this.hours = tempHours;
    }

    public void reset() {
        for (Responsibility next: responsibilities) {
            next.clear();
        }
        calculateHours();
    }

//    public void startHour() {
//        task = getFirstTask();
//    }

    public Task getTask() {
        return task;
    }

    public void finishHour() {
        calculateHours();
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("hours", hours);
        json.put("task", task != null ? task.toJson(): null);
        json.put("hourlyList", hourlyList.toJson());
        json.put("hourNum", hourNum.toJson());
        json.put("todoList", toDoListToJson());
        json.put("responsibilities", responsibilitiesToJson());
        return json;
    }

    public void setResponsibilities(List<Responsibility> responsibilities) {
        this.responsibilities = responsibilities;
    }

//    public void setTodoList(List<Task> todoList) {
//        this.todoList = todoList;
//    }

    public void setHourlyList(HourlyList hourlyList) {
        this.hourlyList = hourlyList;
    }

    public void setHourNum(HourNum hourNum) {
        this.hourNum = hourNum;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    private JSONArray responsibilitiesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Responsibility responsibility: responsibilities) {
            jsonArray.put(responsibility.toJson());
        }

        return jsonArray;
    }

    private JSONArray toDoListToJson() {
        JSONArray jsonArray = new JSONArray();
        for (SubTask task: todoList) {
            jsonArray.put(task.toJson());
        }

        return jsonArray;
    }

    public Task searchTask(String task) {
        Task task1 = null;
        for (Responsibility responsibility: responsibilities) {
            for (Task next: responsibility.getTasks()) {
                if (next.getName().equals(task)) {
                    task1 = next;
                    break;
                }
            }
            if (task1 != null) {
                break;
            }
        }
        return task1;
    }

    public void searchSubTask(SubTask next) {
        Boolean found = false;
        for (Responsibility responsibility: responsibilities) {
            for (Task task: responsibility.getTasks()) {
                for (SubTask subTask1: task.getSubTasks()) {
                    if (subTask1.equals(next)) {
                        subTask1.complete();
                        searchTask(subTask1.getTask()).complete();
                        found = true;
                        break;
                    }
                    if (found) {
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            if (found) {
                break;
            }
        }
    }
}
