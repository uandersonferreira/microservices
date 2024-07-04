package br.com.uanderson.orders_service.controllers;

import br.com.uanderson.orders_service.model.entities.dtos.OrderRequest;
import br.com.uanderson.orders_service.model.entities.dtos.OrderResponse;
import br.com.uanderson.orders_service.services.OrderServices;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServices orderServices;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "orders-service", fallbackMethod = "placeOrderFallback")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest){
        var orderResponse = orderServices.placeOrder(orderRequest);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrders(){
        return orderServices.getAllOrders();
    }

    private ResponseEntity<OrderResponse> placeOrderFallback(OrderResponse orderResponse, Throwable throwable){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }



}//class
