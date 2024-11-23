package SYSC4806Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@org.springframework.stereotype.Controller
public class MerchantController {

    @Autowired
    MerchantRepository merchantRepository;

    @PostMapping("/createMerchant")
    public String createMerchant(@RequestParam String name, @RequestParam String username, @RequestParam String password, Model model) {
        Merchant merchant = new Merchant(name, username, password);
        model.addAttribute("merchant", merchant);
        merchantRepository.save(merchant);
        return "redirect:/" + merchant.getId();
    }
    @PostMapping("/loginMerchant")
    public String loginMerchant(@RequestParam String username, @RequestParam String password, Model model) {
        Iterable<Merchant> merchants = merchantRepository.findAll();

        for (Merchant merchant: merchants) {
            if (merchant.login(username, password)) {
                return "redirect:/" + merchant.getId();
            }
        }

        return "error";
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
    public String createShop(@PathVariable Long merchantId, @RequestParam String shopName, @RequestParam String shopDescription, Model model) {
        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
        if (merchant.isPresent()) {
            Shop shop = new Shop(shopName,shopDescription,merchant.get());
            merchant.get().addShop(shop);
            merchantRepository.save(merchant.get());
            return "redirect:/" + merchant.get().getId();
        }
        else {
            return "error";
        }
    }
}