package SYSC4806Project;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Optional<Cart> findById(Long id);
}
