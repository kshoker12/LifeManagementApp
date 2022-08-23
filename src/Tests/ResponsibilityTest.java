package Tests;

import Model.Responsibility;
import Model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponsibilityTest {
    private Responsibility responsibility;

    @BeforeEach
    void init() {
        responsibility = new Responsibility("Training");
    }

    @Test
    void test() {
//        assertEquals(30/55 , 5);
    }

    @Test
    void testConstructor() {
        assertEquals(responsibility.getHours(), 0);
        assertEquals(responsibility.getName(), "Training");
        assertEquals(responsibility.getTasks().size(), 0);
    }

    @Test
    void testAddTask() {
        assertEquals(responsibility.getTasks().size(), 0);
        Task task = new Task("Box", 2, false);
        responsibility.addTask(task);
        assertEquals(responsibility.getTasks().size(), 1);
        assertEquals(task.getResponsibility(), responsibility);
    }

    @Test
    void testRemoveTask() {
        assertEquals(responsibility.getTasks().size(), 0);
        Task task = new Task("Box", 2, false);
        responsibility.addTask(task);
        assertEquals(responsibility.getTasks().size(), 1);
        responsibility.removeTask(task);
        assertEquals(responsibility.getTasks().size(), 0);
        assertEquals(task.getResponsibility(), null);
    }

    @Test
    void testClear() {
        responsibility.addTask(new Task("box", 10, false));
        responsibility.addTask(new Task("False", 10, true));
        assertEquals(responsibility.getTasks().size(), 2);
        responsibility.clear();
        assertEquals(responsibility.getTasks().size(), 0);
        assertEquals(responsibility.getRepeatedTasks().size(), 1);
    }

    @Test
    void testIncrementHours() {
        assertEquals(responsibility.getHours(), 0);
        responsibility.incrementHours();
        assertEquals(responsibility.getHours(), 1);
        responsibility.incrementHours();
        assertEquals(responsibility.getHours(), 2);
    }

    @Test
    void testReset() {
        responsibility.addTask(new Task("box", 10, false));
        responsibility.addTask(new Task("False", 10, true));
        responsibility.incrementHours();
        responsibility.incrementHours();
        assertEquals(responsibility.getTasks().size(), 2);
        responsibility.clear();
        assertEquals(responsibility.getTasks().size(), 0);
        assertEquals(responsibility.getRepeatedTasks().size(), 1);
        assertEquals(responsibility.getHours(), 2);
        responsibility.reset();
        assertEquals(responsibility.getHours(), 0);
        assertEquals(responsibility.getTasks().size(), 1);
        assertEquals(responsibility.getRepeatedTasks().size(), 0);
    }

}
