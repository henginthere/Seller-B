package backend.sellerB.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class RegisterOrderDetailDto {

    private Long productSeq;
    private Long orderSeq;
    private Integer orderDetailCount;

}
