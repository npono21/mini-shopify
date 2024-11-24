package sysc4806.group7.mini_shopify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BuyerTest {
    Buyer b1;
    Merchant m1;
    Shop s1;
    ArrayList<Tag> tags;

    @BeforeEach
    void setUp() {
        b1 = new Buyer("Rebecca", "ult1m4t3_fr1sb33");
        m1 = new Merchant("Arthur", "zinch");
        tags = new ArrayList<>();
        tags.add(Tag.BABY);
        tags.add(Tag.GROCERY);
        tags.add(Tag.APPLIANCES);
        s1 = new Shop("Arthur's Assorted Goods", "A shop", m1, tags);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getShop() {

    }
}