package br.com.uanderson.orders_service.events;

import br.com.uanderson.orders_service.model.enums.OrderStatus;

public record OrderEvent(String orderNumber, int itemCount, OrderStatus orderStatus) {
}
