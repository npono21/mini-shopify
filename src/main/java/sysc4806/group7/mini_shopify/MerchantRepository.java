package sysc4806.group7.mini_shopify;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MerchantRepository extends CrudRepository<Merchant, Long> {
    Optional<Merchant> findById(Long id);
}
