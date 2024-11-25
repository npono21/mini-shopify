package sysc4806.group7.mini_shopify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class MerchantControllerTest {
    // TODO: fix, demo is running when tests are
    int merchantId = 3;
    int shopId = 1;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MerchantRepository merchantRepository;


    @Test
    public void createMerchantTest() throws Exception {
        mockMvc.perform(post("/home/merchant/createMerchant")
                        .param("name", "bob")
                        .param("username", "bob@gmail.com")
                        .param("password", "test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home/merchant/" + merchantId));
    }

    @Test
    public void signingMerchantTest() throws Exception {
        // create merchant
        mockMvc.perform(post("/home/merchant/createMerchant")
                .param("name", "bob")
                .param("username", "bob@gmail.com")
                .param("password", "test"));
        // signing merchant
        mockMvc.perform(post("/home/merchant/signinMerchant")
                        .param("username", "bob@gmail.com")
                        .param("password", "test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home/merchant/" + merchantId));
    }

    @Test
    public void createShopTest() throws Exception {
        // mock a merchant
        mockMvc.perform(post("/home/merchant/createMerchant")
                .param("name", "bob")
                .param("username", "bob@gmail.com")
                .param("password", "test"));
        // create a shop
        mockMvc.perform(post("/home/merchant/1/createShop")
                        .param("shopName", "shop1")
                        .param("shopDescription", "shop1's shop description")
                        .param("shopTags", "COMPUTERS"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home/merchant/1"));
    }

    @Test
    public void showMerchantHomeTest() throws Exception {
        // mock a merchant
        mockMvc.perform(post("/home/merchant/createMerchant")
                .param("name", "bob")
                .param("username", "bob@gmail.com")
                .param("password", "test"));
        mockMvc.perform(get("/home/merchant/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("merchant"));

    }

    @Test
    public void showShopHomeTest() throws Exception {
        // mock a merchant
        mockMvc.perform(post("/home/merchant/createMerchant")
                .param("name", "bob")
                .param("username", "bob@gmail.com")
                .param("password", "test"));
        // create a shop
        mockMvc.perform(post("/home/merchant/1/createShop")
                .param("shopName", "shop1")
                .param("shopDescription", "shop1's shop description")
                .param("shopTags", "COMPUTERS"));
        // show shop home
        mockMvc.perform(get("/home/merchant/1/shop/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("shop"))
                .andExpect(model().attributeExists("merchant"));
    }

    @Test
    public void addProductTest() throws Exception {
        // mock a merchant
        mockMvc.perform(post("/home/merchant/createMerchant")
                .param("name", "bob")
                .param("username", "bob@gmail.com")
                .param("password", "test"));
        // create a shop
        mockMvc.perform(post("/home/merchant/1/createShop")
                        .param("shopName", "shop1")
                        .param("shopDescription", "shop1's shop description")
                        .param("shopTags", "COMPUTERS"));
        // add a product
        mockMvc.perform(MockMvcRequestBuilders.multipart("/home/merchant/1/shop/1/addProduct")
                        .file("select_product_img", "image.png".getBytes())
                        .param("productName", "test")
                        .param("productDescription", "test")
                        .param("productPrice", "100")
                        .param("quantity", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home/merchant/1/shop/1"));
    }

}