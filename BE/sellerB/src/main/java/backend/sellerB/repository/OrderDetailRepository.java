package backend.sellerB.repository;

import backend.sellerB.entity.Orderdetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<Orderdetail, Long> {

    Optional<List<Orderdetail>> findByOrder_OrderSeq(Long seq);
}
