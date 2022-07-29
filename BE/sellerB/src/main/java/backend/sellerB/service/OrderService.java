package backend.sellerB.service;

import backend.sellerB.dto.CustomerDto;
import backend.sellerB.dto.OrderDto;
import backend.sellerB.entity.Customer;
import backend.sellerB.entity.Order;
import backend.sellerB.repository.OrderRepository;
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

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .addr(orderDto.getAddress())
                .orderState(orderDto.getOrderState())
                .build();

        return OrderDto.from(orderRepository.save(order));
    }

    //고객 자신만의 주문 목록을 조회해야한다
    public List<OrderDto> getOrderList() {
        return OrderDto.fromList(orderRepository.findAll());
    }

    public OrderDto getOrderDetail(Integer seq) {
        Optional<Order> orderOptional = orderRepository.findById(seq);
        Order order = orderOptional.get();
        return OrderDto.from(order);
    }

    public OrderDto updateOrder(Integer seq, OrderDto orderDto) {
        Optional<Order> orderOptional = orderRepository.findById(seq);
        Order order = orderOptional.get();
        order.setAddr(orderDto.getAddress());
        order.setOrderState(orderDto.getOrderState());
        return OrderDto.from(order);
    }

    public OrderDto deleteOrder(Integer seq) {
        Optional<Order> orderOptional = orderRepository.findById(seq);
        Order order = orderOptional.get();
        orderRepository.deleteById(seq);
        return OrderDto.from(order);
    }
}
