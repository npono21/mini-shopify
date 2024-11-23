package sysc4806.group7.mini_shopify;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

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

    @Bean
    public CommandLineRunner demo(CartRepository repository) {
        return (args) -> {
            Shop shop = new Shop();
            Product eggs = new Product("Eggs", "some eggs", 3.00, "/images/jakub-kapusnak-Hj53USePB1E-unsplash.jpg", shop);
            shop.addProduct(eggs);
            shop.addInventory(eggs, 4);
            Product milk = new Product("Milk", "some milk",4.50, "/images/eiliv-aceron-_8bnn1GqX70-unsplash.jpg", shop);
            shop.addProduct(milk);
            shop.addInventory(milk, 2);
            Cart cart = new Cart();
            cart.addItems(eggs, 2);
            cart.addItems(milk, 1);

            repository.save(cart);


            log.info("Carts found with findAll():");
            log.info("-------------------------------");
            repository.findAll().forEach(cart1 -> {
                log.info(cart1.toString());
            });
            log.info("");


            Optional<Cart> cart2 = repository.findById(1L);
            log.info("Cart found with findById(1L):");
            log.info("--------------------------------");
            log.info(cart2.get().toString());
            log.info("");
        };
    }
}
