package backend.sellerB.dto;

import backend.sellerB.entity.Address;
import backend.sellerB.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AddressDto {
    private Customer customer;
    private String addr;
    private String addrRequest;

    public static AddressDto from(Address address) {
        if(address == null) return null;
        return AddressDto.builder()
                .customer(address.getCustomer())
                .addr(address.getAddr())
                .addrRequest(address.getAddrRequests())
                .build();
    }

    public static ArrayList<AddressDto> fromList(List<Address> addressList) {
        ArrayList<AddressDto> listAddressDto = new ArrayList<>();
        int i = 0;
        while (i < addressList.size()) {
            AddressDto addressDto = AddressDto.builder()
                    .customer(addressList.get(i).getCustomer())
                    .addr(addressList.get(i).getAddr())
                    .addrRequest(addressList.get(i).getAddrRequests())
                    .build();
            listAddressDto.add(addressDto);
            i++;
        }
        return listAddressDto;
    }
}
