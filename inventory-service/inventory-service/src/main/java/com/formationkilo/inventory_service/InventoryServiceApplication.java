package com.formationkilo.inventory_service;

import com.formationkilo.inventory_service.entities.Product;
import com.formationkilo.inventory_service.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository){
		return args -> {
			productRepository.save(Product.builder().name("Ordinateur").price(300.0).quantity(5).build());
			productRepository.save(Product.builder().name("Telephone").price(500.0).quantity(30).build());
		};
	}
}
