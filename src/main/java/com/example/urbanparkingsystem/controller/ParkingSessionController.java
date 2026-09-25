package com.example.urbanparkingsystem.controller;

import com.example.urbanparkingsystem.entity.ParkingSession;
import com.example.urbanparkingsystem.service.ParkingSessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-sessions")
public class ParkingSessionController {

    private final ParkingSessionService parkingSessionService;

    public ParkingSessionController(ParkingSessionService parkingSessionService) {
        this.parkingSessionService = parkingSessionService;
    }

    // Create a parking session manually
    @PostMapping
    public ParkingSession createSession(@RequestBody ParkingSession parkingSession) {
        return parkingSessionService.createSession(parkingSession);
    }

    // Get all parking sessions
    @GetMapping
    public List<ParkingSession> getAllSessions() {
        return parkingSessionService.getAllSessions();
    }

    // Get parking session by ID
    @GetMapping("/{id}")
    public ParkingSession getSessionById(@PathVariable Long id) {
        return parkingSessionService.getSessionById(id);
    }

    // Automatic vehicle entry
    @PostMapping("/entry/{vehicleId}")
    public ParkingSession vehicleEntry(@PathVariable Long vehicleId) {
        return parkingSessionService.vehicleEntry(vehicleId);
    }

    // Vehicle exit
    @PostMapping("/exit/{vehicleId}")
    public ParkingSession vehicleExit(@PathVariable Long vehicleId) {
        return parkingSessionService.vehicleExit(vehicleId);
    }
}