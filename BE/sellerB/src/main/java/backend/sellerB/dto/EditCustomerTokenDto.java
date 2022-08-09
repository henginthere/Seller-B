package backend.sellerB.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditCustomerTokenDto {
    private String customerToken;
    private String customerId;
}
