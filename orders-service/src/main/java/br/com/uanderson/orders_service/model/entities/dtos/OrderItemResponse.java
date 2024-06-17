package br.com.uanderson.orders_service.model.entities.dtos;

public record OrderItemResponse (
         Long id,
         String sku,
         Double price,
         Long quantity
){ }
