package Tests;

import Model.Destination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DestinationTest {
    Destination destination;

    @BeforeEach
    void init() {
        destination = new Destination(5);
    }

    @Test
    void testConstructor() {
        assertEquals(5, destination.getPoint());
        assertEquals(false, destination.isFinish());
        assertEquals(-1 ,destination.getEarned());
        assertEquals("", destination.getName());
    }

    @Test
    void testSetName() {
        destination.setName("40 Hours Total");
        assertEquals("40 Hours Total", destination.getName());
    }

    @Test
    void testSetEarned() {
        destination.setEarned(5);
        assertEquals(5, destination.getEarned());
        assertEquals(true, destination.isFinish());
    }

    @Test
    void testSetFinish() {
        assertEquals(false, destination.isFinish());
        destination.setFinish(true);
        assertEquals(true, destination.isFinish());
    }

    @Test
    void testReset() {
        destination.setEarned(5);
        assertEquals(true, destination.isFinish());
        destination.reset();
        assertEquals(false, destination.isFinish());
        assertEquals(-1, destination.getEarned());
    }

}
