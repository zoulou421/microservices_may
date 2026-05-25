package com.formationkilo.billing_service.entities;

import com.formationkilo.billing_service.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Bill {
    @Id
    @GeneratedValue
    private Long id;
    private Date billingDate;
    private long customerId;
    @OneToMany(mappedBy = "bill")
    private List<ProductItem>productItems=new ArrayList<>();
    @Transient private Customer customer;
}
