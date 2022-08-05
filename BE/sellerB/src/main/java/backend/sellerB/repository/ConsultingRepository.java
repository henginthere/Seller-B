package backend.sellerB.repository;

import backend.sellerB.entity.Consulting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultingRepository extends JpaRepository<Consulting, Long> {
    List<Consulting> findAllByCustomer_CustomerId(String customerId);
    List<Consulting> findAllByConsultant_ConsultantId(String consultantId);
}
