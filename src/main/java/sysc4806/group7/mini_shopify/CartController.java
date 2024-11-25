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
    CartRepository cartRepository;

    // support for thymeleaf to hit @DeleteMapping endpoint
    @Bean
    public FilterRegistrationBean<HiddenHttpMethodFilter> hiddenHttpMethodFilter() {
        return new FilterRegistrationBean<>(new HiddenHttpMethodFilter());
    }


    @GetMapping("/{cartId}")
    public String getCart(@PathVariable Long cartId, Model model) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if (cart.isPresent()) {
            model.addAttribute("cart", cart.get());
            return "cart_home";
        } else {
            return "general_error";
        }

    }

    @DeleteMapping("/{cartId}/{productId}")
    public String deleteProductFromCart(@PathVariable Long cartId, @PathVariable Long productId, Model model, RedirectAttributes redirectAttributes) {
        Optional<Cart> result = cartRepository.findById(cartId);
        if (result.isPresent()) {
            Cart cart = result.get();
            cart.removeProduct(productId);
            cartRepository.save(cart);
            model.addAttribute("cart", cart);
        } else {
            return "general_error";
        }
        return "cart_home";
    }

    @PostMapping("/{cartId}/update/{productId}")
    public String updateProductQuantityInCart(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam int quantity, Model model, RedirectAttributes redirectAttributes) {
        Optional<Cart> result = cartRepository.findById(cartId);
        if (result.isPresent()) {
            Cart cart = result.get();
            try {
                cart.updateQuantity(productId, quantity);
                cartRepository.save(cart);
                model.addAttribute("cart", cart);
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("alertMessage", e.getMessage());
                redirectAttributes.addFlashAttribute("alertItemId", productId);
            }
        } else {
            return "general_error";
        }
        return "redirect:/home/carts/" + cartId;
    }
}
