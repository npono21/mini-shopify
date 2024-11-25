package sysc4806.group7.mini_shopify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    Buyer buyer;
    Merchant merchant;
    Shop shop;
    Shop noTagShop;
    Product bread;
    Product toaster;
    ArrayList<Tag> tags;

    @BeforeEach
    void setUp() {
        buyer = new Buyer("Rebecca", "rebecca_123", "ult1m4t3_fr1sb33");
        merchant = new Merchant("Arthur", "arthurt_123", "zinch");
        tags = new ArrayList<>();
        tags.add(Tag.BABY);
        tags.add(Tag.GROCERY);
        tags.add(Tag.APPLIANCES);
        shop = new Shop("Arthur's Appliances", "sells appliances", merchant, tags);
        merchant.addShop(shop);
        toaster = new Product("Toaster", "a toaster", 12.99);
        bread = new Product("Bread", "some bread", 4.25);
        noTagShop = new Shop("Babak's Books", "sells Martin Fowler books", merchant);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addProduct() {
        // Add valid Product to Shop
        assertTrue(shop.addProduct(toaster));
        // Add duplicate valid Product to Shop
        assertTrue(shop.addProduct(toaster));
        // Add null Product to Shop
        assertFalse(shop.addProduct(null));
    }

    @Test
    void removeProduct() {
        // Add valid Product to Shop
        shop.addProduct(toaster);
        // Try to remove invalid Product from Shop
        assertFalse(shop.removeProduct(bread));
        // Try to remove valid Product from Shop
        assertTrue(shop.removeProduct(toaster));
        // Try to remove already removed Product from Shop
        assertFalse(shop.removeProduct(toaster));
        // Try to remove null Product from Shop
        assertFalse(shop.removeProduct(null));
    }

    @Test
    void addInventory() {
        // Add valid Product to Shop
        shop.addProduct(toaster);
        // Try to add quantity of invalid Product to Shop
        assertFalse(shop.addInventory(bread, 1));
        // Try to add valid quantity of valid Product to Shop
        assertTrue(shop.addInventory(toaster, 12));
        // Try to add negative quantity of valid Product to Shop
        assertFalse(shop.addInventory(toaster, -1));
        // Try to add negative quantity of null Product to Shop
        assertFalse(shop.addInventory(null, 100));
    }

    @Test
    void removeInventory() {
        // Add valid Product to Shop
        shop.addProduct(toaster);
        // Add quantity of Product to Shop
        shop.addInventory(toaster, 12);
        // Try to remove quantity of invalid Product from Shop
        assertFalse(shop.addInventory(bread, 1));
        // Try to remove valid quantity of valid Product from Shop
        assertTrue(shop.removeInventory(toaster, 2));
        // Try to remove invalid quantity of valid Product from Shop
        assertFalse(shop.removeInventory(toaster, 100));
    }

    @Test
    void getProductList() {
        // Test empty list
        assertEquals(0, shop.getProducts().size());
        // Add valid Product to Shop
        shop.addProduct(toaster);
        // Test singleton list
        assertEquals(1, shop.getProducts().size());
        assertTrue(shop.getProducts().contains(toaster));
        // Add valid Product to Shop
        shop.addProduct(bread);
        // Test multiple list
        assertEquals(2, shop.getProducts().size());
        assertTrue(shop.getProducts().contains(bread));
        // Test list equality
        List<Product> expectedList = new ArrayList<>();
        expectedList.add(toaster);
        expectedList.add(bread);
        assertEquals(expectedList, shop.getProducts());
    }

    @Test
    void getInventory() {
        // Get inventory when no items are in shop
        assertEquals(0, shop.getInventory().size());
        // Add valid Product to Shop
        shop.addProduct(toaster);
        // Get inventory when Product in shop, but no quantity
        assertEquals(1, shop.getInventory().size());
        assertTrue(shop.getInventory().contains(toaster));
        // Add quantity of Product to shop
        shop.addInventory(toaster, 12);
        // Get inventory when Product in shop and has quantity
        assertTrue(shop.getInventory().contains(toaster));
    }

    @Test
    void getName() {
        assertEquals("Arthur's Appliances", shop.getName());
    }

    @Test
    void setName() {
        assertEquals("Arthur's Appliances", shop.getName());
        shop.setName("Arthur's Avocados");
        assertEquals("Arthur's Avocados", shop.getName());
    }

    @Test
    void addTag() {
        assertFalse(shop.getTags().contains(Tag.ENTERTAINMENT));
        shop.addTag(Tag.ENTERTAINMENT);
        assertTrue(shop.getTags().contains(Tag.ENTERTAINMENT));
    }

    @Test
    void removeTag() {
        // Remove existing tag
        assertFalse(shop.getTags().contains(Tag.ENTERTAINMENT));
        shop.addTag(Tag.ENTERTAINMENT);
        assertTrue(shop.getTags().contains(Tag.ENTERTAINMENT));

        // Try to remove from empty
        assertEquals(0, noTagShop.getTags().size());
        noTagShop.removeTag(Tag.ENTERTAINMENT);
        assertEquals(0, noTagShop.getTags().size());
    }


    @Test
    void testToString() {
        // Empty shop
        String expected = "Shop [name=Arthur's Appliances, id=*, Merchant=(User [Name: Arthur, Type: Merchant]), productList=[], inventory=[], tags=[BABY, GROCERY, APPLIANCES]]";
        // id only exists if persisted, which it is not for testing this method.
        String pattern = "id=(null|\\d+)";
        String actual = shop.toString();
        String normActual = actual.replaceAll(pattern, "id=*");
        assertEquals(expected, normActual);
        // Add valid Product to Shop
        shop.addProduct(toaster);
        // Add quantity of Product to shop
        shop.addInventory(toaster, 12);
        // Shop with inventory
        System.out.println(shop.toString());
        expected = "Shop [name=Arthur's Appliances, id=*, Merchant=(User [Name: Arthur, Type: Merchant]), productList=[Product [name=Toaster, price=12.99, tags=[]]], inventory=[[product=Product [name=Toaster, price=12.99, tags=[]], quantity=12]], tags=[BABY, GROCERY, APPLIANCES]]";
        assertEquals(expected, shop.toString().replaceAll(pattern, "id=*"));
    }
}