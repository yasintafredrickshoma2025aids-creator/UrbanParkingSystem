package com.example.urbanparkingsystem.controller;

import com.example.urbanparkingsystem.entity.ParkingSlot;
import com.example.urbanparkingsystem.service.ParkingSlotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-slots")
public class ParkingSlotController {

    private final ParkingSlotService parkingSlotService;

    public ParkingSlotController(ParkingSlotService parkingSlotService) {
        this.parkingSlotService = parkingSlotService;
    }

    @PostMapping
    public ParkingSlot addParkingSlot(@RequestBody ParkingSlot parkingSlot) {
        return parkingSlotService.addParkingSlot(parkingSlot);
    }

    @GetMapping
    public List<ParkingSlot> getAllParkingSlots() {
        return parkingSlotService.getAllParkingSlots();
    }

    @GetMapping("/{id}")
    public ParkingSlot getParkingSlotById(@PathVariable Long id) {
        return parkingSlotService.getParkingSlotById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteParkingSlot(@PathVariable Long id) {
        parkingSlotService.deleteParkingSlot(id);
        return "Parking slot deleted successfully";
    }

    @PostMapping("/park/{vehicleId}")
    public ParkingSlot parkVehicle(@PathVariable Long vehicleId) {
        return parkingSlotService.parkVehicle(vehicleId);
    }

    @PostMapping("/retrieve/{vehicleId}")
    public ParkingSlot retrieveVehicle(@PathVariable Long vehicleId) {
        return parkingSlotService.retrieveVehicle(vehicleId);
    }
}