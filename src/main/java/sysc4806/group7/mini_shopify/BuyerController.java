package sysc4806.group7.mini_shopify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
@RequestMapping("/home/buyer")
public class BuyerController {

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    ShopRepository shopRepository;

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
        return "credential_error";
    }
    @GetMapping("/{buyerId}")
    public String showBuyerHome(@PathVariable Long buyerId, Model model) {
        Optional<Buyer> buyer = buyerRepository.findById(buyerId);
        if (buyer.isPresent()) {
            model.addAttribute("buyer", buyer.get());
            return "buyer_home";
        }
        else {
            return "general_error";
        }
    }

    @PostMapping("/{buyerId}/{shopId}")
    public String addProductToCart(@PathVariable Long buyerId, @PathVariable Long shopId,
                                   @RequestParam String productName,
                                   @RequestParam String productDescription,
                                   @RequestParam double productPrice,
                                   @RequestParam(required = false) byte[] productImg,
                                   @RequestParam int quantity,
                                   RedirectAttributes redirectAttributes,
                                   Model model) {
        Optional<Buyer> buyer = buyerRepository.findById(buyerId);
        Optional<Shop> shop = shopRepository.findById(shopId);
        if (buyer.isPresent() && shop.isPresent()) {
            Cart cart = buyer.get().getCart();
            Product product;
            product = new Product(productName, productDescription, productPrice, productImg, shop.get());
            cart.addProduct(product);
            try {
                model.addAttribute("buyer", buyer.get());
                model.addAttribute("buyerId", buyer.get().getId());
                model.addAttribute("shop", shop.get());
                cart.addItems(product, quantity);
                buyerRepository.save(buyer.get());
            } catch (Exception e) {
                System.out.println("p: " + productName);
                redirectAttributes.addFlashAttribute("alertItemName", productName);
                redirectAttributes.addFlashAttribute("alertMessage", "Insufficient inventory. No items added to cart.");
            }
            return "redirect:/home/search/" + shopId + "?buyerId=" + buyerId;
        } else {
            return "general_error";
        }
    }
}