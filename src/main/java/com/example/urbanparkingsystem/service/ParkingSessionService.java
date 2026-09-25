package com.example.urbanparkingsystem.service;

import com.example.urbanparkingsystem.entity.ParkingSession;
import com.example.urbanparkingsystem.entity.ParkingSlot;
import com.example.urbanparkingsystem.entity.ParkingTariff;
import com.example.urbanparkingsystem.repository.ParkingSessionRepository;
import com.example.urbanparkingsystem.repository.ParkingSlotRepository;
import com.example.urbanparkingsystem.repository.ParkingTariffRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingSessionService {

    private final ParkingSessionRepository parkingSessionRepository;
    private final ParkingSlotRepository parkingSlotRepository;
    private final ParkingTariffRepository parkingTariffRepository;

    public ParkingSessionService(
            ParkingSessionRepository parkingSessionRepository,
            ParkingSlotRepository parkingSlotRepository,
            ParkingTariffRepository parkingTariffRepository) {

        this.parkingSessionRepository = parkingSessionRepository;
        this.parkingSlotRepository = parkingSlotRepository;
        this.parkingTariffRepository = parkingTariffRepository;
    }

    // Create a parking session manually
    public ParkingSession createSession(ParkingSession parkingSession) {
        return parkingSessionRepository.save(parkingSession);
    }

    // Get all parking sessions
    public List<ParkingSession> getAllSessions() {
        return parkingSessionRepository.findAll();
    }

    // Get parking session by ID
    public ParkingSession getSessionById(Long id) {
        return parkingSessionRepository.findById(id).orElse(null);
    }

    // Automatically create a parking session when a vehicle enters
    public ParkingSession vehicleEntry(Long vehicleId) {

        List<ParkingSlot> parkingSlots = parkingSlotRepository.findAll();

        // Find an available parking slot
        for (ParkingSlot slot : parkingSlots) {

            if ("AVAILABLE".equals(slot.getStatus())
                    && slot.getVehicleId() == null) {

                // Park the vehicle
                slot.setStatus("OCCUPIED");
                slot.setVehicleId(vehicleId);

                parkingSlotRepository.save(slot);

                // Create parking session
                ParkingSession session = new ParkingSession();

                session.setVehicleId(vehicleId);
                session.setParkingSlotId(slot.getId());
                session.setEntryTime(LocalDateTime.now());
                session.setStatus("ACTIVE");

                // Generate retrieval code
                String retrievalCode = "RET-" +
                        UUID.randomUUID()
                                .toString()
                                .substring(0, 6)
                                .toUpperCase();

                session.setRetrievalCode(retrievalCode);

                return parkingSessionRepository.save(session);
            }
        }

        // No available parking slot
        return null;
    }

    // Vehicle exit
    public ParkingSession vehicleExit(Long vehicleId) {

        List<ParkingSession> sessions = parkingSessionRepository.findAll();

        // Find the active session for this vehicle
        for (ParkingSession session : sessions) {

            if (vehicleId.equals(session.getVehicleId())
                    && "ACTIVE".equals(session.getStatus())) {

                // Record exit time
                LocalDateTime exitTime = LocalDateTime.now();
                session.setExitTime(exitTime);

                // Calculate parking duration
                long durationMinutes = Duration.between(
                        session.getEntryTime(),
                        exitTime
                ).toMinutes();

                // Minimum chargeable time = 1 minute
                if (durationMinutes <= 0) {
                    durationMinutes = 1;
                }

                session.setDurationMinutes(durationMinutes);

                // Get the latest parking tariff
                List<ParkingTariff> tariffs =
                        parkingTariffRepository.findAll();

                if (tariffs.isEmpty()) {
                    return null;
                }

                ParkingTariff parkingTariff =
                        tariffs.get(tariffs.size() - 1);

                double hourlyRate = parkingTariff.getHourlyRate();

                // Charge every started hour
                double totalAmount =
                        Math.ceil(durationMinutes / 60.0)
                                * hourlyRate;

                session.setTotalAmount(totalAmount);

                // Mark session as completed
                session.setStatus("COMPLETED");

                // Make parking slot available again
                ParkingSlot parkingSlot =
                        parkingSlotRepository
                                .findById(session.getParkingSlotId())
                                .orElse(null);

                if (parkingSlot != null) {

                    parkingSlot.setStatus("AVAILABLE");
                    parkingSlot.setVehicleId(null);

                    parkingSlotRepository.save(parkingSlot);
                }

                // Save completed parking session
                return parkingSessionRepository.save(session);
            }
        }

        // No active session found
        return null;
    }
}