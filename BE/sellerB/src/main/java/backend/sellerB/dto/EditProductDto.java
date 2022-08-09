package backend.sellerB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditProductDto {
    private Long productSeq;
    private String productId;
    private String productName;
    private Integer productPrice;
    private String productManual;
    private String productThumbnail;
}
