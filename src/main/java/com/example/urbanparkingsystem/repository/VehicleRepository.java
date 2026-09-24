package com.example.urbanparkingsystem.repository;

import com.example.urbanparkingsystem.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
