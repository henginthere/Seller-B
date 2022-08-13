package backend.sellerB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class EditProductDto {
    private Long productSeq;
    private String productId;
    private String productName;
    private Integer productPrice;
    private String productManual;
    private String productThumbnailUrl;
//    private MultipartFile productThumbnailFile;
}
