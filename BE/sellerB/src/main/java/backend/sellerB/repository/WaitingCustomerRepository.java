package backend.sellerB.repository;

import backend.sellerB.dto.WaitingCustomerDto;
import backend.sellerB.entity.WaitingCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WaitingCustomerRepository extends JpaRepository<WaitingCustomer, Long> {
    List<WaitingCustomer> findWaitingCustomersByProductGroup_ProductGroupName(String productGroupName);
}
