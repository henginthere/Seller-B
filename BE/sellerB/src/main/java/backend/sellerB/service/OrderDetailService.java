package backend.sellerB.service;

import backend.sellerB.dto.NoticeDto;
import backend.sellerB.dto.OrderDetailDto;
import backend.sellerB.dto.RegisterOrderDetailDto;
import backend.sellerB.entity.Order;
import backend.sellerB.entity.Orderdetail;
import backend.sellerB.entity.Product;
import backend.sellerB.repository.OrderDetailRepository;
import backend.sellerB.repository.OrderRepository;
import backend.sellerB.repository.ProductRepository;
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
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderDetailDto createOrderDetail(RegisterOrderDetailDto registerOrderDetailDto) {

        Product product = productRepository.findById(registerOrderDetailDto.getProductSeq()).get();
        Order order = orderRepository.findById(registerOrderDetailDto.getOrderSeq()).get();

        Orderdetail orderDetail = Orderdetail.builder()
                .product(product)
                .order(order)
                .orderDetailCount(registerOrderDetailDto.getOrderDetailCount())
                .build();

        return OrderDetailDto.from(orderDetailRepository.save(orderDetail));
    }

    public List<OrderDetailDto> getOrderDetailList() {
        return OrderDetailDto.fromList(orderDetailRepository.findAll());
    }

    public List<OrderDetailDto> getOrderDetailbyOrderSeq(Long orderSeq) {
        Optional<List<Orderdetail>> orderDetailOptionalList = orderDetailRepository.findByOrder_OrderSeq(orderSeq);
        List<Orderdetail> orderdetailList = orderDetailOptionalList.get();
        return OrderDetailDto.fromList(orderdetailList);
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
        orderDetailRepository.deleteById(seq);
        return OrderDetailDto.from(orderdetail);
    }
}
