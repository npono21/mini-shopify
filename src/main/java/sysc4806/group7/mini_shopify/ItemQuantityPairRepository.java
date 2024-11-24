package sysc4806.group7.mini_shopify;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ItemQuantityPairRepository extends CrudRepository<ItemQuantityPair, Long> {
    Optional<ItemQuantityPair> findById(Long id);
}
