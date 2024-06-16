package br.com.uanderson.orders_service.controllers;

import br.com.uanderson.orders_service.model.entities.dtos.OrderRequest;
import br.com.uanderson.orders_service.services.OrderServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServices orderServices;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderServices.placeOrder(orderRequest);
        return "Order placed successfully";
    }
}
