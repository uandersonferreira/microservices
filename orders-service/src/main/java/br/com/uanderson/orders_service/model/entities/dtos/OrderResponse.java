package br.com.uanderson.orders_service.model.entities.dtos;

import java.util.List;

public record OrderResponse(
        Long id, String orderNumber, List<OrderItemResponse> orderItems
) { }
