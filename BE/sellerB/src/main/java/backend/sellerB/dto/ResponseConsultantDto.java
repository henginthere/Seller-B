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
    private ProductGroup productGroup;
    private String consultantImageUrl;
    private Boolean consultantDelYn;
    private String brandName;


    private Set<AuthorityDto> authorityDtoSet;

    public static String getBrandName(Consultant consultant){
        System.out.println("1");
        if (consultant != null) {
            ProductGroup productGroup = consultant.getProductGroup();
            System.out.println("2");
            if (productGroup != null) {
                Brand brand = productGroup.getBrand();
                System.out.println("3");
                if (brand != null) {
                    String brandName = brand.getBrandNameKor();
                    System.out.println("4");
                    if (brandName != null) {
                        System.out.println("5");
                        return brandName;
                    }
                }
            }
        }
        System.out.println("666666");
        return "브랜드 없음";
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
                .productGroup(consultant.getProductGroup())

                .consultantImageUrl(consultant.getConsultantImageUrl())
                .consultantDelYn(consultant.getConsultantDelYn())
                .authorityDtoSet(consultant.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }

    public static ArrayList<ResponseConsultantDto> fromList(List<Consultant> consultantList) {
        ArrayList<ResponseConsultantDto> listResponseConsultantDto= new ArrayList<>();
        int i = 0;
        while(i < consultantList.size()){

            String brandName = consultantList.get(i).getProductGroup().getBrand().getBrandNameKor();

            ResponseConsultantDto responseConsultantDto = ResponseConsultantDto.builder()
                    .consultantSeq(consultantList.get(i).getConsultantSeq())
                    .consultantId(consultantList.get(i).getConsultantId())
                    .consultantName(consultantList.get(i).getConsultantName())
                    .consultantEmail(consultantList.get(i).getConsultantEmail())
                    .consultantTel(consultantList.get(i).getConsultantTel())
                    .consultantImageUrl(consultantList.get(i).getConsultantImageUrl())
                    .brandName(getBrandName(consultantList.get(i)))
//                    .consultantDelYn(consultantList.get(i).getConsultantDelYn())
//                    .productGroup(consultantList.get(i).getProductGroup())
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
