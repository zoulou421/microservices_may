package com.formationkilo.billing_service.repositories;


import com.formationkilo.billing_service.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BillRepository extends JpaRepository<Bill,Long> {
}
