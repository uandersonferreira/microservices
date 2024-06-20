package br.com.uanderson.products_service.controllers;

import br.com.uanderson.products_service.model.dtos.ProductRequest;
import br.com.uanderson.products_service.model.dtos.ProductResponse;
import br.com.uanderson.products_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    @Value("${spring.application.name}")
    private String serviceName;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  void addProduct(@RequestBody ProductRequest productRequest){
        productService.addProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        log.info("SERVICE - {}", serviceName);
        return productService.getAllProducts();
    }

}//class
