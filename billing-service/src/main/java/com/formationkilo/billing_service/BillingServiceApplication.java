package com.formationkilo.billing_service;

import com.formationkilo.billing_service.entities.Bill;
import com.formationkilo.billing_service.entities.ProductItem;
import com.formationkilo.billing_service.feign.CustomerRestClient;
import com.formationkilo.billing_service.feign.ProductRestClient;
import com.formationkilo.billing_service.model.Customer;
import com.formationkilo.billing_service.model.Product;
import com.formationkilo.billing_service.repositories.BillRepository;
import com.formationkilo.billing_service.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	/*@Bean
	CommandLineRunner start(
			BillRepository billRepository, ProductItemRepository productItemRepository,
			CustomerRestClient customerRestClient, ProductRestClient productRestClient
			){
	return args -> {
		Collection<Customer>customers=customerRestClient.getAllCustomers().getContent();

		Collection<Product>products=productRestClient.getAllProducts().getContent();

		customers.forEach(customer -> {
			Bill bill =Bill.builder()
					.billingDate(new Date())
					.customerId(customer.getId())
					.build();
			billRepository.save(bill);
			products.forEach(product -> {
				ProductItem productItem= ProductItem.builder()
						.bill(bill)
						.productId(product.getId())
						.quantity(1+new Random().nextInt(10))
						.unitPrice(product.getPrice())
						.build();
				productItemRepository.save(productItem);
			});
		});

	};

	}*/

	@Bean
	CommandLineRunner start(
			BillRepository billRepository,
			ProductItemRepository productItemRepository,
			CustomerRestClient customerRestClient,
			ProductRestClient productRestClient) {
		return args -> {

			Map<String, Object> customerResponse = customerRestClient.getAllCustomers();
			Map<String, Object> embeddedCustomers = (Map<String, Object>) customerResponse.get("_embedded");
			List<Map<String, Object>> customerList = (List<Map<String, Object>>) embeddedCustomers.get("customers");

			Map<String, Object> productResponse = productRestClient.getAllProducts();
			Map<String, Object> embeddedProducts = (Map<String, Object>) productResponse.get("_embedded");
			List<Map<String, Object>> productList = (List<Map<String, Object>>) embeddedProducts.get("products");

			customerList.forEach(customerMap -> {
				Long customerId = Long.valueOf(customerMap.get("id").toString());

				Bill bill = Bill.builder()
						.billingDate(new Date())
						.customerId(customerId)
						.build();
				billRepository.save(bill);

				productList.forEach(productMap -> {
					Long productId = Long.valueOf(productMap.get("id").toString());
					Double unitPrice = Double.valueOf(productMap.get("price").toString());

					ProductItem productItem = ProductItem.builder()
							.bill(bill)
							.productId(productId)
							.quantity(1 + new Random().nextInt(10))
							.unitPrice(unitPrice)
							.build();
					productItemRepository.save(productItem);
				});
			});
		};
	}

}
