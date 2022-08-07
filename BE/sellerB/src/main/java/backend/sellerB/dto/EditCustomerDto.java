package backend.sellerB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditCustomerDto {

    private String customerPass;
    private String customerEmail;


}
