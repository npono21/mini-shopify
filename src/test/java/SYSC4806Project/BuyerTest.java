package SYSC4806Project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuyerTest {

    Main main;
    Buyer b1;
    Merchant m1;
    Shop s1;

    @BeforeEach
    void setUp() {
        main = new Main();
        b1 = new Buyer("Rebecca", "ult1m4t3_fr1sb33", main);
        m1 = new Merchant("Arthur", "zinch", main);
        s1 = new Shop("Arthur's Appliances", 123, m1);
        main.addShop(s1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getShop() {
        // Try to get a valid Shop
        assertEquals(s1, b1.getShop("Arthur's Appliances"));
        // Try to get an invalid Shop
        assertNull(b1.getShop("Michael's Magic Tricks"));
    }
}