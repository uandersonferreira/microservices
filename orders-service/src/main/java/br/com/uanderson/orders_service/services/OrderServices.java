package br.com.uanderson.orders_service.services;

import br.com.uanderson.orders_service.model.entities.Order;
import br.com.uanderson.orders_service.model.entities.OrderItems;
import br.com.uanderson.orders_service.model.entities.dtos.*;
import br.com.uanderson.orders_service.respositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServices {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        //Check for inventory
        BaseResponse result = webClientBuilder.build()
                .post()
                .uri("lb://inventory-service/api/inventory/in-stock")
                .bodyValue(orderRequest.getOrderItems())
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();

        if (result != null && !result.hasErrors()){
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setOrderItems(orderRequest.getOrderItems().stream()
                    .map(orderItemRequest -> mapOrderItemRequestToOrderItem(orderItemRequest, order))
                    .collect(Collectors.toList())
            );

            var savedOrder = orderRepository.save(order);

            return mapToOrderResponse(savedOrder);

        }else {
            throw new IllegalArgumentException("Some of the products are not in stock");
        }

    }

    public List<OrderResponse> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::mapToOrderResponse).collect(Collectors.toList());
    }

    private OrderResponse mapToOrderResponse(Order order) {
        return new OrderResponse(order.getId(), order.getOrderNumber(),
                order.getOrderItems().stream().map(this::mapToOrderItemRequest).collect(Collectors.toList())
        );
    }

    private OrderItemResponse mapToOrderItemRequest(OrderItems orderItems) {
        return new OrderItemResponse(orderItems.getId(), orderItems.getSku(),
                orderItems.getPrice(), orderItems.getQuantity());
    }

    private OrderItems mapOrderItemRequestToOrderItem(OrderItemRequest orderItemRequest, Order order) {
        return OrderItems.builder()
                .id(orderItemRequest.getId())
                .sku(orderItemRequest.getSku())
                .price(orderItemRequest.getPrice())
                .quantity(orderItemRequest.getQuantity())
                .order(order)
                .build();
    }


}//class
