package SYSC4806Project;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.SocketOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class SearchController {

    @Autowired
    Logger logger;

    @Autowired
    ShopRepository shopRepository;

    @GetMapping("/searchResults")
    public String getMerchant(@RequestParam String searchString, Model model) {

        // Log the string that the user has entered in search bar
        logger.info("Displaying shop results for search string: " + searchString);

        // Add the search string to the model
        model.addAttribute("searchString", searchString);

        // Split the string into tokens around whitespace
        String[] splits = searchString.split(" ");
        // Cast as an ArrayList instead of an Array because THIS. IS. JAVAAAAAAA.
        ArrayList<String> searchTerms = new ArrayList<>(Arrays.asList(splits));
        logger.info("Split search terms:");
        for (String searchTerm : searchTerms) {
            logger.info("\t" + searchTerm);
        }

        // Create a set of Shops to accumulate search hits and avoid dupes
        ArrayList<Shop> shopResults = new ArrayList<>();

        // NOTE: Can do this, but we're already going to go through our
        // results to check tags, so just do the work there.
        // Loop through our ArrayList of search terms, accumulating results.
        //for (String searchTerm : searchTerms) {
        //    // Query shops by name (can at most hit one, since unique).
        //    Optional<Shop> shopByName = shopRepository.findByName(searchTerm);
        //    if (shopByName.isPresent()) {
        //        shopResults.add(shopByName.get());
        //        logger.info("Shop with NAME matching search term: " + //searchTerm);
        //    }
        //}

        // Query shops by tags (as many hits possible as there are shops).
        // Note: Could do this by defining a ShopRepository method to filter
        // on tags, but I'm not convinced it would be more efficient.
        List<Shop> allShops = shopRepository.findAll();
        for (Shop shop : allShops) {
            logger.info("\t" + shop);
        }
        // Loop: Shops
        /*
        for (Shop shop : allShops) {
            // Loop: Search Terms
            for (String searchTerm: searchTerms) {
                // Check: substring match of searchTerm in Shop name
                if (shop.getName().toUpperCase().contains(searchTerm.toUpperCase())) {
                    // Accumulate Shop
                    shopResults.add(shop);
                    logger.info("Shop [" + shop.getName() + "] found with NAME matching search term: " + searchTerm);
                }
                // Loop: Tags
                for (Tag tag : shop.getTags()) {
                        // Check: tag match of searchTerm in Shop tags
                        if (tag.toString().toUpperCase().equals(searchTerm.toUpperCase())) {
                            // Accumulate Shop
                            shopResults.add(shop);
                            logger.info("Shop [" + shop.getName() + "] found with TAG matching search term: " + searchTerm);
                        }
                    }
                }
        }
         */

        // Add the results to the model
        model.addAttribute(searchString);

        // Return the template as a view
        return "search_results";
    }

}