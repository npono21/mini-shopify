package sysc4806.group7.mini_shopify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
@RequestMapping("/home/buyer")
public class BuyerController {

    @Autowired
    BuyerRepository buyerRepository;

    @PostMapping("/createBuyer")
    public String createBuyer(@RequestParam String name, @RequestParam String username, @RequestParam String password, Model model) {
        Buyer buyer = new Buyer(name, username, password);
        buyerRepository.save(buyer);
        model.addAttribute("buyer", buyer);
        return "redirect:/home/buyer/" + buyer.getId();
    }
    @PostMapping("/signinBuyer")
    public String signinBuyer(@RequestParam String username, @RequestParam String password, Model model) {
        List<Buyer> buyers = buyerRepository.findAll();
        
        for (Buyer buyer : buyers) {
            if (buyer.login(username, password)) {
                model.addAttribute("buyer", buyer);
                return "redirect:/home/buyer/" + buyer.getId();
            }
        }
        return "error";
    }
    @GetMapping("/{buyerId}")
    public String showBuyerHome(@PathVariable Long buyerId, Model model) {
        Optional<Buyer> buyer = buyerRepository.findById(buyerId);
        if (buyer.isPresent()) {
            model.addAttribute("buyer", buyer.get());
            return "buyer_home";
        }
        else {
            return "error";
        }
    }
}