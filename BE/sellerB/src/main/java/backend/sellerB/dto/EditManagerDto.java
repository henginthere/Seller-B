package backend.sellerB.dto;

import backend.sellerB.entity.ProductGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class EditManagerDto {

    private String managerPass;
    private String managerTel;
    private String managerEmail;
    private String managerImageUrl;
//    private MultipartFile managerImageFile;
}
