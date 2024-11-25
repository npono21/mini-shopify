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
    private MockMvc mockMvc;
    private String urlString;
    private String searchString;

    @Test
    public void getSearchResults() throws Exception {

        // The endpoint under test
        urlString = "/home/search/results";

        // Test: No search term
        searchString = "";
        mockMvc.perform(get(urlString)
                        .param("searchString", searchString))
                .andExpect(status().isOk());

        // Test: Single search term
        searchString = "baby";
        mockMvc.perform(get(urlString)
                        .param("searchString", searchString))
                .andExpect(status().isOk());

        // Test: Multiple search terms
        searchString = "baby appliances grocery";
        mockMvc.perform(get(urlString)
                        .param("searchString", searchString))
                .andExpect(status().isOk());
    }

}