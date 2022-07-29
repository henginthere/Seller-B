package backend.sellerB.service;

import backend.sellerB.dto.AddressDto;
import backend.sellerB.entity.Address;
import backend.sellerB.repository.AddressRepository;
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

    public AddressDto create(AddressDto addressDto) {
        Address address = Address.builder()
                .customer(addressDto.getCustomer())
                .addr(addressDto.getAddr())
                .addrRequests(addressDto.getAddrRequest())
                .build();

        return AddressDto.from(addressRepository.save(address));
    }

    public List<AddressDto> getAddressList(String customerId) { return AddressDto.fromList(addressRepository.findAddressesByCustomer_CustomerId(customerId));}

    public AddressDto getAddressDetail(Integer seq) {
        Optional<Address> addressOptional = addressRepository.findById(seq);
        Address address = addressOptional.get();
        return AddressDto.from(address);
    }

    public AddressDto updateAddress(Integer seq, AddressDto addressDto) {
        Optional<Address> addressOptional = addressRepository.findById(seq);
        Address address = addressOptional.get();
        address.setCustomer(addressDto.getCustomer());
        address.setAddr(addressDto.getAddr());
        address.setAddrRequests(addressDto.getAddrRequest());
        return AddressDto.from(address);
    }

    public AddressDto deleteAddress(Integer seq) {
        Optional<Address> addressOptional = addressRepository.findById(seq);
        Address address = addressOptional.get();
        addressRepository.deleteById(seq);
        return AddressDto.from(address);
    }
}
