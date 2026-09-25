package com.example.urbanparkingsystem.repository;

import com.example.urbanparkingsystem.entity.ParkingInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingInvoiceRepository extends JpaRepository<ParkingInvoice, Long> {
}