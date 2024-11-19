package SYSC4806Project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.RegisteredBean;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    Main main;
    User buyer;
    User merchant;
    Shop shop;

    @BeforeEach
    void setUp() {
        main = new Main();
        buyer = new Buyer("Rebecca", "ult1m4t3_fr1sb33", main);
        merchant = new Merchant("Arthur", "zinch", main);
        shop = new Shop("Arthur's Appliances", 123, (Merchant)merchant);
        main.addShop(shop);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void login() {
        // Try login with correct password
        assertTrue(buyer.login("Rebecca", "ult1m4t3_fr1sb33"));
        // Try login with empty password
        assertFalse(buyer.login("Rebecca", ""));
        // Try login with incorrect password
        assertFalse(buyer.login("Rebecca", "zinch"));
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