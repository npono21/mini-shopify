package SYSC4806Project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MerchantTest {
    Merchant m1;

    @BeforeEach
    void setUp() {
        m1 = new Merchant("Arthur", "zinch");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createShop() {
        assertInstanceOf(Shop.class, m1.createShop("Arthur's Appliances", "a shop", 123));
        // TODO: assertThrows(IllegalArgumentException.class, () -> m1.createShop("Arthur's Appliances", "a shop", 123));
    }

    @Test
    void addProductToShop() {
        Product toaster = new Product("Toaster", 12.99);
        Shop s1 = m1.createShop("Arthur's Appliances", "a shop", 123);
        m1.addProductToShop(s1, toaster);
        assertTrue(s1.getProductList().contains(toaster));
    }

    @Test
    void removeProductFromShop() {
        Product toaster = new Product("Toaster", 12.99);
        Shop s1 = m1.createShop("Arthur's Appliances", "a shop", 123);
        m1.addProductToShop(s1, toaster);
        assertTrue(s1.getProductList().contains(toaster));
        m1.removeProductFromShop(s1, toaster);
        assertFalse(s1.getProductList().contains(toaster));
    }

    @Test
    void setProductQuantity() {
        Product toaster = new Product("Toaster", 12.99);
        Shop s1 = m1.createShop("Arthur's Appliances", "a shop", 123);
        m1.addProductToShop(s1, toaster);
        m1.setProductQuantity(s1, toaster, 22);
        assertTrue(s1.getInventory().getItemQuantity(toaster) == 22);
    }
}