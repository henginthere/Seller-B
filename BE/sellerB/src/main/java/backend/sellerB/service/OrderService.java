package backend.sellerB.service;

import backend.sellerB.dto.CreateOrderDto;
import backend.sellerB.dto.CustomerDto;
import backend.sellerB.dto.OrderDetailDto;
import backend.sellerB.dto.OrderDto;
import backend.sellerB.entity.*;
import backend.sellerB.repository.AddressRepository;
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
public class OrderService {
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderDto createOrder(CreateOrderDto createOrderDto) {
        Optional<Address> addressOptional = addressRepository.findById(createOrderDto.getAddrSeq());
        Address address = addressOptional.get();
        Order order = Order.builder()
                .addr(address)
                .orderState(createOrderDto.getOrderState())
                .build();
        orderRepository.save(order);

        Order recentOrder = orderRepository.findTop1ByOrderByOrderSeqDesc();
        int i = 0;
        while (i < createOrderDto.getRegisterOrderDetailDtoList().size()) {
            Product product = productRepository.findById(createOrderDto.getRegisterOrderDetailDtoList().get(i).getProductSeq()).get();
            Orderdetail orderDetail = Orderdetail.builder()
                    .product(product)
                    .order(recentOrder)
                    .orderDetailCount(createOrderDto.getRegisterOrderDetailDtoList().get(i).getOrderDetailCount())
                    .build();
            orderDetailRepository.save(orderDetail);
            i++;
        }

        return OrderDto.from(orderRepository.save(order));
    }

    //고객 자신만의 주문 목록을 조회해야한다
    public List<OrderDto> getOrderList(Long seq) {
        Optional<List<Order>> orderOptionalList = orderRepository.findByOrderRegUserSeq(seq);
        List<Order> orderList = orderOptionalList.get();
        return OrderDto.fromList(orderList);
    }

    public OrderDto getOrderDetail(Long seq) {
        Optional<Order> orderOptional = orderRepository.findById(seq);
        Order order = orderOptional.get();
        return OrderDto.from(order);
    }

    public OrderDto updateOrder(Long seq, OrderDto orderDto) {
        Optional<Order> orderOptional = orderRepository.findById(seq);
        Order order = orderOptional.get();
        order.setAddr(orderDto.getAddress());
        order.setOrderState(orderDto.getOrderState());
        return OrderDto.from(order);
    }

    public OrderDto deleteOrder(Long seq) {
        Optional<Order> orderOptional = orderRepository.findById(seq);
        Order order = orderOptional.get();
//        order.setOrderDelYn(true);
        orderRepository.deleteById(seq);
        return OrderDto.from(order);
    }
}
