package Persistence;

import Model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class AppReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public AppReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public App read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBeastmode(jsonObject);
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private App parseBeastmode(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        App beastmode = new App(name);
        int hours = jsonObject.getInt("hours");
        beastmode.setHours(hours);
        addResponsibilities(beastmode, jsonObject);
        addToDoList(beastmode, jsonObject);
        try {
            Task task = readTask(jsonObject.getJSONObject("task"));
            beastmode.setTask(task);
        } catch (JSONException e) {

        }
//        jsonObject.getJSONObject("task");
        addHourNum(beastmode, jsonObject.getJSONObject("hourNum"));
        addHourlyList(beastmode, jsonObject.getJSONObject("hourlyList"));
        return beastmode;
    }

    private void addHourlyList(App beastmode, JSONObject hourlyList) {
        HourlyList hourlyList1;
        try {
            Task task = readTask(hourlyList.getJSONObject("task"));
            hourlyList1 = new HourlyList(task);
        } catch (JSONException e) {
            hourlyList1 = new HourlyList(null);
        }
        JSONArray subTaskList = hourlyList.getJSONArray("subTaskList");
        for (Object obj: subTaskList) {
            JSONObject nextTask = (JSONObject) obj;
            hourlyList1.getSubTaskList().add(readSubTask(nextTask));
        }
        beastmode.setHourlyList(hourlyList1);
    }

    private void addHourNum(App beastmode, JSONObject jsonObject) {
        HourNum hourNum = new HourNum();
        hourNum.setHour(jsonObject.getInt("hour"));
        beastmode.setHourNum(hourNum);
    }

    private void addToDoList(App beastmode, JSONObject jsonObject) {
        JSONArray todoList = jsonObject.getJSONArray("todoList");
        for (Object obj: todoList) {
            JSONObject todo = (JSONObject) obj;
            SubTask task = readSubTask(todo);
            beastmode.getTodoList().add(task);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addResponsibilities(App beastmode, JSONObject jsonObject) {
        JSONArray responsibilities = jsonObject.getJSONArray("responsibilities");
        for (Object obj : responsibilities) {
            JSONObject resp = (JSONObject) obj;
            Responsibility r = addResponsibility(resp);
            beastmode.addResponsibility(r);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private Responsibility addResponsibility(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Responsibility responsibility = new Responsibility(name);
        int hours = jsonObject.getInt("hours");
        responsibility.setHours(hours);
        JSONArray tasks = jsonObject.getJSONArray("tasks");
        JSONArray repeatedTasks = jsonObject.getJSONArray("repeatedTasks");
        for (Object e: tasks) {
            JSONObject nextTask = (JSONObject) e;
            Task s = readTask(nextTask);
            responsibility.addTask(s);
        }
        for (Object e: repeatedTasks) {
            JSONObject nextTask = (JSONObject) e;
            Task s = readTask(nextTask);
            responsibility.getRepeatedTasks().add(s);
        }
        return responsibility;
    }

    // EFFECTS: reads the exercise
    private Task readTask(JSONObject e) {
        String name = e.getString("name");
        Boolean isRepeated = e.getBoolean("isRepeated");
        Task task = new Task(name, 0, isRepeated);
        task.setCompleted(e.getBoolean("isCompleted"));
        task.setDuration(e.getInt("duration"));
        task.setResponsibility(e.getString("Responsibility"));
        JSONArray subtasks = e.getJSONArray("subTasks");
        for (Object i: subtasks) {
            JSONObject nextSubTask = (JSONObject) i;
            task.addSubtask(readSubTask(nextSubTask));
        }
        return task;
    }

    private SubTask readSubTask(JSONObject i) {
        String name = i.getString("name");
        int duration = i.getInt("duration");
        String task = i.getString("task");
        SubTask subTask = new SubTask(name, duration, task);
        subTask.setCompleted(i.getBoolean("isCompleted"));
        return subTask;
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }
}
