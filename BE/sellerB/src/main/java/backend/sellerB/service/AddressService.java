package backend.sellerB.service;

import backend.sellerB.dto.AddressDto;
import backend.sellerB.dto.RegisterAddressDto;
import backend.sellerB.entity.Address;
import backend.sellerB.entity.Customer;
import backend.sellerB.repository.AddressRepository;
import backend.sellerB.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    public RegisterAddressDto create(RegisterAddressDto registerAddressDto) {
        Optional<Customer> customerOptional = customerRepository.findBycustomerId(registerAddressDto.getCustomerId());
        Customer customer = customerOptional.get();
        Address address = Address.builder()
                .customer(customer)
                .addr(registerAddressDto.getAddr())
                .addrRequests(registerAddressDto.getAddrRequest())
                .build();

        return RegisterAddressDto.from(addressRepository.save(address));
    }

    public List<AddressDto> getAddressList(String customerId) {
        System.out.println(customerId);
        Optional<List<Address>> addressOptionalList = addressRepository.findAddressesByCustomer_CustomerId(customerId);
        List<Address> addressList = addressOptionalList.get();
        return AddressDto.fromList(addressList);
    }

    public AddressDto getAddressDetail(Long seq) {
        Optional<Address> addressOptional = addressRepository.findById(seq);
        Address address = addressOptional.get();
        return AddressDto.from(address);
    }

    public RegisterAddressDto updateAddress(Long seq, RegisterAddressDto registerAddressDto) {
        Optional<Address> addressOptional = addressRepository.findById(seq);
        Address address = addressOptional.get();
        address.setCustomer(address.getCustomer());
        address.setAddr(registerAddressDto.getAddr());
        address.setAddrRequests(registerAddressDto.getAddrRequest());
        return RegisterAddressDto.from(address);
    }

    public AddressDto deleteAddress(Long seq) {
        Optional<Address> addressOptional = addressRepository.findById(seq);
        Address address = addressOptional.get();
        addressRepository.deleteById(seq);
//        address.setAddrDelYn(true);
        return AddressDto.from(address);
    }
}
