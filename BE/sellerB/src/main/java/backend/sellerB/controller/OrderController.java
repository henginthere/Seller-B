package backend.sellerB.controller;

import backend.sellerB.dto.OrderDto;
import backend.sellerB.dto.CreateOrderDto;
import backend.sellerB.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> saveOrder(@Valid @RequestBody CreateOrderDto createOrderDto) {
        return ResponseEntity.ok(orderService.createOrder(createOrderDto));
    }

    @GetMapping("/{seq}")
    public ResponseEntity<OrderDto> getOrderDetail(@PathVariable Long seq) {
        return ResponseEntity.ok(orderService.getOrderDetail(seq));
    }

    @GetMapping("/list/{seq}")
    public ResponseEntity<List<OrderDto>> getOrderList(@PathVariable Long seq) {
        return ResponseEntity.ok(orderService.getOrderList(seq));
    }

    @PutMapping("/{seq}")
    public ResponseEntity<OrderDto> updateOrder(@Valid @RequestBody OrderDto orderDto, @PathVariable Long seq) {
        return ResponseEntity.ok(orderService.updateOrder(seq, orderDto));
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<OrderDto> deleteOrder(@PathVariable Long seq) {
        return ResponseEntity.ok(orderService.deleteOrder(seq));
    }
}
