package com.example.urbanparkingsystem.service;

import com.example.urbanparkingsystem.entity.ParkingTariff;
import com.example.urbanparkingsystem.repository.ParkingTariffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingTariffService {

    private final ParkingTariffRepository parkingTariffRepository;

    public ParkingTariffService(ParkingTariffRepository parkingTariffRepository) {
        this.parkingTariffRepository = parkingTariffRepository;
    }

    // Add a parking tariff
    public ParkingTariff addTariff(ParkingTariff parkingTariff) {
        return parkingTariffRepository.save(parkingTariff);
    }

    // Get all parking tariffs
    public List<ParkingTariff> getAllTariffs() {
        return parkingTariffRepository.findAll();
    }

    // Get tariff by ID
    public ParkingTariff getTariffById(Long id) {
        return parkingTariffRepository.findById(id).orElse(null);
    }

    // Update parking tariff
    public ParkingTariff updateTariff(Long id, ParkingTariff parkingTariff) {

        ParkingTariff existingTariff =
                parkingTariffRepository.findById(id).orElse(null);

        if (existingTariff == null) {
            return null;
        }

        existingTariff.setHourlyRate(parkingTariff.getHourlyRate());
        existingTariff.setDescription(parkingTariff.getDescription());

        return parkingTariffRepository.save(existingTariff);
    }

    // Delete parking tariff
    public void deleteTariff(Long id) {
        parkingTariffRepository.deleteById(id);
    }
}