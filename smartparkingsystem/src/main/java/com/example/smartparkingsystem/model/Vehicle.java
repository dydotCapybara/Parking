package com.example.smartparkingsystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String licensePlate;
    private  String type; // e.g., Car, Motorcycle, Truck

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 添加getType方法
    public String getType() {
        return this.type;
    }

    public Long getId() {
        return this.id;
    }
}