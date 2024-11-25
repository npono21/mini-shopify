package sysc4806.group7.mini_shopify;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/registerMerchant")
    public String registerMerchant() {
        return "merchant_register";
    }

    @GetMapping("/loginMerchant")
    public String loginMerchant() {
        return "merchant_login";
    }

    @GetMapping("/registerBuyer")
    public String registerBuyer(Model model) {
        return "buyer_register";
    }

    @GetMapping("/loginBuyer")
    public String loginBuyer(Model model) {
        return "buyer_login";
    }

    @GetMapping("/generalError")
    public String generalError(Model model) {
        return "general_error";
    }

    @GetMapping("/credentialError")
    public String credentialError(Model model) {
        return "credential_error";
    }

}
