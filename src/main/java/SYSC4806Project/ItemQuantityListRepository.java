package SYSC4806Project;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ItemQuantityListRepository extends CrudRepository<ItemQuantityList, Long> {
    Optional<ItemQuantityList> findById(Long id);
}
