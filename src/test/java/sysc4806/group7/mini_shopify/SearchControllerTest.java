package sysc4806.group7.mini_shopify;

import jakarta.persistence.Access;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class SearchControllerTest {

    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    MerchantRepository merchantRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    private MockMvc mockMvc;

    Buyer buyer;
    Merchant merchant;
    Shop s0;
    Shop s1;
    Shop s2;
    ArrayList<Tag> t1;
    ArrayList<Tag> t2;
    String searchString;
    String response;


    @Test
    public void getSearchResults() throws Exception {

        /* Setup */
        // Buyer
        buyer = new Buyer("Rebecca", "rebecca_123", "ult1m4t3_fr1sb33");
        buyerRepository.save(buyer);

        // Merchant
        merchant = new Merchant("Arthur", "arthur_123", "zinch");
        merchantRepository.save(merchant);

        // Tag: t1 (one tag)
        t1 = new ArrayList<>();
        t1.add(Tag.BABY);
        // Tag: t2 (several tags)
        t2 = new ArrayList<>();
        t2.add(Tag.BABY);
        t2.add(Tag.APPLIANCES);
        t2.add(Tag.GROCERY);
        // Shop: s0 (no tags)
        s0 = new Shop("No Tag Shop", "sells something", merchant);
        merchant.addShop(s0);
        // Shop: s1 (one tag)
        s1 = new Shop("One Tag Shop", "sells baby stuff only", merchant);
        merchant.addShop(s1);
        // Shop: s2 (several tags)
        s2 = new Shop("One Tag Shop", "sells babies, appliances, and groceries", merchant);
        merchant.addShop(s2);

        /* Test */

        searchString = "baby";
        // Test our endpoint.
        mockMvc.perform(get("/home/search/results")
                        .param("searchString", searchString))
                .andExpect(status().isOk());
    }

}