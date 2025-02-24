package br.com.uanderson.notification_service.listeners;

import br.com.uanderson.notification_service.events.OrderEvent;
import br.com.uanderson.notification_service.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventListener {

    @KafkaListener(topics = "orders-topic")
    public void handleOrdersNotifications(String message){
        OrderEvent orderEvent = JsonUtils.fromJson(message, OrderEvent.class);

        //Send email to customer, send SMS to customer, etc.
        //Notify another service...

        log.info("Order {} event received for order : {} with {} items",
                orderEvent.orderStatus(),
                orderEvent.orderNumber(),
                orderEvent.itemCount()
        );
    }


}
