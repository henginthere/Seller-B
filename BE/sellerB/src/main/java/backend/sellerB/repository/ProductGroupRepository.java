package backend.sellerB.repository;

import backend.sellerB.entity.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Integer> {
}
