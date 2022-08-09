package backend.sellerB.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateWaitingCustomerDto {
    private Long customerSeq;
    private Long productSeq;
    private Boolean waitingCustomerState;
}
