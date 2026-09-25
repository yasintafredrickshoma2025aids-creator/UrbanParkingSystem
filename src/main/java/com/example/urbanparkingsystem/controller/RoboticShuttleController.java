package com.example.urbanparkingsystem.controller;

import com.example.urbanparkingsystem.entity.RoboticShuttle;
import com.example.urbanparkingsystem.service.RoboticShuttleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/robotic-shuttles")
public class RoboticShuttleController {

    private final RoboticShuttleService roboticShuttleService;

    public RoboticShuttleController(
            RoboticShuttleService roboticShuttleService) {

        this.roboticShuttleService = roboticShuttleService;
    }

    @PostMapping
    public RoboticShuttle addShuttle(
            @RequestBody RoboticShuttle shuttle) {

        return roboticShuttleService.addShuttle(shuttle);
    }

    @GetMapping
    public List<RoboticShuttle> getAllShuttles() {
        return roboticShuttleService.getAllShuttles();
    }

    @GetMapping("/{id}")
    public RoboticShuttle getShuttleById(
            @PathVariable Long id) {

        return roboticShuttleService.getShuttleById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteShuttle(
            @PathVariable Long id) {

        roboticShuttleService.deleteShuttle(id);

        return "Robotic shuttle deleted successfully";
    }
}