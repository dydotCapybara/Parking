package com.example.smartparkingsystem.repository;

import com.example.smartparkingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}