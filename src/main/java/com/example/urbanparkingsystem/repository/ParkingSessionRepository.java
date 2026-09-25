package com.example.urbanparkingsystem.repository;

import com.example.urbanparkingsystem.entity.ParkingSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {
}