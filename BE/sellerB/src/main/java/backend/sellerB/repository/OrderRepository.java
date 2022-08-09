package backend.sellerB.repository;

import backend.sellerB.entity.Order;
import backend.sellerB.entity.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<List<Order>> findByOrderRegUserSeq(Long seq);

}
