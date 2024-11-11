package SYSC4806Project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product apple;
    private Product toaster;

    @BeforeEach
    void setUp() {
        apple = new Product("apple", 1.50);
        toaster = new Product("toaster", 12.99);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getName() {
        assertEquals("apple", apple.getName());
        assertEquals("toaster", toaster.getName());
    }

    @Test
    void getPrice() {
        assertEquals(1.50, apple.getPrice());
        assertEquals(12.99, toaster.getPrice());
    }

    @Test
    void setPrice() {
        apple.setPrice(3.33);
        toaster.setPrice(6.66);
        assertEquals(3.33, apple.getPrice());
        assertEquals(6.66, toaster.getPrice());
    }

    @Test
    void addTag() {
        apple.addTag(Tag.GROCERY);
        assertTrue(apple.getTags().contains(Tag.GROCERY));
        assertFalse(apple.getTags().contains(Tag.ELECTRONIC));

        toaster.addTag(Tag.APPLIANCE);
        toaster.addTag(Tag.ELECTRONIC);
        assertTrue(toaster.getTags().contains(Tag.APPLIANCE));
        assertTrue(toaster.getTags().contains(Tag.ELECTRONIC));
        assertFalse(toaster.getTags().contains(Tag.GROCERY));
    }

    @Test
    void getTags() {
        List<Tag> appleTags = new ArrayList<Tag>();
        appleTags.add(Tag.GROCERY);
        apple.addTag(Tag.GROCERY);
        assertEquals(appleTags, apple.getTags());

        List<Tag> toasterTags = new ArrayList<Tag>();
        toasterTags.add(Tag.APPLIANCE);
        toasterTags.add(Tag.ELECTRONIC);
        toaster.addTag(Tag.APPLIANCE);
        toaster.addTag(Tag.ELECTRONIC);
        assertEquals(toasterTags, toaster.getTags());
    }

    @Test
    void removeTag() {
        apple.addTag(Tag.GROCERY);
        assertTrue(apple.getTags().contains(Tag.GROCERY));
        apple.removeTag(Tag.GROCERY);
        assertFalse(apple.getTags().contains(Tag.GROCERY));

        toaster.addTag(Tag.APPLIANCE);
        toaster.addTag(Tag.ELECTRONIC);
        assertTrue(toaster.getTags().contains(Tag.APPLIANCE));
        toaster.removeTag(Tag.APPLIANCE);
        assertFalse(toaster.getTags().contains(Tag.APPLIANCE));
    }

    @Test
    void removeAllTags() {
        apple.addTag(Tag.GROCERY);
        assertTrue(apple.getTags().contains(Tag.GROCERY));
        apple.removeAllTags();
        assertFalse(apple.getTags().contains(Tag.GROCERY));

        toaster.addTag(Tag.APPLIANCE);
        toaster.addTag(Tag.ELECTRONIC);
        assertTrue(toaster.getTags().contains(Tag.APPLIANCE));
        assertTrue(toaster.getTags().contains(Tag.ELECTRONIC));
        toaster.removeAllTags();
        assertFalse(toaster.getTags().contains(Tag.APPLIANCE));
        assertFalse(toaster.getTags().contains(Tag.ELECTRONIC));
    }

    @Test
    void testToString() {
        String expected = "Product [name=toaster, price=12.99, tags=[]]";
        assertEquals(expected, toaster.toString());

        toaster.addTag(Tag.APPLIANCE);
        toaster.addTag(Tag.ELECTRONIC);
        expected = "Product [name=toaster, price=12.99, tags=[APPLIANCE, ELECTRONIC]]";
        assertEquals(expected, toaster.toString());
    }

}