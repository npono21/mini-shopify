package SYSC4806Project.testRepos;

import SYSC4806Project.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartTestRepository extends JpaRepository<Cart, Long> {
}
