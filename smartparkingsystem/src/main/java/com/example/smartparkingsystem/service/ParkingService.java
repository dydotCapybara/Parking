package com.example.smartparkingsystem.service;

import com.example.smartparkingsystem.model.ParkingSpot;
import com.example.smartparkingsystem.model.User;
import com.example.smartparkingsystem.model.Vehicle;
import com.example.smartparkingsystem.repository.ParkingSpotRepository;
import com.example.smartparkingsystem.repository.UserRepository;
import com.example.smartparkingsystem.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<ParkingSpot> getAvailableSpots() {
        return parkingSpotRepository.findByIsAvailableTrue();
    }

    public void reserveSpot(Long userId, Long vehicleId, Long spotId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(vehicleId);
        Optional<ParkingSpot> spotOpt = parkingSpotRepository.findById(spotId);

        if (userOpt.isPresent() && vehicleOpt.isPresent() && spotOpt.isPresent()) {
            User user = userOpt.get();
            Vehicle vehicle = vehicleOpt.get();
            ParkingSpot spot = spotOpt.get();

            // 使用type字段直接比较
            if ("Motorcycle".equals(vehicle.getType()) && spot.getId() % 2 == 0) {
                // 假设偶数ID的车位更适合摩托车
                spot.setAvailable(false);
                parkingSpotRepository.save(spot);
                System.out.println(user.getName() + " has reserved motorcycle spot " + spot.getId() + " for " + vehicle.getLicensePlate());
            } else if ("Car".equals(vehicle.getType()) && spot.getId() % 2 != 0) {
                // 奇数ID的车位更适合小汽车
                spot.setAvailable(false);
                parkingSpotRepository.save(spot);
                System.out.println(user.getName() + " has reserved car spot " + spot.getId() + " for " + vehicle.getLicensePlate());
            } else {
                System.out.println("No suitable spot found for this vehicle type.");
            }
        } else {
            System.out.println("User, vehicle or spot not found.");
        }
    }

    public void releaseSpot(Long spotId) {
        Optional<ParkingSpot> spotOpt = parkingSpotRepository.findById(spotId);
        if (spotOpt.isPresent()) {
            ParkingSpot spot = spotOpt.get();
            spot.setAvailable(true);
            parkingSpotRepository.save(spot);
            System.out.println("Spot " + spot.getId() + " has been released.");
        }
    }
}