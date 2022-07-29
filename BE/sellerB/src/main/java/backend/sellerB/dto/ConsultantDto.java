package backend.sellerB.dto;

import backend.sellerB.entity.Brand;
import backend.sellerB.entity.Consultant;
import backend.sellerB.entity.Manager;
import backend.sellerB.entity.ProductGroup;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class ConsultantDto {

    private String consultantId;
    private String consultantName;
    private String consultantEmail;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String consultantPass;
    private String consultantTel;
    private ProductGroup productGroup;


    private Set<AuthorityDto> authorityDtoSet;

    //엔티티를 dto로
    public static ConsultantDto from(Consultant consultant) {

        if(consultant == null) return null;
        return ConsultantDto.builder()
                .consultantId(consultant.getConsultantId())
                .consultantName(consultant.getConsultantName())
                .consultantEmail(consultant.getConsultantEmail())
                .consultantPass(consultant.getConsultantPass())
                .consultantTel(consultant.getConsultantTel())
                .productGroup(consultant.getProductGroup())
                .authorityDtoSet(consultant.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
