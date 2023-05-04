package Tests;

import Model.Attribute;
import Model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {
    Category category;
    Attribute attribute;

    @BeforeEach
    void init() {
        category = new Category("Work Ethic");
        attribute = new Attribute("Motivation", 40, "Work Ethic");
    }

    @Test
    void testConstructor() {
        assertEquals(category.getName(), "Work Ethic");
        assertEquals(0, category.getAttributes().size());
        assertEquals(0, category.getRating());
    }

    @Test
    void testAddAttribute() {
        category.addAttribute(attribute);
        assertEquals(1, category.getAttributes().size());
        assertEquals(attribute.getName(), category.getAttributes().get(0).getName());
        category.addAttribute(attribute);
        assertEquals(2, category.getAttributes().size());
        assertEquals(attribute.getName(), category.getAttributes().get(1).getName());
    }
}
