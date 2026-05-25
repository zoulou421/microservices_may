package com.formationkilo.billing_service.repositories;

import com.formationkilo.billing_service.entities.Bill;
import com.formationkilo.billing_service.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductItemRepository extends JpaRepository<ProductItem,Long> {
}
