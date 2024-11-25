package sysc4806.group7.mini_shopify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // This will mock the BuyerRepository for the test
    private BuyerRepository buyerRepository;

    @BeforeEach
    void setUp() {
        // Mocking the repository behavior for test cases
        Buyer testBuyer = new Buyer();
        testBuyer.setId(1L);
        System.out.println("Setup");

        // Mock the repository response
        Mockito.when(buyerRepository.findById(1L)).thenReturn(Optional.of(testBuyer));
        Mockito.when(buyerRepository.findById(999L)).thenReturn(Optional.empty());
    }

    @Test
    void testGetCart_ExistingBuyer() throws Exception {
        mockMvc.perform(get("/home/carts/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("cart_home"))
                .andExpect(model().attributeExists("buyerId"))
                .andExpect(model().attributeExists("cart"));
    }

    @Test
    void testGetCart_NonExistingBuyer() throws Exception {
        mockMvc.perform(get("/home/carts/999"))
                .andExpect(status().isOk())
                .andExpect(view().name("general_error"));
    }
}


