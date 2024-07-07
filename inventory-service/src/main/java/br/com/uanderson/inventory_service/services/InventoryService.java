package br.com.uanderson.inventory_service.services;

import br.com.uanderson.inventory_service.model.entities.Inventory;
import br.com.uanderson.inventory_service.model.entities.dtos.BaseResponse;
import br.com.uanderson.inventory_service.model.entities.dtos.OrderItemRequest;
import br.com.uanderson.inventory_service.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String sku) {
       var inventory = inventoryRepository.findBySku(sku);
       return inventory.filter(value -> value.getQuantity() > 0).isPresent();
    }

    public BaseResponse areInStock(List<OrderItemRequest> orderItems) {
        var erroList = new ArrayList<>();

        List<String> skus = orderItems.stream()
                .map(OrderItemRequest::getSku).toList();

        List<Inventory> inventoryList = inventoryRepository.findBySkuIn(skus);

        orderItems.forEach(orderItem -> {
            var inventory = inventoryList.stream()
                    .filter(value -> value.getSku().equals(orderItem.getSku()))
                    .findFirst();

            if (inventory.isEmpty()){
                erroList.add("Product with sku '" + orderItem.getSku() + "' does not exist");
            } else if (inventory.get().getQuantity() < orderItem.getQuantity()) {
                erroList.add("Product with sku '" + orderItem.getSku() + "' has insufficient quantity");
            }
        });

        return (!erroList.isEmpty()) ? new BaseResponse(erroList.toArray(new String[0])) : new BaseResponse(null);


    }//method
}//class
