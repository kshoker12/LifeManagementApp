package Tests;

import Model.HourlyList;
import Model.SubTask;
import Model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HourlyListTest {
    private HourlyList hourlyList;
    private List<Task> taskList;
    private List<SubTask> subTaskList;

    @BeforeEach
    void init() {
        taskList = new ArrayList<>();
        subTaskList = new ArrayList<>();
        Task task = new Task("t1", 0, false);
        task.addSubtask(new SubTask("S1", 20, "t1"));
        task.addSubtask(new SubTask("S2", 20, "t1"));
        task.addSubtask(new SubTask("S3", 19, "t1"));
        subTaskList.add(task.getSubTasks().get(0));
        subTaskList.add(task.getSubTasks().get(1));
        subTaskList.add(task.getSubTasks().get(2));
        Task task1 = new Task("t2", 0, false);
        task1.addSubtask(new SubTask("S4", 10, "t1"));
        task1.addSubtask(new SubTask("S5", 10, "t1"));
        task1.addSubtask(new SubTask("S6", 10, "t1"));
        subTaskList.add(task1.getSubTasks().get(0));
        subTaskList.add(task1.getSubTasks().get(1));
        subTaskList.add(task1.getSubTasks().get(2));
        Task task2 = new Task("t3", 0, false);
        task2.addSubtask(new SubTask("S7", 10, "t1"));
        task2.addSubtask(new SubTask("S8", 10, "t1"));
        task2.addSubtask(new SubTask("S9", 10, "t1"));
        subTaskList.add(task2.getSubTasks().get(0));
        subTaskList.add(task2.getSubTasks().get(1));
        subTaskList.add(task2.getSubTasks().get(2));
        taskList.add(task);
        taskList.add(task1);
        taskList.add(task2);
        hourlyList = new HourlyList(task);
        hourlyList.initSubTaskList(subTaskList);
    }

    @Test
    void testConstructor() {
        assertEquals(hourlyList.getTask(), taskList.get(0));
        assertEquals(hourlyList.getSubTaskList().size(), 9);
    }


    @Test
    void testLoop() {
        List<SubTask> subTaskList = new ArrayList<>();
        for (SubTask subTask: hourlyList) {
            subTaskList.add(subTask);
        }
        assertEquals(subTaskList.size(), 4);
        for (int i = 0; i < 3; i++) {
            assertEquals(subTaskList.get(i), taskList.get(0).getSubTasks().get(i));
        }
        assertEquals(subTaskList.get(3), taskList.get(1).getSubTasks().get(0));

    }
}
