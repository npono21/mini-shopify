package sysc4806.group7.mini_shopify;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@org.springframework.stereotype.Controller
@RequestMapping("/home/search")
public class SearchController {

    @Autowired
    Logger logger;

    @Autowired
    ShopRepository shopRepository;

    @GetMapping("/results")
    public String getSearchResults(@RequestParam String searchString, Model model) {

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

        // Create a SET of Shops to accumulate search hits and avoid dupes
        Set<Shop> shopResults = new HashSet<>();

        /* Get Shops */

        // Get all of them at once rather than relying on different CRUDRepository
        // methods, since we are going to search for BOTH name and tag hits, and
        // this simplifies that process as we loop. Yes, this is a bigger read,
        // but it is only one read.

        // findAll() returns an Iterable, so cast to ArrayList() for search
        Iterable<Shop> shopsIterable = shopRepository.findAll();
        List<Shop> allShops = new ArrayList<>();
        for (Shop shop : shopsIterable) {
            // Exclude any nulls, if they occur
            if (shop.getName() != null) {
                allShops.add(shop);
            }
        }

        // Debug
        logger.info("Found " + allShops.size() + " shops:");
        for (Shop shop : allShops) {
            logger.info("\t" + shop.getName());
        }

        /* Perform the Search */

        // To meet requirements, we perform a single unified search by both
        // Shop NAME and Shop TAGS. So, a user can enter any string they like,
        // and each whitespace-separated substring is treated as a single
        // search term for both criteria.
        //
        // To keep us case-insensitive, all pattern matching is done in
        // uppercase.
        //
        // We also return substring matches to make the search more
        // user-friendly. So, for example, if a user searches for "electronic",
        // they will still catch results for the tag "ELECTRONICS".

        // Loop: Shops
        for (Shop shop : allShops) {
            // Loop: Search Terms
            for (String searchTerm: searchTerms) {
                // Check: substring match of searchTerm in Shop name
                if (shop.getName().toUpperCase().contains(searchTerm.toUpperCase())) {
                    // Accumulate Shop
                    shopResults.add(shop);
                    logger.info("Shop [" + shop.getName() + "] found with NAME matching search term: [" + searchTerm + "]");
                }
                // Loop: Tags
                for (Tag tag : shop.getTags()) {
                        // Check: tag match of searchTerm in Shop tags
                        if (tag.toString().toUpperCase().contains(searchTerm.toUpperCase())) {
                            // Accumulate Shop
                            shopResults.add(shop);
                            logger.info("Shop [" + shop.getName() + "] found with TAG matching search term: [" + searchTerm + "]");
                        }
                }
            }
        }

        // List all Shops found matching Name or Tag query
        logger.info("Returning " + shopResults.size() + " matching shops:");
        for (Shop shop : shopResults) {
            logger.info("\t" + shop.getName());
        }

        // Add the results to the model
        model.addAttribute("shopResults", shopResults);

        // Return the template as a view
        return "search_results";
    }
    @GetMapping("/{shopId}")
    public String showShopHome(@PathVariable Long shopId, Model model) {
        Optional<Shop> shop = shopRepository.findById(shopId);
        if (shop.isPresent()) {
            model.addAttribute("shop", shop.get());
            return "shop_home";
        } else {
            return "general_error";
        }
    }

}