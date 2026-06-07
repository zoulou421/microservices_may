package com.formationkilo.customer_service;

import com.formationkilo.customer_service.config.CustomerConfigParams;
import com.formationkilo.customer_service.entities.Customer;
import com.formationkilo.customer_service.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository){
		return args ->{
          customerRepository.save(new Customer(null, "Mister BEBY","bonevy.beby@gmail.com"));
		  customerRepository.save(Customer.builder().name("MOUSSA").email("moussa@gmail.com").build());
		};
	}
}
