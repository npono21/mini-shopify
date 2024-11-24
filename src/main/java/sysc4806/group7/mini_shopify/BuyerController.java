package sysc4806.group7.mini_shopify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@org.springframework.stereotype.Controller
public class BuyerController {

    @Autowired
    BuyerRepository buyerRepository;

    @PostMapping("/createBuyer")
    public String createMerchant(@RequestParam String name, @RequestParam String username, @RequestParam String password, Model model) {
        Buyer buyer = new Buyer(name, username, password);
        buyerRepository.save(buyer);
        model.addAttribute("buyer", buyer);
        return "redirect:/" + buyer.getId();
    }
    @GetMapping("/{buyerId}")
    public String showMerchantHome(@PathVariable Long merchantId, Model model) {
        Optional<Buyer> buyer = buyerRepository.findById(merchantId);
        if (buyer.isPresent()) {
            model.addAttribute("buyer", buyer.get());
            return "buyer_home";
        }
        else {
            return "error";
        }
    }
}