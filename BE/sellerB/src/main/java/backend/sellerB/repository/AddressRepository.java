package backend.sellerB.repository;

import backend.sellerB.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAddressesByCustomer_CustomerId(String customerId);
}
