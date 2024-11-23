package SYSC4806Project;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class MerchantController {

    @Autowired
    Logger logger;

    @Autowired
    MerchantRepository merchantRepository;

    @PostMapping("/createMerchant")
    public String createMerchant(@RequestParam String username, @RequestParam String password, Model model) {
        Merchant merchant = new Merchant(username, password);
        model.addAttribute("merchant", merchant);
        merchantRepository.save(merchant);
        return "redirect:/" + merchant.getId();
    }
    @GetMapping("/{merchantId}")
    public String getMerchant(@PathVariable Long merchantId, Model model) {
        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
        if (merchant.isPresent()) {
            model.addAttribute("merchant", merchant.get());
            return "merchant_home";
        }
        else {
            return "error";
        }
    }
    @PostMapping("/{merchantId}/createShop")
    public String createShop(@PathVariable Long merchantId, @RequestParam String shopName, @RequestParam String shopDescription, @RequestParam ArrayList<Tag> shopTags, Model model) {
        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
        if (merchant.isPresent()) {
            // Log list of received tags
            logger.debug("Tags for shop" + shopName + ":");
            for (Tag tag : shopTags) {
                logger.debug(tag.toString());
            }
            Shop shop = new Shop(shopName,shopDescription,merchant.get(), shopTags);
            merchant.get().addShop(shop);
            merchantRepository.save(merchant.get());
            return "redirect:/" + merchant.get().getId();
        }
        else {
            return "error";
        }
    }
}