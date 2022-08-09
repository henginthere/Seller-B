package backend.sellerB.dto;

import backend.sellerB.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ResponseAddressDetailDto {

    private Long customerSeq;
    private String address;
    private String addressReq;

    public static ResponseAddressDetailDto from(Address address) {
        if(address == null) return null;
        return ResponseAddressDetailDto.builder()
                .customerSeq(address.getCustomer().getCustomerSeq())
                .address(address.getAddr())
                .addressReq(address.getAddrRequests())
                .build();
    }

    public static ArrayList<ResponseAddressDetailDto> fromList(List<Address> addressList) {
        ArrayList<ResponseAddressDetailDto> listResponseAddressDetailDto = new ArrayList<>();
        int i = 0;
        while (i < addressList.size()) {
            ResponseAddressDetailDto responseAddressDetailDto = ResponseAddressDetailDto.builder()
                    .customerSeq(addressList.get(i).getCustomer().getCustomerSeq())
                    .address(addressList.get(i).getAddr())
                    .addressReq(addressList.get(i).getAddrRequests())
                    .build();
            listResponseAddressDetailDto.add(responseAddressDetailDto);
            i++;
        }
        return listResponseAddressDetailDto;
    }
}
