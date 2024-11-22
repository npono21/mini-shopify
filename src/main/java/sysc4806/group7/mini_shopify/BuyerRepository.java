package sysc4806.group7.mini_shopify;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BuyerRepository extends CrudRepository<Buyer, Long> {
    Optional<Buyer> findById(Long id);
}