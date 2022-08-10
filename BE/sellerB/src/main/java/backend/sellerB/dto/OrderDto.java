package backend.sellerB.dto;

import backend.sellerB.entity.Address;
import backend.sellerB.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class OrderDto {

    private Address address;
    private Byte orderState;
    private Long orderSeq;

    public static OrderDto from(Order order) {
        if(order == null) return null;
        return OrderDto.builder()
                .orderSeq(order.getOrderSeq())
                .address(order.getAddr())
                .orderState(order.getOrderState())
                .build();
    }

    public static ArrayList<OrderDto> fromList(List<Order> orderList) {
        ArrayList<OrderDto> listOrderDto = new ArrayList<>();
        int i = 0;
        while (i < orderList.size()) {
            OrderDto orderDto = OrderDto.builder()
                    .orderSeq(orderList.get(i).getOrderSeq())
                    .address(orderList.get(i).getAddr())
                    .orderState(orderList.get(i).getOrderState())
                    .build();
            listOrderDto.add(orderDto);
            i++;
        }
        return listOrderDto;
    }
}
