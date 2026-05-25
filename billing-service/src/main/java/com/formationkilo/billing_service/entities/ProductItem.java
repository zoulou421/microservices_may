package com.formationkilo.billing_service.entities;

import com.formationkilo.billing_service.model.Product;
import jakarta.persistence.*;

@Entity
public class ProductItem {
    @Id
    @GeneratedValue
    private Long id;
    private long productId;
    @ManyToOne
    private Bill bill;
    private int quantity;
    private double unitPrice;
    @Transient private Product product;
}
