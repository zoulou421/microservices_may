package com.formationkilo.billing_service.web;

import com.formationkilo.billing_service.entities.Bill;
import com.formationkilo.billing_service.feign.CustomerRestClient;
import com.formationkilo.billing_service.feign.ProductRestClient;
import com.formationkilo.billing_service.repositories.BillRepository;
import com.formationkilo.billing_service.repositories.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
    @Autowired
    public BillRepository billRepository;
    @Autowired
    public ProductItemRepository productItemRepository;
    @Autowired
    public ProductRestClient productRestClient;
    @Autowired
    public CustomerRestClient customerRestClient;

    @GetMapping("/bills/{id}")
    public Bill getBill(@PathVariable Long id){
        Bill bill =billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(productRestClient.getProductById(productItem.getProductId()));
        });
        return bill;
    }
}
