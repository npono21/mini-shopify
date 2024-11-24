package sysc4806.group7.mini_shopify;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ShopRepository extends CrudRepository<Shop, Long> {
    Optional<Shop> findById(Long id);
    Optional<Shop> findByIdAndMerchantId(Long id, Long merchantId);
    Optional<Shop> findByName(String shopName);
    List<Shop> findAll();
}
