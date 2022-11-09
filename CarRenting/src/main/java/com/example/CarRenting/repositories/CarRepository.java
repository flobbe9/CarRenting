package com.example.CarRenting.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CarRenting.enums.Color;
import com.example.CarRenting.enums.FuelType;
import com.example.CarRenting.models.Car;
import com.example.CarRenting.models.Specification;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByBrandAndModelAndColorAndFuelTypeAndSpecification(String brand, 
                                                                         String model, 
                                                                         Color color, 
                                                                         FuelType fuelType, 
                                                                         Specification specification);
    
    List<Car> findAllByBrandAndModel(String brand, String model);   
    
    List<Car> findAllByIsAvailable(boolean isAvailable);
    
}