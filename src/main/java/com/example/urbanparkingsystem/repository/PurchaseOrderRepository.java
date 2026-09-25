package com.example.urbanparkingsystem.repository;

import com.example.urbanparkingsystem.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository
        extends JpaRepository<PurchaseOrder, Long> {
}