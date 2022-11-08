package com.example.CarRenting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CarRenting.models.Car;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    
}
