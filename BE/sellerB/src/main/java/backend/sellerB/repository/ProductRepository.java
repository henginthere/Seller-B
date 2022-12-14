package backend.sellerB.repository;

import backend.sellerB.dto.ProductRes;
import backend.sellerB.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findByProductNameContaining(String productName);
    Optional<Product> findByProductId(String productId);
    Optional<List<Product>> findByProductGroup_Brand_BrandSeq(Long seq);

    Optional<List<Product>> findProductsByProductGroup_ProductGroupName(String productGroupName);
    Optional<List<Product>> findProductsByProductGroup_ProductGroupSeq(Long productGroupSeq);
}
