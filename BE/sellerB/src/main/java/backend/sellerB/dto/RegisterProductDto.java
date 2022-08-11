package backend.sellerB.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
public class RegisterProductDto {
    private Long productSeq;
    private String productGroupName;
    private Long productGroupSeq;
    private String productId;
    private String productName;
    private Integer productPrice;
    private String productManual;
    private String productThumbnailUrl;
}
