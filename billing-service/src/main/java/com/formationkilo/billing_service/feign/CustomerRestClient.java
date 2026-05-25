package com.formationkilo.billing_service.feign;


import com.formationkilo.billing_service.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "customer-service")
public interface CustomerRestClient {
    @GetMapping("/api/customers/{id}")
    Customer getCustomerById(@PathVariable Long id);

    /*@GetMapping("/api/customers")
    PagedModel<Customer> getAllCustomers();*/
    @GetMapping("/api/customers")
    Map<String, Object> getAllCustomers();
}
