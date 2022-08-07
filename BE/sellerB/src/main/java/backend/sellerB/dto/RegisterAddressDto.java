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
public class RegisterAddressDto {
    private String customerId;
    private String addr;
    private String addrRequest;

    public static RegisterAddressDto from(Address address) {
        if(address == null) return null;
        return RegisterAddressDto.builder()
                .customerId(address.getCustomer().getCustomerId())
                .addr(address.getAddr())
                .addrRequest(address.getAddrRequests())
                .build();
    }

    public static ArrayList<RegisterAddressDto> fromList(List<Address> addressList) {
        ArrayList<RegisterAddressDto> listRegisterAddressDto = new ArrayList<>();
        int i = 0;
        while (i < addressList.size()) {
            RegisterAddressDto registerAddressDto = RegisterAddressDto.builder()
                    .customerId(addressList.get(i).getCustomer().getCustomerId())
                    .addr(addressList.get(i).getAddr())
                    .addrRequest(addressList.get(i).getAddrRequests())
                    .build();
            listRegisterAddressDto.add(registerAddressDto);
            i++;
        }
        return listRegisterAddressDto;
    }
}
