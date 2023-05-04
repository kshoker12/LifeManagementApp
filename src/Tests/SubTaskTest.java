package Tests;

import Model.SubTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubTaskTest {
    private SubTask subTask;

    @BeforeEach
    void init() {
        subTask = new SubTask("Shoveling", 2, "dd");
    }

    @Test
    void testConstructor() {
        assertEquals("Shoveling", subTask.getName());
        assertEquals(2, subTask.getDuration());
    }

    @Test
    void testComplete() {
        assertEquals(false, subTask.getIsCompleted());
        subTask.complete();
        assertEquals(true, subTask.getIsCompleted());
    }
}
