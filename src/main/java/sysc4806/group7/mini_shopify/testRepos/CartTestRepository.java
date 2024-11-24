package sysc4806.group7.mini_shopify.testRepos;

import sysc4806.group7.mini_shopify.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartTestRepository extends JpaRepository<Cart, Long> {
}
