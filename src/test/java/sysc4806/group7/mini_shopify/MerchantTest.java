package sysc4806.group7.mini_shopify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MerchantTest {
    private Merchant m1;
    private Shop s1;
    private ArrayList<Tag> tags;

    @BeforeEach
    void setUp() {
        m1 = new Merchant("Arthur", "arthur123", "zinch");
        tags = new ArrayList<>();
        tags.add(Tag.BABY);
        tags.add(Tag.GROCERY);
        tags.add(Tag.APPLIANCES);
        s1 = new Shop("Arthur's Appliances", "a shop", m1, tags);
    }

    @AfterEach
    void tearDown() {
        m1 = null;
        s1 = null;
        tags = null;
    }

    @Test
    void createShop() {
        assertInstanceOf(Shop.class, s1);
        // TODO: assertThrows(IllegalArgumentException.class, () -> m1.createShop("Arthur's Appliances", "a shop", 123));
    }

    @Test
    void addProductToShop() {
        Product toaster = new Product("Toaster", "a toaster", 12.99, s1);
        m1.addProductToShop(s1, toaster);
        assertTrue(s1.getProducts().contains(toaster));
    }

    @Test
    void removeProductFromShop() {
        Product toaster = new Product("Toaster", "a toaster", 12.99, s1);
        m1.addProductToShop(s1, toaster);
        assertTrue(s1.getProducts().contains(toaster));
        m1.removeProductFromShop(s1, toaster);
        assertFalse(s1.getProducts().contains(toaster));
    }

    @Test
    void setProductQuantity() {
        Product toaster = new Product("Toaster", "a toaster",12.99, s1);
        m1.addProductToShop(s1, toaster);
        m1.setProductQuantity(s1, toaster, 22);
        assertTrue(s1.getInventory().getItemQuantity(toaster) == 22);
    }
}