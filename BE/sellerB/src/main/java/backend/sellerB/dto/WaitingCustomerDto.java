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

    private Long waitingCustomerSeq;
    private Long customerSeq;
    private String customerName;
    private String customerId;
    private Long productSeq;
    private String productName;
    private String productId;
    private Boolean waitingCustomerState;
    private Long productGroupSeq;


    public static WaitingCustomerDto from(WaitingCustomer waitingCustomer) {
        if(waitingCustomer == null) return null;
        return WaitingCustomerDto.builder()
                .waitingCustomerSeq(waitingCustomer.getWaitingCustomerSeq())
                .customerSeq(waitingCustomer.getCustomer().getCustomerSeq())
                .customerName(waitingCustomer.getCustomer().getCustomerName())
                .customerId(waitingCustomer.getCustomer().getCustomerId())
                .productSeq(waitingCustomer.getProduct().getProductSeq())
                .productName(waitingCustomer.getProduct().getProductName())
                .productId(waitingCustomer.getProduct().getProductId())
                .waitingCustomerState(waitingCustomer.getWaitingCustomerState())
                .productGroupSeq(waitingCustomer.getProduct().getProductGroup().getProductGroupSeq())
                .build();
    }

    public static ArrayList<WaitingCustomerDto> fromList(List<WaitingCustomer> waitingCustomerList) {
        ArrayList<WaitingCustomerDto> listWaitingCustomerDto= new ArrayList<>();
        int i = 0;
        while(i < waitingCustomerList.size()){
            WaitingCustomerDto waitingCustomerDto = WaitingCustomerDto.builder()
                    .waitingCustomerSeq(waitingCustomerList.get(i).getWaitingCustomerSeq())
                    .customerSeq(waitingCustomerList.get(i).getCustomer().getCustomerSeq())
                    .customerName(waitingCustomerList.get(i).getCustomer().getCustomerName())
                    .customerId(waitingCustomerList.get(i).getCustomer().getCustomerId())
                    .productSeq(waitingCustomerList.get(i).getProduct().getProductSeq())
                    .productName(waitingCustomerList.get(i).getProduct().getProductName())
                    .productId(waitingCustomerList.get(i).getProduct().getProductId())
                    .waitingCustomerState(waitingCustomerList.get(i).getWaitingCustomerState())
                    .productGroupSeq(waitingCustomerList.get(i).getProduct().getProductGroup().getProductGroupSeq())
                    .build();
            listWaitingCustomerDto.add(waitingCustomerDto);
            i++;
        }
        return listWaitingCustomerDto;
    }
}
