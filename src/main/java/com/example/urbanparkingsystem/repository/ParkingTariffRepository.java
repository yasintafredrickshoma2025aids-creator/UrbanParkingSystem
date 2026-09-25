package com.example.urbanparkingsystem.repository;

import com.example.urbanparkingsystem.entity.ParkingTariff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingTariffRepository extends JpaRepository<ParkingTariff, Long> {
}