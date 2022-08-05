package backend.sellerB.repository;

import backend.sellerB.entity.Orderdetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<Orderdetail, Long> {
}
