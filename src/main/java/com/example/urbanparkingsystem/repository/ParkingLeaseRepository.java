package com.example.urbanparkingsystem.repository;

import com.example.urbanparkingsystem.entity.ParkingLease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLeaseRepository extends JpaRepository<ParkingLease, Long> {
}