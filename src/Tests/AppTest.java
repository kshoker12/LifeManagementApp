package Tests;

import Model.App;
import Model.Responsibility;
import Model.SubTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    private App app;

    @BeforeEach
    void init() {
        app = new App("Karn's Beastmode");
    }

    @Test
    void testConstructor() {
        assertEquals(app.getHours(), 0);
        assertEquals(app.getName(), "Karn's Beastmode");
        assertEquals(app.getResponsibilities().size(), 0);
        assertEquals(app.getTodoList().size(), 0);
    }

    @Test
    void testAddResponsibility() {
        Responsibility responsibility = new Responsibility("Training");
        responsibility.incrementHours();
        responsibility.incrementHours();
        assertEquals(app.getHours(), 0);
        app.addResponsibility(responsibility);
        assertEquals(app.getHours(), 2);
        assertEquals(app.getResponsibilities().size(), 1);
        Responsibility responsibility1 = new Responsibility("Boxing");
        responsibility1.incrementHours();
        responsibility1.incrementHours();
        responsibility1.incrementHours();
        app.addResponsibility(responsibility1);
        assertEquals(app.getHours(), 5);
        assertEquals(app.getResponsibilities().size(), 2);

    }

    @Test
    void testRemoveResponsibility() {
        Responsibility responsibility = new Responsibility("Training");
        responsibility.incrementHours();
        responsibility.incrementHours();
        assertEquals(app.getHours(), 0);
        app.addResponsibility(responsibility);
        assertEquals(app.getHours(), 2);
        Responsibility responsibility1 = new Responsibility("Boxing");
        responsibility1.incrementHours();
        responsibility1.incrementHours();
        responsibility1.incrementHours();
        app.addResponsibility(responsibility1);
        assertEquals(app.getHours(), 5);
        app.removeResponsibility(responsibility);
        assertEquals(app.getHours(), 3);
        assertEquals(app.getResponsibilities().size(), 1);
        app.removeResponsibility(responsibility1);
        assertEquals(app.getHours(), 0);
        assertEquals(app.getResponsibilities().size(), 0);
    }

    @Test
    void testAddTask() {
        assertEquals(app.getTodoList().size(), 0);
        app.addTask(new SubTask("box", 2, "h"));
        assertEquals(app.getTodoList().size(), 1);
    }

    @Test
    void testRemoveTask() {
        assertEquals(app.getTodoList().size(), 0);
        app.addTask(new SubTask("box", 2, "f"));
        assertEquals(app.getTodoList().size(), 1);
        app.removeTask(new SubTask("box", 2, "f"));
        assertEquals(app.getTodoList().size(), 0);
    }

    @Test
    void testReset() {
        Responsibility r1 = new Responsibility("Boxing");
        r1.incrementHours();
        r1.incrementHours();
        r1.incrementHours();
        Responsibility r2 = new Responsibility("Training");
        r2.incrementHours();
        r2.incrementHours();
        assertEquals(app.getHours(), 0);
        app.addResponsibility(r1);
        assertEquals(app.getHours(), 3);
        app.addResponsibility(r2);
        assertEquals(app.getHours(), 5);
        app.reset();
        assertEquals(app.getHours(), 0);
    }
}
