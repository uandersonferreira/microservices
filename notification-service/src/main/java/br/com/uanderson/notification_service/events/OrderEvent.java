package br.com.uanderson.notification_service.events;


import br.com.uanderson.notification_service.model.enums.OrderStatus;

public record OrderEvent(String orderNumber, int itemCount, OrderStatus orderStatus) {
}
