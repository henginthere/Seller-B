package backend.sellerB.dto;

import backend.sellerB.entity.Consultant;
import backend.sellerB.entity.Consulting;
import backend.sellerB.entity.Customer;
import backend.sellerB.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ResponseConsultingDto {
    private Long consultingSeq;
    private Customer customer;
    private Consultant consultant;
    private Product product;
    private String consultingState;
    private LocalDateTime consultingStartDate;
    private LocalDateTime consultingEndDate;

    public static ResponseConsultingDto from(Consulting consulting) {
        if(consulting == null) return null;
        return ResponseConsultingDto.builder()
                .consultingSeq(consulting.getConsultingSeq())
                .customer(consulting.getCustomer())
                .consultant(consulting.getConsultant())
                .product(consulting.getProduct())
                .consultingState(consulting.getConsultingState())
                .consultingStartDate(consulting.getConsultingStartDate())
                .consultingEndDate(consulting.getConsultingEndDate())
                .build();
    }

    public static ArrayList<ResponseConsultingDto> fromList(List<Consulting> consultingList) {
        ArrayList<ResponseConsultingDto> listResponseConsultingDto = new ArrayList<>();
        int i = 0;
        while (i < consultingList.size()) {
            ResponseConsultingDto responseConsultingDto = ResponseConsultingDto.builder()
                    .consultingSeq(consultingList.get(i).getConsultingSeq())
                    .customer(consultingList.get(i).getCustomer())
                    .consultant(consultingList.get(i).getConsultant())
                    .product(consultingList.get(i).getProduct())
                    .consultingState(consultingList.get(i).getConsultingState())
                    .consultingStartDate(consultingList.get(i).getConsultingStartDate())
                    .consultingEndDate(consultingList.get(i).getConsultingEndDate())
                    .build();
            listResponseConsultingDto.add(responseConsultingDto);
            i++;
        }
        return listResponseConsultingDto;
    }
}
