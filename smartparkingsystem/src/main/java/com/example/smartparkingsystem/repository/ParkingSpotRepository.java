package com.example.smartparkingsystem.repository;

import com.example.smartparkingsystem.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    List<ParkingSpot> findByIsAvailableTrue();
}