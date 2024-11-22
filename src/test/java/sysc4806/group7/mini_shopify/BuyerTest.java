package sysc4806.group7.mini_shopify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BuyerTest {
    Buyer b1;
    Merchant m1;
    Shop s1;

    @BeforeEach
    void setUp() {
        b1 = new Buyer("Rebecca", "ult1m4t3_fr1sb33");
        m1 = new Merchant("Arthur", "zinch");
        s1 = new Shop("Arthur's Appliances", "A shop", m1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getShop() {

    }
}