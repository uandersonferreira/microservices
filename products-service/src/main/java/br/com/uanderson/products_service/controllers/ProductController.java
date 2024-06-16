package br.com.uanderson.products_service.controllers;

import br.com.uanderson.products_service.model.dtos.ProductRequest;
import br.com.uanderson.products_service.model.dtos.ProductResponse;
import br.com.uanderson.products_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  void addProduct(@RequestBody ProductRequest productRequest){
        productService.addProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }

}//class
