package Tests;

import Model.Attribute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttributeTest {
    Attribute attribute;

    @BeforeEach
    void init() {
        attribute = new Attribute("Motivation", 0, "Work Ethic");
    }

    @Test
    void testConstructor() {
        assertEquals(attribute.getCategory(), "Work Ethic");
        assertEquals(attribute.getName(), "Motivation");
        assertEquals(attribute.getRating(), 0);
        assertEquals(0, attribute.getWeeklyRatings().size());
    }

    @Test
    void testAddRating() {
        attribute.addRating(4);
        assertEquals("4", attribute.obtainRating(0));
        assertEquals(attribute.getWeeklyRatings().size(), 1);
        attribute.addRating(5);
        assertEquals("5", attribute.obtainRating(1));
        assertEquals(attribute.getWeeklyRatings().size(), 2);
    }

    @Test
    void testUpdate() {
        attribute.addRating(5);
        attribute.addRating(5);
        attribute.addRating(5);
        attribute.addRating(5);
        attribute.addRating(5);
        attribute.addRating(5);
        attribute.addRating(5);
        attribute.update();
        assertEquals(5, attribute.getRating());
    }


}
