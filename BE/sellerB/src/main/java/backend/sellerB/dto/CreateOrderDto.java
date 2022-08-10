package backend.sellerB.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CreateOrderDto {

    private Long addrSeq;
    private Byte orderState;
    private List<RegisterOrderDetailDto> registerOrderDetailDtoList;
}
