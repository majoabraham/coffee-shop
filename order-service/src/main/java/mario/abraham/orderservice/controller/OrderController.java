package mario.abraham.orderservice.controller;

import mario.abraham.orderservice.dto.OrderRequest;
import mario.abraham.orderservice.dto.OrderResponse;
import mario.abraham.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    @DeleteMapping("/{orderNumber}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteOrder(@PathVariable String orderNumber) {

        orderService.deleteOrderByOrderNumber(orderNumber);

        return "Order '%s' was deleted successfully".formatted(orderNumber);
    }

    @DeleteMapping("/{orderNumber}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public String cancelOrder(@PathVariable String orderNumber) {

        orderService.cancelOrderByOrderNumber(orderNumber);

        return "Order '%s' was cancelled successfully".formatted(orderNumber);
    }
}
