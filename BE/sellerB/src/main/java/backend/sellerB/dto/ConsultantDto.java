package backend.sellerB.dto;

import backend.sellerB.entity.*;
import backend.sellerB.service.ConsultantService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class ConsultantDto {


    private Long consultantSeq;
    private String consultantId;
    private String consultantName;
    private String consultantEmail;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String consultantPass;
    private String consultantTel;
    private ProductGroup productGroup;
    private String consultantImageUrl;
    private Boolean consultantDelYn;


    private Set<AuthorityDto> authorityDtoSet;

    //엔티티를 dto로
    public static ConsultantDto from(Consultant consultant) {

        if(consultant == null) return null;
        return ConsultantDto.builder()
                .consultantSeq(consultant.getConsultantSeq())
                .consultantId(consultant.getConsultantId())
                .consultantName(consultant.getConsultantName())
                .consultantEmail(consultant.getConsultantEmail())
                .consultantPass(consultant.getConsultantPass())
                .consultantTel(consultant.getConsultantTel())
                .productGroup(consultant.getProductGroup())
                .consultantImageUrl(consultant.getConsultantImageUrl())
                .consultantDelYn(consultant.getConsultantDelYn())
                .authorityDtoSet(consultant.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }

    public static ArrayList<ConsultantDto> fromList(List<Consultant> consultantList) {
        ArrayList<ConsultantDto> listConsultantDto= new ArrayList<>();
        int i = 0;
        while(i < consultantList.size()){
            ConsultantDto consultantDto = ConsultantDto.builder()
                    .consultantSeq(consultantList.get(i).getConsultantSeq())
                    .consultantId(consultantList.get(i).getConsultantId())
                    .consultantName(consultantList.get(i).getConsultantName())
                    .consultantEmail(consultantList.get(i).getConsultantEmail())
                    .consultantTel(consultantList.get(i).getConsultantTel())
                    .consultantImageUrl(consultantList.get(i).getConsultantImageUrl())
                    .consultantDelYn(consultantList.get(i).getConsultantDelYn())
                    .productGroup(consultantList.get(i).getProductGroup())
                    .build();
            listConsultantDto.add(consultantDto);
            i++;
        }
        return listConsultantDto;
    }
}
