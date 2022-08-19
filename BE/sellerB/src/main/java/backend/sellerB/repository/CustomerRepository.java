package backend.sellerB.repository;

import backend.sellerB.dto.EditCustomerDto;
import backend.sellerB.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findBycustomerId(String customerId);
    Optional<Customer> findBycustomerSeq(Long customerSeq);
}
