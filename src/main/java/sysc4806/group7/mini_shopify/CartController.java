package sysc4806.group7.mini_shopify;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/home/carts")
public class CartController implements WebMvcConfigurer {
    @Autowired
    BuyerRepository buyerRepository;

    // support for thymeleaf to hit @DeleteMapping endpoint
    @Bean
    public FilterRegistrationBean<HiddenHttpMethodFilter> hiddenHttpMethodFilter() {
        return new FilterRegistrationBean<>(new HiddenHttpMethodFilter());
    }


    @GetMapping("/{buyerId}")
    public String getCart(@PathVariable Long buyerId, Model model) {
        Optional<Buyer> buyer = buyerRepository.findById(buyerId);
        if (buyer.isPresent()) {
            model.addAttribute("buyerId", buyer.get().getId());
            model.addAttribute("cart", buyer.get().getCart());
            return "cart_home";
        } else {
            return "general_error";
        }

    }

    @DeleteMapping("/{buyerId}/{productId}")
    public String deleteProductFromCart(@PathVariable Long buyerId, @PathVariable Long productId, Model model, RedirectAttributes redirectAttributes) {
        Optional<Buyer> buyer = buyerRepository.findById(buyerId);
        if (buyer.isPresent()) {
            Cart cart = buyer.get().getCart();
            cart.removeProduct(productId);
            buyerRepository.save(buyer.get());
            model.addAttribute("buyerId", buyer.get().getId());
            model.addAttribute("cart", cart);
        } else {
            return "general_error";
        }
        return "cart_home";
    }

    @PostMapping("/{buyerId}/update/{productId}")
    public String updateProductQuantityInCart(@PathVariable Long buyerId, @PathVariable Long productId, @RequestParam int quantity, Model model, RedirectAttributes redirectAttributes) {
        Optional<Buyer> result = buyerRepository.findById(buyerId);
        if (result.isPresent()) {
            Cart cart = result.get().getCart();
            try {
                cart.updateQuantity(productId, quantity);
                buyerRepository.save(result.get());
                model.addAttribute("cart", cart);
                model.addAttribute("buyerId", buyerId);
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("alertMessage", e.getMessage());
                redirectAttributes.addFlashAttribute("alertItemId", productId);
            }
        } else {
            return "general_error";
        }
        return "redirect:/home/carts/" + buyerId;
    }
}
