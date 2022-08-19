package backend.sellerB.repository;

import backend.sellerB.entity.CustomerWaitingPage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerWaitingPageRepository extends JpaRepository<CustomerWaitingPage, Long> {
    Optional<CustomerWaitingPage> findByProduct_ProductSeq(Long seq);
}
