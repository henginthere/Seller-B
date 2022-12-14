package backend.sellerB.dto;

import backend.sellerB.entity.Brand;
import backend.sellerB.entity.Consultant;
import backend.sellerB.entity.ProductGroup;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class ResponseConsultantDto {
    private Long consultantSeq;
    private String consultantId;
    private String consultantName;
    private String consultantEmail;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String consultantPass;
    private String consultantTel;
    private String productGroupName;
    private Long productGroupSeq;
    private String consultantImageUrl;
    private Boolean consultantDelYn;
    private String brandName;


    private Set<AuthorityDto> authorityDtoSet;

    public static String getBrandName(Consultant consultant){
        if (consultant != null) {
            ProductGroup productGroup = consultant.getProductGroup();
            if (productGroup != null) {
                Brand brand = productGroup.getBrand();
                if (brand != null) {
                    String brandName = brand.getBrandNameEng();
                    if (brandName != null) {
                        return brandName;
                    }
                }
            }
        }
        return "브랜드 없음";
    }

    public static String getProductGroupName(Consultant consultant){
        if (consultant != null) {
            ProductGroup productGroup = consultant.getProductGroup();
            if (productGroup != null) {
                String productGroupName = productGroup.getProductGroupName();
                if (productGroupName != null) {
                    return productGroupName;
                }
            }
        }
        return "제품군 없음";
    }


    public static Long getProductGroupSeq(Consultant consultant) {
        if (consultant != null) {
            ProductGroup productGroup = consultant.getProductGroup();
            if (productGroup != null) {
                Long productGroupSeq = productGroup.getProductGroupSeq();
                if (productGroupSeq != null) {
                    return productGroupSeq;
                }
            }
        }
        return -1L;

    }

    //엔티티를 dto로
    public static ResponseConsultantDto from(Consultant consultant) {

        if(consultant == null) return null;
        return ResponseConsultantDto.builder()
                .consultantSeq(consultant.getConsultantSeq())
                .consultantId(consultant.getConsultantId())
                .consultantName(consultant.getConsultantName())
                .consultantEmail(consultant.getConsultantEmail())
                .consultantPass(consultant.getConsultantPass())
                .consultantTel(consultant.getConsultantTel())
                .brandName(getBrandName(consultant))
                .productGroupName(getProductGroupName(consultant))
                .productGroupSeq(getProductGroupSeq(consultant))
                .consultantImageUrl(consultant.getConsultantImageUrl())
                .consultantDelYn(consultant.getConsultantDelYn())
                .authorityDtoSet(consultant.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }

    public static ArrayList<ResponseConsultantDto> fromList(List<Consultant> consultantList) throws NullPointerException{
        ArrayList<ResponseConsultantDto> listResponseConsultantDto= new ArrayList<>();
        int i = 0;
        while(i < consultantList.size()){
            ResponseConsultantDto responseConsultantDto = ResponseConsultantDto.builder()
                    .consultantSeq(consultantList.get(i).getConsultantSeq())
                    .consultantId(consultantList.get(i).getConsultantId())
                    .consultantName(consultantList.get(i).getConsultantName())
                    .consultantEmail(consultantList.get(i).getConsultantEmail())
                    .consultantTel(consultantList.get(i).getConsultantTel())
                    .consultantImageUrl(consultantList.get(i).getConsultantImageUrl())
                    .brandName(getBrandName(consultantList.get(i)))
                    .productGroupSeq(getProductGroupSeq(consultantList.get(i)))
//                    .consultantDelYn(consultantList.get(i).getConsultantDelYn())
                    .productGroupName(getProductGroupName(consultantList.get(i)))
//                    .authorityDtoSet(consultantList.get(i).getAuthorities().stream()
//                            .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
//                            .collect(Collectors.toSet()))
                    .build();
            listResponseConsultantDto.add(responseConsultantDto);
            i++;
        }
        return listResponseConsultantDto;
    }
}
