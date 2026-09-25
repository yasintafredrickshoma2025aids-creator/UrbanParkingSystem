package com.example.urbanparkingsystem.controller;

import com.example.urbanparkingsystem.entity.ParkingTariff;
import com.example.urbanparkingsystem.service.ParkingTariffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-tariffs")
public class ParkingTariffController {

    private final ParkingTariffService parkingTariffService;

    public ParkingTariffController(ParkingTariffService parkingTariffService) {
        this.parkingTariffService = parkingTariffService;
    }

    // Add parking tariff
    @PostMapping
    public ParkingTariff addTariff(@RequestBody ParkingTariff parkingTariff) {
        return parkingTariffService.addTariff(parkingTariff);
    }

    // Get all parking tariffs
    @GetMapping
    public List<ParkingTariff> getAllTariffs() {
        return parkingTariffService.getAllTariffs();
    }

    // Get tariff by ID
    @GetMapping("/{id}")
    public ParkingTariff getTariffById(@PathVariable Long id) {
        return parkingTariffService.getTariffById(id);
    }

    // Update parking tariff
    @PutMapping("/{id}")
    public ParkingTariff updateTariff(
            @PathVariable Long id,
            @RequestBody ParkingTariff parkingTariff) {

        return parkingTariffService.updateTariff(id, parkingTariff);
    }

    // Delete parking tariff
    @DeleteMapping("/{id}")
    public String deleteTariff(@PathVariable Long id) {

        parkingTariffService.deleteTariff(id);

        return "Parking tariff deleted successfully";
    }
}