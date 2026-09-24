package com.example.urbanparkingsystem.service;

import com.example.urbanparkingsystem.entity.ParkingSlot;
import com.example.urbanparkingsystem.repository.ParkingSlotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSlotService {

    private final ParkingSlotRepository parkingSlotRepository;

    public ParkingSlotService(ParkingSlotRepository parkingSlotRepository) {
        this.parkingSlotRepository = parkingSlotRepository;
    }

    public ParkingSlot addParkingSlot(ParkingSlot parkingSlot) {
        return parkingSlotRepository.save(parkingSlot);
    }

    public List<ParkingSlot> getAllParkingSlots() {
        return parkingSlotRepository.findAll();
    }

    public ParkingSlot getParkingSlotById(Long id) {
        return parkingSlotRepository.findById(id).orElse(null);
    }

    public void deleteParkingSlot(Long id) {
        parkingSlotRepository.deleteById(id);
    }

    // Automatically park a vehicle
    public ParkingSlot parkVehicle(Long vehicleId) {

        List<ParkingSlot> parkingSlots = parkingSlotRepository.findAll();

        // Check if vehicle is already parked
        for (ParkingSlot slot : parkingSlots) {

            if (vehicleId.equals(slot.getVehicleId())
                    && "OCCUPIED".equals(slot.getStatus())) {

                return slot;
            }
        }

        // Find an available parking slot
        for (ParkingSlot slot : parkingSlots) {

            if ("AVAILABLE".equals(slot.getStatus())
                    && slot.getVehicleId() == null) {

                slot.setStatus("OCCUPIED");
                slot.setVehicleId(vehicleId);

                return parkingSlotRepository.save(slot);
            }
        }

        return null;
    }

    // Retrieve a parked vehicle
    public ParkingSlot retrieveVehicle(Long vehicleId) {

        List<ParkingSlot> parkingSlots = parkingSlotRepository.findAll();

        for (ParkingSlot slot : parkingSlots) {

            if (vehicleId.equals(slot.getVehicleId())
                    && "OCCUPIED".equals(slot.getStatus())) {

                slot.setStatus("AVAILABLE");
                slot.setVehicleId(null);

                return parkingSlotRepository.save(slot);
            }
        }

        return null;
    }
}