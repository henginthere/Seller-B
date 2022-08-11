package backend.sellerB.dto;

import backend.sellerB.entity.ProductGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class EditConsultantDto {

    private String consultantPass;
    private String consultantEmail;
    private String consultantTel;
    private ProductGroup productGroup;
//    private String consultantImageUrl;
    private MultipartFile consultantImageFile;
}
