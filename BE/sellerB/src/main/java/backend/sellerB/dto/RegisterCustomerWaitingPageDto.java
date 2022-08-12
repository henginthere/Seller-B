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
    private Product product;
    private String customerWaitingPageMent;
//    private MultipartFile customerWaitingPageImageFile;
    private String customerWaitingPageImageUrl;
}
