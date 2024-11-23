package SYSC4806Project;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ItemQuantityPairRepository extends CrudRepository<ItemQuantityPair, Long> {
    Optional<ItemQuantityPair> findById(Long id);
}
