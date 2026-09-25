package com.example.urbanparkingsystem.controller;

import com.example.urbanparkingsystem.entity.ParkingLease;
import com.example.urbanparkingsystem.service.ParkingLeaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-leases")
public class ParkingLeaseController {

    private final ParkingLeaseService parkingLeaseService;

    public ParkingLeaseController(ParkingLeaseService parkingLeaseService) {
        this.parkingLeaseService = parkingLeaseService;
    }

    @PostMapping
    public ParkingLease addLease(@RequestBody ParkingLease parkingLease) {
        return parkingLeaseService.addLease(parkingLease);
    }

    @GetMapping
    public List<ParkingLease> getAllLeases() {
        return parkingLeaseService.getAllLeases();
    }

    @GetMapping("/{id}")
    public ParkingLease getLeaseById(@PathVariable Long id) {
        return parkingLeaseService.getLeaseById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteLease(@PathVariable Long id) {
        parkingLeaseService.deleteLease(id);
        return "Parking lease deleted successfully";
    }
}