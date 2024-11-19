package SYSC4806Project;

import SYSC4806Project.ItemQuantityPair;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemQuantityListTest {

    Product apple;
    Product bread;
    Product toaster;

    ItemQuantityPair iqp1;
    ItemQuantityPair iqp2;
    ItemQuantityPair iqp3;
    ItemQuantityList iql1;

    @BeforeEach
    void setUp() {
        apple = new Product("apple", 1.50);
        bread = new Product("bread", 4.25);
        toaster = new Product("toaster", 12.99);
        iql1 = new ItemQuantityList();
        iqp1 = new ItemQuantityPair(apple, 1);
        iqp2 = new ItemQuantityPair(bread, 2);
        iqp3 = new ItemQuantityPair(toaster, 3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addItems() {
        // Add Product::toaster to ItemQuantityList
        assertTrue(iql1.addProduct(toaster));
        // Try to add valid quantity of valid product
        assertTrue(iql1.addItems(toaster, 1));
        // Try to add invalid quantity of valid product
        assertFalse(iql1.addItems(toaster, -1));
        // Try to add valid quantity of invalid product
        assertFalse(iql1.addItems(bread, 1));
    }

    @Test
    void addProduct() {
        // Add Product::toaster to ItemQuantityList
        assertTrue(iql1.addProduct(toaster));
        // Try to add duplicate of Product::toaster to ItemQuantityList
        assertFalse(iql1.addProduct(toaster));
        // Try to add null product
        assertFalse(iql1.addProduct(null));
    }

    @Test
    void removeItems() {
        // Add Product::toaster to ItemQuantityList
        iql1.addProduct(toaster);
        // Add valid quantity of valid product
        iql1.addItems(toaster, 3);
        // Try to remove null item
        assertFalse(iql1.removeItems(null, 1));
        // Try to remove invalid item
        assertFalse(iql1.removeItems(bread, 1));
        // Try to remove valid item, valid quantity
        assertTrue(iql1.removeItems(toaster, 1));
        // Try to remove valid item, invalid quantity
        assertFalse(iql1.removeItems(toaster, 100));
    }

    @Test
    void removeProduct() {
        // Add Product::toaster to ItemQuantityList
        iql1.addProduct(toaster);
        // Try to remove null product
        assertFalse(iql1.removeProduct(null));
        // Try to remove invalid product
        assertFalse(iql1.removeProduct(bread));
        // Try to remove valid product
        assertTrue(iql1.removeProduct(toaster));
    }

    @Test
    void contains() {
        // Add Product::toaster to ItemQuantityList
        iql1.addProduct(toaster);
        // Check for contained Product
        assertTrue(iql1.contains(toaster));
        // Check for uncontained Product
        assertFalse(iql1.contains(bread));
        // Check for null Product
        assertFalse(iql1.contains(null));
    }

    @Test
    void getItemQuantity() {
        // Add Product::toaster to ItemQuantityList
        iql1.addProduct(toaster);
        // Try to get quantity of valid product without any quantity
        assertEquals(0, iql1.getItemQuantity(toaster));
        // Add valid quantity of valid product
        iql1.addItems(toaster, 3);
        // Try to get quantity of valid product with some quantity
        assertEquals(3, iql1.getItemQuantity(toaster));
        // Try to get quantity of invalid product
        assertThrows(RuntimeException.class, () -> iql1.getItemQuantity(bread));
        // Try to get quantity of null item
        assertThrows(RuntimeException.class, () -> iql1.getItemQuantity(null));
    }
}