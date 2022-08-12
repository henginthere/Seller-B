package backend.sellerB.repository;

import backend.sellerB.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<List<Review>> findByConsulting_Consultant_ConsultantSeq(Long seq);
    Optional<List<Review>> findByConsulting_Customer_CustomerSeq(Long seq);
}
