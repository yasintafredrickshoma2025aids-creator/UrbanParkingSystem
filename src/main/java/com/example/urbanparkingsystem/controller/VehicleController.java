package com.example.urbanparkingsystem.controller;

import com.example.urbanparkingsystem.entity.Vehicle;
import com.example.urbanparkingsystem.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle);
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return "Vehicle deleted successfully";
    }

    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        Vehicle existingVehicle = vehicleService.getVehicleById(id);

        if (existingVehicle == null) {
            return null;
        }

        existingVehicle.setRegistrationNumber(vehicle.getRegistrationNumber());
        existingVehicle.setVehicleType(vehicle.getVehicleType());
        existingVehicle.setOwnerName(vehicle.getOwnerName());

        return vehicleService.addVehicle(existingVehicle);
    }
}