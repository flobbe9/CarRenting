package com.example.CarRenting.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CarRenting.enums.Color;
import com.example.CarRenting.enums.FuelType;
import com.example.CarRenting.models.Car;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByBrandAndModelAndColorAndFuelTypeAndSpecificationId(String brand, 
                                                                           String model, 
                                                                           Color color, 
                                                                           FuelType fuelType, 
                                                                           Long specificationId);
    
    List<Car> findAllByBrandAndModel(String brand, String model);   
    
    List<Car> findAllByIsAvailable(Boolean isAvailable);

    List<Car> findAllByFuelType(FuelType fuelType);
    
    List<Car> findAllByColor(Color color);

    List<Car> findAllBySpecificationId(Long specificationId);

    Boolean existsByBrand(String brand);

    Boolean existsByModel(String model);
}