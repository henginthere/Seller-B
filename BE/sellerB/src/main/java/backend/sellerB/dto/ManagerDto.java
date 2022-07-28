package backend.sellerB.dto;

import backend.sellerB.entity.Brand;
import backend.sellerB.entity.Manager;
import backend.sellerB.entity.Notice;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class ManagerDto {

    private Brand brandSeq;
    private String managerId;
    private String managerName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String managerPass;
    private String managerTel;
    private String managerEmail;

    private Set<AuthorityDto> authorityDtoSet;

    //엔티티를 dto로
    public static ManagerDto from(Manager manager) {
        if(manager == null) return null;
        return ManagerDto.builder()
                .brandSeq(manager.getBrandSeq())
                .managerId(manager.getManagerId())
                .managerName(manager.getManagerName())
                .managerPass(manager.getManagerPass())
                .managerTel(manager.getManagerTel())
                .managerEmail(manager.getManagerEmail())
                .authorityDtoSet(manager.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }

}
