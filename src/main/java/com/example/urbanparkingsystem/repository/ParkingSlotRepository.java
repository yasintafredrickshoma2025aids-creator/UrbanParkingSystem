package com.example.urbanparkingsystem.repository;

import com.example.urbanparkingsystem.entity.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
}
