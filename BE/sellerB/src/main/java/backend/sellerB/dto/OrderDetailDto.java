package backend.sellerB.dto;

import backend.sellerB.entity.Order;
import backend.sellerB.entity.Orderdetail;
import backend.sellerB.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class OrderDetailDto {
    private Product product;
    private Order order;
    private Integer orderDetailCount;

    public static OrderDetailDto from(Orderdetail orderdetail) {
        if(orderdetail == null) return null;
        return OrderDetailDto.builder()
                .product(orderdetail.getProduct())
                .order(orderdetail.getOrder())
                .orderDetailCount(orderdetail.getOrderDetailCount())
                .build();
    }

    public static ArrayList<OrderDetailDto> fromList(List<Orderdetail> orderdetailList) {
        ArrayList<OrderDetailDto> listOrderDetailDto = new ArrayList<>();
        int i = 0;
        while (i < orderdetailList.size()) {
            OrderDetailDto orderDetailDto = OrderDetailDto.builder()
                    .product(orderdetailList.get(i).getProduct())
                    .order(orderdetailList.get(i).getOrder())
                    .orderDetailCount(orderdetailList.get(i).getOrderDetailCount())
                    .build();
            listOrderDetailDto.add(orderDetailDto);
            i++;
        }
        return listOrderDetailDto;
    }
}
