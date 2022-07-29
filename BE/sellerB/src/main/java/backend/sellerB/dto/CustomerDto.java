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
    private String customerId;
    private String customerName;
    private String customerPass;
    private String customerEmail;
    private String customerGender;
    private String customerTel;
    private String customerAddr;
    private Timestamp customerBirth;
    private String customerToken;

    public static CustomerDto from(Customer customer) {
        if(customer == null) return null;
        return CustomerDto.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .customerPass(customer.getCustomerPass())
                .customerEmail(customer.getCustomerEmail())
                .customerGender(customer.getCustomerGender())
                .customerTel(customer.getCustomerTel())
                .customerAddr(customer.getCustomerAddr())
                .customerBirth(customer.getCustomerBirth())
                .customerToken(customer.getCustomerToken())
                .build();
    }


    public static ArrayList<CustomerDto> fromList(List<Customer> customerList) {
        ArrayList<CustomerDto> listCustomerDto = new ArrayList<>();
        int i = 0;
        while (i < customerList.size()) {
            CustomerDto customerDto = CustomerDto.builder()
                    .customerId(customerList.get(i).getCustomerId())
                    .customerName(customerList.get(i).getCustomerName())
                    .customerPass(customerList.get(i).getCustomerPass())
                    .customerEmail(customerList.get(i).getCustomerEmail())
                    .customerGender(customerList.get(i).getCustomerGender())
                    .customerTel(customerList.get(i).getCustomerTel())
                    .customerAddr(customerList.get(i).getCustomerAddr())
                    .customerBirth(customerList.get(i).getCustomerBirth())
                    .customerToken(customerList.get(i).getCustomerToken())
                    .build();
            listCustomerDto.add(customerDto);
            i++;
        }
        return listCustomerDto;
    }
}
