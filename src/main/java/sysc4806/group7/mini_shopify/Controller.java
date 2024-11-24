package sysc4806.group7.mini_shopify;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/registerMerchant")
    public String registerMerchant() {
        return "merchant_register";
    }
    @GetMapping("/merchantLogin")
    public String merchantLogin() {
        return "merchant_login";
    }
    @GetMapping("/loginMerchant")
    public String loginMerchant(Model model) {
        return "merchant_home";
    }
}