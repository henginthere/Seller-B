package backend.sellerB.repository;

import backend.sellerB.entity.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {
    Optional<ProductGroup> findByProductGroupName(String productGroupName);
}
