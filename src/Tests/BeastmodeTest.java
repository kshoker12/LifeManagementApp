//package Tests;
//
//import Model.Beastmode;
//import Model.Responsibility;
//import Model.Task;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class BeastmodeTest {
//    private Beastmode beastmode;
//
//    @BeforeEach
//    void init() {
//        beastmode = new Beastmode("Karn's Beastmode");
//    }
//
//    @Test
//    void testConstructor() {
//        assertEquals(beastmode.getHours(), 0);
//        assertEquals(beastmode.getName(), "Karn's Beastmode");
//        assertEquals(beastmode.getResponsibilities().size(), 0);
//        assertEquals(beastmode.getTodoList().size(), 0);
//    }
//
//    @Test
//    void testAddResponsibility() {
//        Responsibility responsibility = new Responsibility("Training");
//        responsibility.incrementHours();
//        responsibility.incrementHours();
//        assertEquals(beastmode.getHours(), 0);
//        beastmode.addResponsibility(responsibility);
//        assertEquals(beastmode.getHours(), 2);
//        assertEquals(beastmode.getResponsibilities().size(), 1);
//        Responsibility responsibility1 = new Responsibility("Boxing");
//        responsibility1.incrementHours();
//        responsibility1.incrementHours();
//        responsibility1.incrementHours();
//        beastmode.addResponsibility(responsibility1);
//        assertEquals(beastmode.getHours(), 5);
//        assertEquals(beastmode.getResponsibilities().size(), 2);
//
//    }
//
//    @Test
//    void testRemoveResponsibility() {
//        Responsibility responsibility = new Responsibility("Training");
//        responsibility.incrementHours();
//        responsibility.incrementHours();
//        assertEquals(beastmode.getHours(), 0);
//        beastmode.addResponsibility(responsibility);
//        assertEquals(beastmode.getHours(), 2);
//        Responsibility responsibility1 = new Responsibility("Boxing");
//        responsibility1.incrementHours();
//        responsibility1.incrementHours();
//        responsibility1.incrementHours();
//        beastmode.addResponsibility(responsibility1);
//        assertEquals(beastmode.getHours(), 5);
//        beastmode.removeResponsibility(responsibility);
//        assertEquals(beastmode.getHours(), 3);
//        assertEquals(beastmode.getResponsibilities().size(), 1);
//        beastmode.removeResponsibility(responsibility1);
//        assertEquals(beastmode.getHours(), 0);
//        assertEquals(beastmode.getResponsibilities().size(), 0);
//    }
//
//    @Test
//    void testAddTask() {
//        assertEquals(beastmode.getTodoList().size(), 0);
//        beastmode.addTask(new Task("box", 2, false));
//        assertEquals(beastmode.getTodoList().size(), 1);
//    }
//
//    @Test
//    void testRemoveTask() {
//        assertEquals(beastmode.getTodoList().size(), 0);
//        beastmode.addTask(new Task("box", 2, false));
//        assertEquals(beastmode.getTodoList().size(), 1);
//        beastmode.removeTask(new Task("box", 2, false));
//        assertEquals(beastmode.getTodoList().size(), 0);
//    }
//
//    @Test
//    void testReset() {
//        Responsibility r1 = new Responsibility("Boxing");
//        r1.incrementHours();
//        r1.incrementHours();
//        r1.incrementHours();
//        Responsibility r2 = new Responsibility("Training");
//        r2.incrementHours();
//        r2.incrementHours();
//        assertEquals(beastmode.getHours(), 0);
//        beastmode.addResponsibility(r1);
//        assertEquals(beastmode.getHours(), 3);
//        beastmode.addResponsibility(r2);
//        assertEquals(beastmode.getHours(), 5);
//        beastmode.reset();
//        assertEquals(beastmode.getHours(), 0);
//    }
//
//    @Test
//    void testStartHour() {
//        Task task = new Task("Hello", 2, false);
//        beastmode.addTask(task);
//        beastmode.addTask(new Task("stop", 3, false));
//        task.complete();
//        beastmode.startHour();
//        assertEquals(beastmode.getTask(), new Task("stop", 3, false));
//    }
//
//    @Test
//    void testFinishHour() {
//        Task task = new Task("Hello", 2, false);
//        Responsibility responsibility = new Responsibility("fdd");
//        responsibility.addTask(task);
//        beastmode.addTask(task);
//        beastmode.startHour();
//        beastmode.finishHour();
//        assertEquals(1, responsibility.getHours());
//    }
//
//}
