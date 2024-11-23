package SYSC4806Project;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MerchantRepository extends CrudRepository<Merchant, Long> {
    Optional<Merchant> findById(Long id);
    Optional<Merchant> findByName(String name);
}
