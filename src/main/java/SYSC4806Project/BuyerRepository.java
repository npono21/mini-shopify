package SYSC4806Project;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BuyerRepository extends CrudRepository<Buyer, Long> {
    Optional<Buyer> findById(Long id);
}