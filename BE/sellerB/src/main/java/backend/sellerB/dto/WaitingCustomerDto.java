package backend.sellerB.dto;

import backend.sellerB.entity.Notice;
import backend.sellerB.entity.WaitingCustomer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class WaitingCustomerDto {

    private Long customerSeq;
    private Long productSeq;
    private Boolean waitingCustomerState;
    private Long productGroupSeq;

    public static WaitingCustomerDto from(WaitingCustomer waitingCustomer) {
        if(waitingCustomer == null) return null;
        return WaitingCustomerDto.builder()
                .customerSeq(waitingCustomer.getCustomer().getCustomerSeq())
                .productSeq(waitingCustomer.getProduct().getProductSeq())
                .waitingCustomerState(waitingCustomer.getWaitingCustomerState())
                .productGroupSeq(waitingCustomer.getProduct().getProductGroup().getProductGroupSeq())
                .build();
    }

    public static ArrayList<WaitingCustomerDto> fromList(List<WaitingCustomer> waitingCustomerList) {
        ArrayList<WaitingCustomerDto> listWaitingCustomerDto= new ArrayList<>();
        int i = 0;
        while(i < waitingCustomerList.size()){
            WaitingCustomerDto waitingCustomerDto = WaitingCustomerDto.builder()
                    .customerSeq(waitingCustomerList.get(i).getCustomer().getCustomerSeq())
                    .productSeq(waitingCustomerList.get(i).getProduct().getProductSeq())
                    .waitingCustomerState(waitingCustomerList.get(i).getWaitingCustomerState())
                    .productGroupSeq(waitingCustomerList.get(i).getProduct().getProductGroup().getProductGroupSeq())
                    .build();
            listWaitingCustomerDto.add(waitingCustomerDto);
            i++;
        }
        return listWaitingCustomerDto;
    }
}
