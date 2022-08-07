package backend.sellerB.dto;

import backend.sellerB.entity.Customer;
import backend.sellerB.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class CustomerDto {
    //token이랑 password 따로 처리 필요
    private Long customerSeq;
    private String customerId;
    private String customerName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 3, max = 100)
    private String customerPass;
    private String customerEmail;
    private String customerBirth;
    private String customerToken;
    private Set<AuthorityDto> authorityDtoSet;

    public static CustomerDto from(Customer customer) {
        if(customer == null) return null;
        return CustomerDto.builder()
                .customerSeq(customer.getCustomerSeq())
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .customerPass(customer.getCustomerPass())
                .customerEmail(customer.getCustomerEmail())
                .customerBirth(customer.getCustomerBirth())
                .customerToken(customer.getCustomerToken())
                .authorityDtoSet(customer.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }


    public static ArrayList<CustomerDto> fromList(List<Customer> customerList) {
        ArrayList<CustomerDto> listCustomerDto = new ArrayList<>();
        int i = 0;
        while (i < customerList.size()) {
            CustomerDto customerDto = CustomerDto.builder()
                    .customerSeq(customerList.get(i).getCustomerSeq())
                    .customerId(customerList.get(i).getCustomerId())
                    .customerName(customerList.get(i).getCustomerName())
                    .customerEmail(customerList.get(i).getCustomerEmail())
                    .customerBirth(customerList.get(i).getCustomerBirth())
                    .customerToken(customerList.get(i).getCustomerToken())
                    .authorityDtoSet(customerList.get(i).getAuthorities().stream()
                            .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                            .collect(Collectors.toSet()))
                    .build();
            listCustomerDto.add(customerDto);
            i++;
        }
        return listCustomerDto;
    }
}
