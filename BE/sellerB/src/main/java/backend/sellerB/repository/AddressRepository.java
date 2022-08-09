package backend.sellerB.repository;

import backend.sellerB.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<List<Address>> findAddressesByCustomer_CustomerId(String customerId);
    Optional<List<Address>> findAddressByCustomer_CustomerSeq(Long customerSeq);
}
