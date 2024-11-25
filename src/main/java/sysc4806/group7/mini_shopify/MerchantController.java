package sysc4806.group7.mini_shopify;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;

import java.util.ArrayList;

@org.springframework.stereotype.Controller
@RequestMapping("/home/merchant")
public class MerchantController {

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    Logger logger;

    @PostMapping("/createMerchant")
    public String createMerchant(@RequestParam String name, @RequestParam String username, @RequestParam String password, Model model) {
        Merchant merchant = new Merchant(name, username, password);
        merchantRepository.save(merchant);
        model.addAttribute("merchant", merchant);
        return "redirect:/home/merchant/" + merchant.getId();
    }

    @GetMapping("/{merchantId}")
    public String showMerchantHome(@PathVariable Long merchantId, Model model) {
        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
        if (merchant.isPresent()) {
            model.addAttribute("merchant", merchant.get());
            return "merchant_home";
        } else {
            return "general_error";
        }
    }

    @PostMapping("/signinMerchant")
    public String signinMerchant(@RequestParam String username, @RequestParam String password, Model model) {
        List<Merchant> merchants = merchantRepository.findAll();
        for (Merchant merchant : merchants) {
            if (merchant.login(username, password)) {
                model.addAttribute("merchant", merchant);
                return "redirect:/home/merchant/" + merchant.getId();
            }
        }
        return "credential_error";
    }

    @PostMapping("/{merchantId}/createShop")
    public String createShop(@PathVariable Long merchantId, @RequestParam String shopName, @RequestParam String shopDescription, @RequestParam(required=false) ArrayList<Tag> shopTags, Model model) {
        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
        Shop shop;
        if (merchant.isPresent()) {
            // Case: Tags POSTed
            if (shopTags != null) {
                logger.info("Adding " + shopTags.size() + " tag(s) for shop " + shopName + ".");
                for (Tag tag : shopTags) {
                    logger.debug(tag.toString());
                }
                // Create the Shop with Tags
                shop = new Shop(shopName,shopDescription,merchant.get(), shopTags);
            }
            // Case: No Tags POSTed
            else {
                logger.info("No tags argued for shop " + shopName + "; creating shop without tags.");
                // Create the Shop without Tags
                shop = new Shop(shopName,shopDescription,merchant.get());
            }

            // Attach Shop to Merchant
            merchant.get().addShop(shop);
            merchantRepository.save(merchant.get());
            model.addAttribute("shop", shop);
            return "redirect:/home/merchant/" + merchant.get().getId();
        } else {
            return "general_error";
        }
    }

    @GetMapping("/{merchantId}/shop/{shopId}")
    public String showShopHome(@PathVariable Long merchantId, @PathVariable Long shopId, Model model) {
        Optional<Shop> shop = shopRepository.findById(shopId);
        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
        if (shop.isPresent() && merchant.isPresent()) {
            model.addAttribute("shop", shop.get());
            model.addAttribute("merchant", merchant.get());
            return "merchant_shop";
        } else {
            return "general_error";
        }
    }

    @PostMapping("/{merchantId}/shop/{shopId}/addProduct")
    public String addProduct(@PathVariable Long merchantId,
                             @PathVariable Long shopId,
                             @RequestParam String productName,
                             @RequestParam String productDescription,
                             @RequestParam double productPrice,
                             @RequestParam int quantity,
                             @RequestParam("select_product_img") MultipartFile productImg,
                             Model model) {
        Optional<Shop> shop = shopRepository.findById(shopId);
        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
        if (shop.isPresent() && merchant.isPresent()) {
            for (Product product: shop.get().getProducts()){
                if (product.getName().equals(productName)){
                    // TODO: handle duplicated product name
                    return "redirect:/home/merchant/" + merchant.get().getId() + "/shop/" + shop.get().getId();
                }
            }
            // TODO: handle product image
            Product product = new Product(productName, productDescription, productPrice);
            shop.get().addProduct(product);
            shop.get().addInventory(product, quantity);
            shopRepository.save(shop.get());
            return "redirect:/home/merchant/" + merchant.get().getId() + "/shop/" + shop.get().getId();
        }
        else {
            return "general_error";
        }
    }
}