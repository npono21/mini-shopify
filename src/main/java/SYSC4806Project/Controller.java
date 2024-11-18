package SYSC4806Project;

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
    @GetMapping("/merchantHome")
    public String loginMerchant(Model model) {
        return "merchant_home";
    }

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/shopperLogin")
    public String shopperLogin(Model model) {
        return "shopper_login";
    }

    @GetMapping("/registerShopper")
    public String registerShopper(Model model) {
        return "shopper_register";
    }
}