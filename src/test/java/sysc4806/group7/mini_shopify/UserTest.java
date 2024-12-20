package sysc4806.group7.mini_shopify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User buyer;
    User merchant;
    Shop shop;
    ArrayList<Tag> tags;

    @BeforeEach
    void setUp() {
        buyer = new Buyer("Rebecca", "Rebecca123", "ult1m4t3_fr1sb33");
        merchant = new Merchant("Arthur", "Arthurt123", "zinch");
        tags = new ArrayList<>();
        tags.add(Tag.BABY);
        tags.add(Tag.GROCERY);
        tags.add(Tag.APPLIANCES);
        shop = new Shop("Arthur's Assorted Goods", "a shop", (Merchant)merchant, tags);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void login() {
        // Try login with correct password
        assertTrue(buyer.login("Rebecca123", "ult1m4t3_fr1sb33"));
        // Try login with empty password
        assertFalse(buyer.login("rebecca_123", ""));
        // Try login with incorrect password
        assertFalse(buyer.login("rebecca_123", "zinch"));
    }

    @Test
    void getName() {
        assertEquals("Rebecca", buyer.getName());
        assertEquals("Arthur", merchant.getName());
    }

    @Test
    void setName() {
        assertEquals("Rebecca", buyer.getName());
        buyer.setName("Jane");
        assertEquals("Jane", buyer.getName());

        assertEquals("Arthur", merchant.getName());
        merchant.setName("John");
        assertEquals("John", merchant.getName());
    }

    @Test
    void getPassword() {
        assertEquals("ult1m4t3_fr1sb33", buyer.getPassword());
        assertEquals("zinch", merchant.getPassword());
    }

    @Test
    void setPassword() {
        buyer.setPassword("password123");
        assertEquals("password123", buyer.getPassword());
        merchant.setPassword("assword");
        assertEquals("assword", merchant.getPassword());
    }

    @Test
    void testToString() {
        assertEquals("User [Name: Rebecca, Type: Buyer]", buyer.toString());
        assertEquals("User [Name: Arthur, Type: Merchant]", merchant.toString());
    }
}