package SYSC4806Project;

import SYSC4806Project.testRepos.CartTestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class CartTest {
    @Autowired
    CartTestRepository cartRepository;

    Cart cart;

    Product apple;
    Product bread;
    Product toaster;

    ItemQuantityPair iqp1;
    ItemQuantityPair iqp2;
    ItemQuantityPair iqp3;
    ItemQuantityList iql1;

    Shop shop;

    @BeforeEach
    void setUp() {
        shop = new Shop();
        cart = new Cart();
        apple = new Product("apple", "some apples", 1.50, shop);
        bread = new Product("bread", "some bread", 4.25, shop);
        toaster = new Product("toaster", "a toaster", 12.99, shop);
        iql1 = new ItemQuantityList();
        iqp1 = new ItemQuantityPair(apple, 1);
        iqp2 = new ItemQuantityPair(bread, 2);
        iqp3 = new ItemQuantityPair(toaster, 3);
        shop.addProduct(apple);
        shop.addProduct(bread);
        shop.addProduct(toaster);
        shop.addInventory(apple, 100);
        shop.addInventory(bread, 100);
        shop.addInventory(toaster, 100);
        cart = cartRepository.save(cart);// need to generate Ids
        System.out.println(cart);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addItem() {
        // Try to add null Product
        //assertFalse(cart.addItem((Product) null));
        // Try to add null ProductId
        //assertFalse(cart.addItem((Long) null));
        // Add quantity of 1 of a Product
        assertTrue(cart.addItem(apple));
        assertEquals(1, cart.getItems().getItemQuantity(apple));
        // Add quantity of 1 of an existing Product
        assertTrue(cart.addItem(apple));
        assertEquals(2, cart.getItems().getItemQuantity(apple));
        // Add quantity of 1 of another Product
        assertTrue(cart.addItem(bread));
        assertEquals(1, cart.getItems().getItemQuantity(bread));
    }

    @Test
    void addItems() {
        // Try to add null Product
        assertFalse(cart.addItems((Product) null, 100));
        // Try to add null Product
        assertFalse(cart.addItems((Long) null, 100));
        // Add quantity of 1 of a Product
        assertTrue(cart.addItems(apple, 1));
        assertEquals(1, cart.getItems().getItemQuantity(apple));
        // Add quantity of 3 of an existing Product
        assertTrue(cart.addItems(apple, 4));
        assertEquals(5, cart.getItems().getItemQuantity(apple));
        // Add quantity of 9 of another existing
        assertTrue(cart.addItems(bread, 9));
        assertEquals(9, cart.getItems().getItemQuantity(bread));
        // Add quantity of -100 of an existing Product
        assertFalse(cart.addItems(apple, -100));
        assertEquals(5, cart.getItems().getItemQuantity(apple));
    }

    @Test
    void removeItem() {
        // Try to remove from empty cart
        assertFalse(cart.removeItem(bread.getId()));
        // Try to remove null Product from cart
        assertFalse(cart.removeItem(null));
        // Add quantity of 2 of a Product
        cart.addItems(apple, 2);
        assertEquals(2, cart.getItems().getItemQuantity(apple));

        // Needed so an Id is generated
        cart = cartRepository.save(cart);
        apple = cart.getItems().getProductByName(apple.getName());
        // Remove quantity of 1 of a Product, leaving 1 quantity
        assertTrue(cart.removeItem(apple.getId()));
        assertEquals(1, cart.getItems().getItemQuantity(apple));
        // Remove quantity of 1 of a Product, leaving 0 quantity
        // and therefore removing Product from Cart
        assertTrue(cart.removeItem(apple.getId()));
        assertFalse(cart.getItems().contains(apple));
    }

    @Test
    void removeItems() {
        // Try to remove from empty cart
        assertThrows(RuntimeException.class, () -> cart.removeItems(bread, 1));
        // Try to remove null Product from cart
        assertFalse(cart.removeItems((Product) null, 1));
        // Try to remove null Product from cart
        assertFalse(cart.removeItems((Long) null, 1));
        // Add quantity of 10 of a Product
        cart.addItems(apple, 10);
        assertEquals(10, cart.getItems().getItemQuantity(apple));
        // Remove quantity of 0 of a Product, leaving 10 quantity
        assertTrue(cart.removeItems(apple, 0));
        assertEquals(10, cart.getItems().getItemQuantity(apple));

        // Needed so an Id is generated
        cart = cartRepository.save(cart);
        apple = cart.getItems().getProductByName(apple.getName());
        // Remove quantity of 1 of a Product, leaving 9 quantity
        assertTrue(cart.removeItems(apple, 1));
        assertEquals(9, cart.getItems().getItemQuantity(apple));
        // Remove quantity of 3 of a Product, leaving 6 quantity
        assertTrue(cart.removeItems(apple, 3));
        assertEquals(6, cart.getItems().getItemQuantity(apple));
        // Try to remove quantity of 100 of a Product, which should fail,
        // still leaving 6 in the cart
        assertFalse(cart.removeItems(apple, 100));
        assertEquals(6, cart.getItems().getItemQuantity(apple));
        // Remove remaining quantity of 6 of a Product from Cart,
        // and therefore remove Product from Cart
        assertTrue(cart.removeItems(apple, 6));
        assertFalse(cart.getItems().contains(apple));
    }

    @Test
    void getItems() {
        ItemQuantityList expectedItems = new ItemQuantityList();
        expectedItems.addProduct(apple);
        expectedItems.addProduct(bread);
        expectedItems.addProduct(toaster);
        expectedItems.addItems(apple, 1);
        expectedItems.addItems(bread,2);
        expectedItems.addItems(toaster,3);
        cart.addItems(apple, 1);
        cart.addItems(bread, 2);
        cart.addItems(toaster, 3);
        assertEquals(expectedItems.getItemQuantity(apple), cart.getItems().getItemQuantity(apple));
        assertEquals(expectedItems.getItemQuantity(bread), cart.getItems().getItemQuantity(bread));
        assertEquals(expectedItems.getItemQuantity(toaster), cart.getItems().getItemQuantity(toaster));
    }

    @Test
    void getRunningTotal() {
        cart.addItems(apple, 1);
        cart.addItems(bread, 2);
        cart.addItems(toaster, 3);
        double expected = apple.getPrice() * 1 + bread.getPrice() * 2 + toaster.getPrice() * 3;
        assertEquals(expected, cart.getRunningTotal());
    }

}