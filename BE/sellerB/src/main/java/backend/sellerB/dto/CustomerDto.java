package backend.sellerB.dto;

import backend.sellerB.entity.Customer;
import backend.sellerB.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CustomerDto {
    //token이랑 password 따로 처리 필요
    private String CustomerId;
    private String CustomerName;
    private String CustomerPass;
    private String CustomerEmail;
    private String CustomerGender;
    private String CustomerTel;
    private String CustomerAddr;
    private Timestamp CustomerBirth;
    private String CustomerToken;

    public static CustomerDto from(Customer customer) {
        if(customer == null) return null;
        return CustomerDto.builder()
                .CustomerId(customer.getCustomerId())
                .CustomerName(customer.getCustomerName())
                .CustomerPass(customer.getCustomerPass())
                .CustomerEmail(customer.getCustomerEmail())
                .CustomerGender(customer.getCustomerGender())
                .CustomerTel(customer.getCustomerTel())
                .CustomerAddr(customer.getCustomerAddr())
                .CustomerBirth(customer.getCustomerBirth())
                .CustomerToken(customer.getCustomerToken())
                .build();
    }


    public static ArrayList<CustomerDto> fromList(List<Customer> customerList) {
        ArrayList<CustomerDto> listCustomerDto = new ArrayList<>();
        int i = 0;
        while (i < customerList.size()) {
            CustomerDto customerDto = CustomerDto.builder()
                    .CustomerId(customerList.get(i).getCustomerId())
                    .CustomerName(customerList.get(i).getCustomerName())
                    .CustomerPass(customerList.get(i).getCustomerPass())
                    .CustomerEmail(customerList.get(i).getCustomerEmail())
                    .CustomerGender(customerList.get(i).getCustomerGender())
                    .CustomerTel(customerList.get(i).getCustomerTel())
                    .CustomerAddr(customerList.get(i).getCustomerAddr())
                    .CustomerBirth(customerList.get(i).getCustomerBirth())
                    .CustomerToken(customerList.get(i).getCustomerToken())
                    .build();
            listCustomerDto.add(customerDto);
            i++;
        }
        return listCustomerDto;
    }
}
