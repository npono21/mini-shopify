package sysc4806.group7.mini_shopify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class MerchantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MerchantRepository merchantRepository;

    @MockBean
    private ShopRepository shopRepository;

    @Test
    public void contextLoads() throws Exception {
        assertThat(mockMvc).isNotNull();
    }


    @Test
    public void createMerchant() throws Exception {
        String username = "bob";
        String password = "test";
        mockMvc.perform(post("/createMerchant")
                        .param("username", username)
                        .param("password", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/1"));
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

/**
    @Test
    public void addProduct() throws Exception {
        // Mock Merchant and Shop Data
        Merchant merchant = mock(Merchant.class);
        merchant.setId(1L);
        Shop shop = mock(Shop.class);
        shop.setId(1L);
        shop.setMerchant(merchant);

        // Mock Repositories
        when(merchantRepository.findById(1L)).thenReturn(Optional.of(merchant));
        when(shopRepository.findByIdAndMerchantId(1L, 1L)).thenReturn(Optional.of(shop));

        // post request
        MockMultipartFile testImage = new MockMultipartFile("data", "filename.png", "text/plain", "some xml".getBytes());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/home/1/1/addProduct")
                        .file("select_product_img", testImage.getBytes())
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .param("productName", "test")
                        .param("productDescription", "test")
                        .param("productPrice", "100")
                        .param("quantity", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home/1/1"));
    }
 */
}