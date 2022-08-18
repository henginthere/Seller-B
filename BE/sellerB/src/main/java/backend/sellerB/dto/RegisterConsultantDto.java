package backend.sellerB.dto;

import backend.sellerB.entity.Consultant;
import backend.sellerB.entity.ProductGroup;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class RegisterConsultantDto {

    private Long consultantSeq;
    private String consultantId;
    private String consultantName;
    private String consultantEmail;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String consultantPass;
    private String consultantTel;
    private Long productGroupSeq;
    private String consultantImageUrl;


    private Set<AuthorityDto> authorityDtoSet;


    public static RegisterConsultantDto from(Consultant consultant) {

        if(consultant == null) return null;
        return RegisterConsultantDto.builder()
                .consultantSeq(consultant.getConsultantSeq())
                .consultantId(consultant.getConsultantId())
                .consultantName(consultant.getConsultantName())
                .consultantEmail(consultant.getConsultantEmail())
                .consultantTel(consultant.getConsultantTel())
                .productGroupSeq(consultant.getProductGroup().getProductGroupSeq())
                .consultantImageUrl(consultant.getConsultantImageUrl())
                .build();
    }

    public static ArrayList<RegisterConsultantDto> fromList(List<Consultant> consultantList) {
        ArrayList<RegisterConsultantDto> listConsultantDto= new ArrayList<>();
        int i = 0;
        while(i < consultantList.size()){
            RegisterConsultantDto registerConsultantDto = RegisterConsultantDto.builder()
                    .consultantSeq(consultantList.get(i).getConsultantSeq())
                    .consultantId(consultantList.get(i).getConsultantId())
                    .consultantName(consultantList.get(i).getConsultantName())
                    .consultantEmail(consultantList.get(i).getConsultantEmail())
                    .consultantTel(consultantList.get(i).getConsultantTel())
                    .consultantImageUrl(consultantList.get(i).getConsultantImageUrl())
                    .productGroupSeq(consultantList.get(i).getProductGroup().getProductGroupSeq())
                    .build();
            listConsultantDto.add(registerConsultantDto);
            i++;
        }
        return listConsultantDto;
    }
}
