package sysc4806.group7.mini_shopify;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AccessingDataJpaApplication {
    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    @Bean
    public Logger logger() {
       return LoggerFactory.getLogger("logger");
    }

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner demo(CartRepository repository, BuyerRepository buyerRepository, MerchantRepository merchantRepository) {
        return (args) -> {
            Merchant merchant = new Merchant("tim", "TT", "tom");

            Shop shop = new Shop("U", "des", merchant, new ArrayList<>(List.of(Tag.GROCERY)));
            Product eggs = new Product("Eggs", "some eggs", 3.00, "/images/jakub-kapusnak-Hj53USePB1E-unsplash.jpg", shop);
            shop.addProduct(eggs);
            shop.addInventory(eggs, 4);
            Product milk = new Product("Milk", "some milk",4.50, "/images/eiliv-aceron-_8bnn1GqX70-unsplash.jpg", shop);
            shop.addProduct(milk);
            shop.addInventory(milk, 2);
            Cart cart = new Cart();
            cart.addItems(eggs, 2);
            cart.addItems(milk, 1);

            merchant.addShop(shop);
            merchantRepository.save(merchant);

            Buyer buyer = new Buyer("John", "JD", "Doe");
            buyerRepository.save(buyer);
        };
    }*/
}
