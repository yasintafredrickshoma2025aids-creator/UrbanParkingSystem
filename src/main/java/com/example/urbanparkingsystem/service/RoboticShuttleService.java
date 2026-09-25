package com.example.urbanparkingsystem.service;

import com.example.urbanparkingsystem.entity.RoboticShuttle;
import com.example.urbanparkingsystem.repository.RoboticShuttleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoboticShuttleService {

    private final RoboticShuttleRepository roboticShuttleRepository;

    public RoboticShuttleService(
            RoboticShuttleRepository roboticShuttleRepository) {

        this.roboticShuttleRepository = roboticShuttleRepository;
    }

    public RoboticShuttle addShuttle(RoboticShuttle shuttle) {
        return roboticShuttleRepository.save(shuttle);
    }

    public List<RoboticShuttle> getAllShuttles() {
        return roboticShuttleRepository.findAll();
    }

    public RoboticShuttle getShuttleById(Long id) {
        return roboticShuttleRepository.findById(id).orElse(null);
    }

    public void deleteShuttle(Long id) {
        roboticShuttleRepository.deleteById(id);
    }
}