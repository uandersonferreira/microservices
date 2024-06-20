package br.com.uanderson.products_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductsServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductsServiceApplication.class, args);
	}

}
