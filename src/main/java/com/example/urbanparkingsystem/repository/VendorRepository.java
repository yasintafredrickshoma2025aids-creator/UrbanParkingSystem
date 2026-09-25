package com.example.urbanparkingsystem.repository;

import com.example.urbanparkingsystem.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}