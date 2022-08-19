package backend.sellerB.dto;

import backend.sellerB.entity.Manager;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class RegisterManagerDto {

    private Long managerSeq;
    private Long brandSeq;
    private String managerId;
    private String managerName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String managerPass;
    private String managerTel;
    private String managerEmail;
    private String managerImageUrl;

    public static RegisterManagerDto from(Manager manager) {

        if(manager == null) return null;
        return RegisterManagerDto.builder()
                .managerSeq(manager.getManagerSeq())
                .brandSeq(manager.getBrand().getBrandSeq())
                .managerId(manager.getManagerId())
                .managerName(manager.getManagerName())
                .managerPass(manager.getManagerPass())
                .managerTel(manager.getManagerTel())
                .managerEmail(manager.getManagerEmail())
                .managerImageUrl(manager.getManagerImageUrl())
                .build();
    }

}
