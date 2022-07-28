package backend.sellerB.dto;

import backend.sellerB.entity.CustomerWaitingPage;
import backend.sellerB.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CustomerWaitingPageDto {
    private Product product;
    private String customerWaitingPageMent;
    private String customerWaitingPageImage;

    public static CustomerWaitingPageDto from(CustomerWaitingPage customerWaitingPage) {
        if(customerWaitingPage == null) return null;
        return CustomerWaitingPageDto.builder()
                .product(customerWaitingPage.getProduct())
                .customerWaitingPageMent(customerWaitingPage.getCustomerWaitingPageMent())
                .customerWaitingPageImage(customerWaitingPage.getCustomerWaitingPageImage())
                .build();
    }

    public static ArrayList<CustomerWaitingPageDto> fromList(List<CustomerWaitingPage> customerWaitingPageList) {
        ArrayList<CustomerWaitingPageDto> listCustomerWaitingPageDto = new ArrayList<>();
        int i = 0;
        while (i < listCustomerWaitingPageDto.size()) {
            CustomerWaitingPageDto customerWaitingPageDto = CustomerWaitingPageDto.builder()
                    .product(customerWaitingPageList.get(i).getProduct())
                    .customerWaitingPageMent(customerWaitingPageList.get(i).getCustomerWaitingPageMent())
                    .customerWaitingPageImage(customerWaitingPageList.get(i).getCustomerWaitingPageImage())
                    .build();
            listCustomerWaitingPageDto.add(customerWaitingPageDto);
            i++;
        }
        return listCustomerWaitingPageDto;
    }
}
