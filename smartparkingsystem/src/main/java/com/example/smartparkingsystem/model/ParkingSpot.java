package com.example.smartparkingsystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isAvailable;

    public Long getId() {
        return id;
    }
}