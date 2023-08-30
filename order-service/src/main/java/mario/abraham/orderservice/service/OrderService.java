package mario.abraham.orderservice.service;

import jakarta.transaction.Transactional;
import mario.abraham.orderservice.dto.OrderRequest;
import mario.abraham.orderservice.dto.OrderResponse;
import mario.abraham.orderservice.dto.PreparationRequest;
import mario.abraham.orderservice.dto.PreparationResponse;
import mario.abraham.orderservice.model.CurrentStatus;
import mario.abraham.orderservice.model.Order;
import mario.abraham.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient webClient;

    public OrderService(OrderRepository orderRepository, WebClient webClient) {
        this.orderRepository = orderRepository;
        this.webClient = webClient;
    }

    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = new Order();

        order.setCoffeeDrinkType(orderRequest.getCoffeeDrinkType());
        order.setCoffeeSize(orderRequest.getCoffeeSize());
        order.setMilkType(orderRequest.getMilkType());
        order.setOnSite(orderRequest.isOnSite());
        order.setPrice(orderRequest.getPrice());
        order.setOrderNumber(UUID.randomUUID().toString());

        Order createdOrder = orderRepository.save(order);

        PreparationRequest preparationRequest = new PreparationRequest();
        preparationRequest.setOrderNumber(createdOrder.getOrderNumber());

        PreparationResponse preparationResponse = webClient.post()
                .uri("http://localhost:8081/api/preparations")
                .body(BodyInserters.fromValue(preparationRequest))
                .retrieve()
                .bodyToMono(PreparationResponse.class)
                .block();

        OrderResponse orderResponse = mapToOrderResponse(createdOrder);

        if (preparationResponse != null) {
            orderResponse.setCurrentStatus(preparationResponse.getCurrentStatus());
        }

        return orderResponse;
    }

    private OrderResponse mapToOrderResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId(order.getId());
        orderResponse.setCoffeeDrinkType(order.getCoffeeDrinkType());
        orderResponse.setCoffeeSize(order.getCoffeeSize());
        orderResponse.setMilkType(order.getMilkType());
        orderResponse.setOnSite(order.isOnSite());
        orderResponse.setPrice(order.getPrice());
        orderResponse.setOrderNumber(order.getOrderNumber());

        return orderResponse;
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(this::mapToOrderResponse)
                .map(this::assignCurrentStatus)
                .toList();
    }

    private OrderResponse assignCurrentStatus(OrderResponse orderResponse) {
        PreparationResponse preparationResponse = webClient.get()
                .uri("http://localhost:8081/api/preparations/" + orderResponse.getOrderNumber())
                .retrieve()
                .bodyToMono(PreparationResponse.class)
                .block();

        if (preparationResponse != null) {
            orderResponse.setCurrentStatus(preparationResponse.getCurrentStatus());
        }

        return orderResponse;
    }

    @Transactional
    public void deleteOrderByOrderNumber(String orderNumber) {

        orderRepository.deleteByOrderNumber(orderNumber);
    }

    @Transactional
    public void cancelOrderByOrderNumber(String orderNumber) {

        PreparationResponse preparationResponse = webClient.get()
                .uri("http://localhost:8081/api/preparations/" + orderNumber)
                .retrieve()
                .bodyToMono(PreparationResponse.class)
                .block();

        if (preparationResponse != null) {
            if (preparationResponse.getCurrentStatus().equals(CurrentStatus.IN_PREPARATION)
                    || preparationResponse.getCurrentStatus().equals(CurrentStatus.FINISHED)) {
                throw new IllegalArgumentException("Order '%s' cannot be cancelled - already in preparation".formatted(orderNumber));
            } else {
                deletePreparation(orderNumber);
                orderRepository.deleteByOrderNumber(orderNumber);
            }
        }

    }

    private void deletePreparation(String orderNumber) {
        webClient.delete()
                .uri("http://localhost:8081/api/preparations/" + orderNumber)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
