package backend.sellerB.dto;

import backend.sellerB.entity.Brand;
import backend.sellerB.entity.Manager;
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
public class ManagerDto {

    private Long managerSeq;
    private Brand brand;
    private String managerId;
    private String managerName;
    private static final Logger logger = LoggerFactory.getLogger(ManagerDto.class);
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String managerPass;
    private String managerTel;
    private String managerEmail;
    private Boolean managerDelYn;

    private Set<AuthorityDto> authorityDtoSet;



    //엔티티를 dto로
    public static ManagerDto from(Manager manager) {
        logger.info(manager.getManagerId());
        if(manager == null) return null;

        return ManagerDto.builder()
                .managerSeq(manager.getManagerSeq())
                .brand(manager.getBrand())
                .managerId(manager.getManagerId())
                .managerName(manager.getManagerName())
                .managerPass(manager.getManagerPass())
                .managerTel(manager.getManagerTel())
                .managerEmail(manager.getManagerEmail())
                .managerDelYn(manager.getManagerDelYn())
                .authorityDtoSet(manager.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }

}
