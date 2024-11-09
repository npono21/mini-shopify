package SYSC4806Project;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@org.springframework.stereotype.Controller
public class MerchantController {

    MerchantRepository merchantRepository;

    @GetMapping("/{merchantId}")
    public String showMerchant(@PathVariable Long merchantId, Model model) {
        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
        if (merchant.isPresent()) {
            model.addAttribute("merchant", merchant);
        }
        return "merchant_home";
    }

    @PostMapping("/{merchantId}/createShop")
    public String createShop(@PathVariable Long merchantId, @RequestBody Shop shop, Model model) {
        return merchantRepository.findById(merchantId).map(merchant -> {
            shop.setMerchant(merchant);
            merchant.addShop(shop);
            merchantRepository.save(merchant);
            return "merchant_home";
        }).orElseGet(() -> "error");
    }

}
