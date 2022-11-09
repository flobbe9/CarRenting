package com.example.CarRenting.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRenting.enums.Color;
import com.example.CarRenting.enums.FuelType;
import com.example.CarRenting.models.Car;
import com.example.CarRenting.models.Specification;
import com.example.CarRenting.repositories.CarRepository;


@Service
public class CarService {
    
    @Autowired
    private CarRepository carRepository;


    public Car addNew(Car car) {
        
        return carRepository.save(car);
    }


    public Car getCar(String brand,
                      String model,
                      Color color,
                      FuelType fuelType,
                      Specification specification) {
        
        return carRepository
                .findByBrandAndModelAndColorAndFuelTypeAndSpecification(brand, model, color, fuelType, specification)
                .orElseThrow(() -> 
                    new IllegalStateException("Could not find a car with these attributes."));
    }


    public List<Car> getAllByBrandAndModel(String brand, String model) {
        
        List<Car> cars = carRepository.findAllByBrandAndModel(brand, model);

        if (cars.size() == 0) 
            throw new IllegalStateException("Could not find any car with brand " + brand + " and model " + model + "."); 

        return cars;
    }


    public List<Car> getAllByIsAvailable(boolean isAvailable) {

        List<Car> cars = carRepository.findAllByIsAvailable(isAvailable);

        if (cars.size() == 0) 
            throw new IllegalStateException("Could not find any car available.");

        return cars;
    }
}