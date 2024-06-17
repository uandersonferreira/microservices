package br.com.uanderson.orders_service.controllers;

import br.com.uanderson.orders_service.model.entities.dtos.OrderRequest;
import br.com.uanderson.orders_service.model.entities.dtos.OrderResponse;
import br.com.uanderson.orders_service.services.OrderServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServices orderServices;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderServices.placeOrder(orderRequest);
        return "Order placed successfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrders(){
        return orderServices.getAllOrders();
    }

}//class
