package com.example.urbanparkingsystem.repository;

import com.example.urbanparkingsystem.entity.VendorBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorBillRepository
        extends JpaRepository<VendorBill, Long> {
}