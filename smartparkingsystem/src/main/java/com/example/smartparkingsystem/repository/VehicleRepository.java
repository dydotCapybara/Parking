package com.example.smartparkingsystem.repository;

import com.example.smartparkingsystem.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle findByLicensePlate(String licensePlate);
}