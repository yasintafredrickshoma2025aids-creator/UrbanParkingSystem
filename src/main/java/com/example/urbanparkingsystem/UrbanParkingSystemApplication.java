package com.example.urbanparkingsystem;

import com.example.urbanparkingsystem.entity.ParkingSlot;
import com.example.urbanparkingsystem.repository.ParkingSlotRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.List;

@SpringBootApplication
public class UrbanParkingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrbanParkingSystemApplication.class, args);
    }

    @Service
    public static class ParkingSlotService {

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
    }
}
