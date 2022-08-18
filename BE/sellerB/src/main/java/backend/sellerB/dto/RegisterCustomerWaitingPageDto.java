package backend.sellerB.dto;

import backend.sellerB.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
public class RegisterCustomerWaitingPageDto {
    private Long productSeq;
    private String customerWaitingPageMent;
    private String customerWaitingPageImage;
}
