package sysc4806.group7.mini_shopify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class MerchantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createMerchant() throws Exception {
        String name = "bob";
        String username = "bob@gmail.com";
        String password = "test";
        mockMvc.perform(post("/home/merchant/createMerchant")
                        .param("name", name)
                        .param("username", username)
                        .param("password", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home/merchant/3")); // TODO: find out why demo is being run at the same time as test
    }

    @Test
    public void addProduct() throws Exception {
        /**
        // post request
        MockMultipartFile testImage = new MockMultipartFile("data", "filename.png", "text/plain", "some xml".getBytes());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/home/1/1/addProduct")
                        .file("select_product_img", testImage.getBytes())
                        .param("productName", "test")
                        .param("productDescription", "test")
                        .param("productPrice", "100")
                        .param("quantity", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("product"))
                .andExpect(redirectedUrl("/home/1/1"));
        */
    }

    @Test
    public void showMerchantHome() {
    }

    @Test
    public void createShop() {
    }

    @Test
    public void showShopHome() {
    }
}