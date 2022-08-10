package backend.sellerB.service;

import backend.sellerB.dto.AddressDto;
import backend.sellerB.dto.RegisterAddressDto;
import backend.sellerB.dto.ResponseAddressDetailDto;
import backend.sellerB.entity.Address;
import backend.sellerB.entity.Customer;
import backend.sellerB.repository.AddressRepository;
import backend.sellerB.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PreUpdate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    public RegisterAddressDto create(RegisterAddressDto registerAddressDto) {
        Optional<Customer> customerOptional = customerRepository.findById(registerAddressDto.getCustomerSeq());
        Customer customer = customerOptional.get();
        Address address = Address.builder()
                .customer(customer)
                .addr(registerAddressDto.getAddr())
                .addrRequests(registerAddressDto.getAddrRequest())
                .build();

        return RegisterAddressDto.from(addressRepository.save(address));
    }

    public List<RegisterAddressDto> getAddressList(Long customerSeq) {
        Optional<List<Address>> addressOptionalList = addressRepository.findAddressByCustomer_CustomerSeq(customerSeq);
        List<Address> addressList = addressOptionalList.get();
        return RegisterAddressDto.fromList(addressList);
    }

    public RegisterAddressDto getAddressDetail(Long seq) {
        Optional<Address> addressOptional = addressRepository.findById(seq);
        Address address = addressOptional.get();


        return RegisterAddressDto.from(address);
    }

    @PreUpdate
    public RegisterAddressDto updateAddress(Long seq, RegisterAddressDto registerAddressDto) {
        Optional<Address> addressOptional = addressRepository.findById(seq);
        Address address = addressOptional.get();
        address.setCustomer(address.getCustomer());
        address.setAddr(registerAddressDto.getAddr());
        address.setAddrRequests(registerAddressDto.getAddrRequest());
        return RegisterAddressDto.from(address);
    }

    public RegisterAddressDto deleteAddress(Long seq) {
        Optional<Address> addressOptional = addressRepository.findById(seq);
        Address address = addressOptional.get();
        addressRepository.deleteById(seq);
//        address.setAddrDelYn(true);
        return RegisterAddressDto.from(address);
    }
}
