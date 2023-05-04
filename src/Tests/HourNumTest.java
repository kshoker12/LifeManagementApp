package Tests;

import Model.HourNum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HourNumTest {
    private HourNum hourNum;

    @BeforeEach
    void init() {
        hourNum = new HourNum();
    }

    @Test
    void testConstructor() {
        assertEquals(hourNum.getHour(), 1);
    }

    @Test
    void setHours() {
        hourNum.setHour(5);
        assertEquals(5, hourNum.getHour());
    }

    @Test
    void testIncrementHour() {
        hourNum.incrementHour();
        assertEquals(2, hourNum.getHour());
    }

    @Test
    void testResetHour() {
        hourNum.incrementHour();
        assertEquals(2, hourNum.getHour());
        hourNum.resetHour();
        assertEquals(1, hourNum.getHour());
    }

    @Test
    void testGetEnding() {
        assertEquals("st", hourNum.getEnding());
        hourNum.incrementHour();
        assertEquals("nd", hourNum.getEnding());
        hourNum.incrementHour();
        assertEquals("rd", hourNum.getEnding());
        hourNum.incrementHour();
        assertEquals("th", hourNum.getEnding());
    }
}
