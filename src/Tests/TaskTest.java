//package Tests;
//
//import Model.SubTask;
//import Model.Task;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class TaskTest {
//    private Task task;
//    private Task task1;
//
//    @BeforeEach
//    void init() {
//        task = new Task("Sports", 2, false);
//        task1 = new Task("Boxing", 1, true);
//    }
//
//    @Test
//    void testConstructor() {
//        assertEquals(task.getName(), "Sports");
//        assertEquals(task.getIsRepeated(), false);
//        assertEquals(task.getDuration(), 0);
//        assertEquals(task.getSubTasks().size(), 0);
//    }
//
//    @Test
//    void testAddSubTask() {
//        assertEquals(task.getSubTasks().size(), 0);
//        SubTask subTask = new SubTask("Sprints", 1, task);
//        task.addSubtask(subTask);
//        assertEquals(task.getSubTasks().size(), 1);
//    }
//
//    @Test
//    void testRemoveSubTask() {
//        assertEquals(task.getSubTasks().size(), 0);
//        SubTask subTask = new SubTask("Sprints", 1, task);
//        task.addSubtask(subTask);
//        assertEquals(task.getSubTasks().size(), 1);
//        task.removeSubtask(new SubTask("Sprints", 1, task));
//        assertEquals(task.getSubTasks().size(), 0);
//    }
//
//    @Test
//    void testIsComplete() {
//        SubTask subTask = new SubTask("Sprints", 1, task);
//        SubTask subTask1 = new SubTask("Box", 1, task);
//        task.addSubtask(subTask);
//        task.addSubtask(subTask1);
//        assertEquals(false, task.getIsCompleted());
//        subTask.complete();
//        task.complete();
//        assertEquals(false, task.getIsCompleted());
//        subTask1.complete();
//        task.complete();
//        assertEquals(task.getIsCompleted(), true);
//    }
//
//}
