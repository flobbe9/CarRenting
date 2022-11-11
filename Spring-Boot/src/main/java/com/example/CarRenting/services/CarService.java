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

    @Autowired
    private SpecificationService specificationService;


    /**
     * Saves car entity to the db. Checks incoming input before saving.
     * 
     * @param car to add.
     * @return saved car.
     * @throws IllegalStateException if one car attribute is invalid.
     */
    public Car saveCar(Car car) {

        isValid(car);
        
        return carRepository.save(car);
    }


    public Car getCar(String brand,
                      String model,
                      Color color,
                      FuelType fuelType,
                      Specification specification) {

        long specificationId = specificationService.getSpecification(specification.getNumSeats(), 
                                                                     specification.getNumDoors(), 
                                                                     specification.getHp(),
                                                                     specification.getSpeedMax(), 
                                                                     specification.getWeightUnladen(),
                                                                     specification.getWeightMax())
                                                   .getId();
        
        return carRepository
                .findByBrandAndModelAndColorAndFuelTypeAndSpecificationId(brand, model, color, fuelType, specificationId)
                .orElseThrow(() -> 
                    new IllegalStateException("Could not find a car with these attributes."));
    }


    public List<Car> getAll() {

        List<Car> cars = carRepository.findAll();

        if (cars.isEmpty()) 
            throw new IllegalStateException("Could not find any car.");

        return cars;
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


    public List<Car> getAllByFuelType(FuelType fuelType) {

        List<Car> cars = carRepository.findAllByFuelType(fuelType);

        if (cars.size() == 0) 
            throw new IllegalStateException("Could not find any car with fuelType " + fuelType.name() + ".");
        

        return cars;
    }


    public List<Car> getAllByColor(Color color) {

        List<Car> cars = carRepository.findAllByColor(color);

        if (cars.size() == 0) 
            throw new IllegalStateException("Could not find any car with color " + color.name() + ".");

        return cars;
    }


    public List<Car> getAllBySpecifiaction(Specification specification) {

        List<Car> cars = carRepository.findAllBySpecificationId(specification.getId());

        if (cars.size() == 0) 
            throw new IllegalStateException("Could not find any car with these exact specifications.");

        return cars;
    }


    public Boolean existsByBrand(String brand) {

        return carRepository.existsByBrand(brand);
    }


    public Boolean existsByModel(String model) {
        
        return carRepository.existsByModel(model);
    }


    public void delete(Car car) {

        carRepository.delete(car);
    }


/////// helper methods:

    /**
     * Checks car attributes.
     * 
     * @param car to check.
     * @return true if all checks were successful.
     * @throws IllegalStateException if one attribute is not valid.
     */
    private boolean isValid(Car car) {

        // checking specification
        specificationService.isValid(car.getSpecification());

        return true;
    }
}