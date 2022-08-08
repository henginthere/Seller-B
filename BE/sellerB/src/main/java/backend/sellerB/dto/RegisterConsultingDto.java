package backend.sellerB.dto;

import backend.sellerB.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RegisterConsultingDto {
    private Long consultingSeq;
    private String customerId;  //seq로 수정
    private Long consultantSeq;
    private Long productSeq;
    private Boolean consultingState;
    //openvidu 정보를 입력

    public static RegisterConsultingDto from(Consulting consulting) {
        if(consulting == null) return null;
        return RegisterConsultingDto.builder()
                .consultingSeq(consulting.getConsultingSeq())
                .customerId(consulting.getCustomer().getCustomerId())
                .consultantSeq(consulting.getConsultant().getConsultantSeq())
                .productSeq(consulting.getProduct().getProductSeq())
                .consultingState(consulting.getConsultingState())
                .build();
    }

    public static ArrayList<RegisterConsultingDto> fromList(List<Consulting> consultingList) {
        ArrayList<RegisterConsultingDto> listRegisterConsultingDto = new ArrayList<>();
        int i = 0;
        while (i < consultingList.size()) {
            RegisterConsultingDto registerConsultingDto = RegisterConsultingDto.builder()
                    .consultingSeq(consultingList.get(i).getConsultingSeq())
                    .customerId(consultingList.get(i).getCustomer().getCustomerId())
                    .consultantSeq(consultingList.get(i).getConsultant().getConsultantSeq())
                    .productSeq(consultingList.get(i).getProduct().getProductSeq())
                    .consultingState(consultingList.get(i).getConsultingState())
                    .build();
            listRegisterConsultingDto.add(registerConsultingDto);
            i++;
        }
        return listRegisterConsultingDto;
    }
}
