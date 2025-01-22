package com.example.smartparkingsystem.controller;

import com.example.smartparkingsystem.model.ParkingSpot;
import com.example.smartparkingsystem.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @GetMapping("/available")
    public List<ParkingSpot> getAvailableSpots() {
        return parkingService.getAvailableSpots();
    }

    @PostMapping("/{userId}/{vehicleId}/{spotId}/reserve")
    public void reserveSpot(@PathVariable Long userId, @PathVariable Long vehicleId, @PathVariable Long spotId) {
        parkingService.reserveSpot(userId, vehicleId, spotId);
    }

    @PostMapping("/{spotId}/release")
    public void releaseSpot(@PathVariable Long spotId) {
        parkingService.releaseSpot(spotId);
    }
}