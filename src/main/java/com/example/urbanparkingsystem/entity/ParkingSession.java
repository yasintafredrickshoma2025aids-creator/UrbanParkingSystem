package com.example.urbanparkingsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ParkingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vehicleId;

    private Long parkingSlotId;

    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    private Long durationMinutes;

    private Double totalAmount;

    private String retrievalCode;

    private String status;
}