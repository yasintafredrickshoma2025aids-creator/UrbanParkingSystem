package com.example.urbanparkingsystem.service;

import com.example.urbanparkingsystem.entity.ParkingLease;
import com.example.urbanparkingsystem.repository.ParkingLeaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLeaseService {

    private final ParkingLeaseRepository parkingLeaseRepository;

    public ParkingLeaseService(ParkingLeaseRepository parkingLeaseRepository) {
        this.parkingLeaseRepository = parkingLeaseRepository;
    }

    public ParkingLease addLease(ParkingLease parkingLease) {
        return parkingLeaseRepository.save(parkingLease);
    }

    public List<ParkingLease> getAllLeases() {
        return parkingLeaseRepository.findAll();
    }

    public ParkingLease getLeaseById(Long id) {
        return parkingLeaseRepository.findById(id).orElse(null);
    }

    public void deleteLease(Long id) {
        parkingLeaseRepository.deleteById(id);
    }
}