package backend.sellerB.service;

import backend.sellerB.dto.NoticeDto;
import backend.sellerB.dto.OrderDetailDto;
import backend.sellerB.entity.Orderdetail;
import backend.sellerB.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailDto createOrderDetail(OrderDetailDto orderDetailDto) {
        Orderdetail orderDetail = Orderdetail.builder()
                .product(orderDetailDto.getProduct())
                .order(orderDetailDto.getOrder())
                .orderDetailCount(orderDetailDto.getOrderDetailCount())
                .build();

        return OrderDetailDto.from(orderDetailRepository.save(orderDetail));
    }

    public List<OrderDetailDto> getOrderDetailList() {
        return OrderDetailDto.fromList(orderDetailRepository.findAll());
    }

    public OrderDetailDto getOrderDetailDetail(Long seq) {
        Optional<Orderdetail> orderDetailOptional = orderDetailRepository.findById(seq);
        Orderdetail orderdetail = orderDetailOptional.get();
        return OrderDetailDto.from(orderdetail);
    }

    public OrderDetailDto updateOrderDetail(Long seq, OrderDetailDto orderDetailDto) {
        Optional<Orderdetail> orderDetailOptional = orderDetailRepository.findById(seq);
        Orderdetail orderdetail = orderDetailOptional.get();
        orderdetail.setProduct(orderDetailDto.getProduct());
        orderdetail.setOrder(orderDetailDto.getOrder());
        orderdetail.setOrderDetailCount(orderDetailDto.getOrderDetailCount());
        return OrderDetailDto.from(orderdetail);
    }

    public OrderDetailDto deleteOrderDetail(Long seq) {
        Optional<Orderdetail> orderDetailOptional = orderDetailRepository.findById(seq);
        Orderdetail orderdetail = orderDetailOptional.get();
        orderdetail.setOrderDetailDelYn(true);
        return OrderDetailDto.from(orderdetail);
    }
}
