package com.example.CarRenting.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRenting.enums.Color;
import com.example.CarRenting.enums.FuelType;
import com.example.CarRenting.models.Car;
import com.example.CarRenting.models.Specification;
import com.example.CarRenting.repositories.CarRepository;


/**
 * Class handling the more complex methods for the Car class.
 * 
 * @see Car
 * @since 1.0
 * @author Florin Schikarski
 */
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
                      String color,
                      String fuelType,
                      Specification specification) {

        // getting id of specification for jpa method
        long specificationId = specificationService.getSpecification(specification.getNumSeats(), 
                                                                     specification.getNumDoors(), 
                                                                     specification.getHorsePower(),
                                                                     specification.getSpeedMax(), 
                                                                     specification.getWeightUnladen(),
                                                                     specification.getWeightMax())
                                                   .getId();

        // making fuelType and color upperCase for comparison
        fuelType = fuelType.toUpperCase();
        color = color.toUpperCase();

        // checking if color and fuelType are enum constants
        isEnum(color, Color.values());
        isEnum(fuelType, FuelType.values());

        // may contain douplicates
        List<Car> cars = carRepository.findByBrandAndModelAndColorAndFuelTypeAndSpecificationId(brand, 
                                                                                                model, 
                                                                                                Color.valueOf(color), 
                                                                                                FuelType.valueOf(fuelType), 
                                                                                                specificationId);
        if (cars.isEmpty())
            throw new IllegalStateException("Could not find a car with these attributes.");

        return cars.get(0);
    }


    public Car getById(Long id) {

        return carRepository.findById(id).orElseThrow(() -> 
            new IllegalStateException("Could not find car with id " + id + "."));
    }


    public List<Car> getAll() {

        return carRepository.findAll();
    }


    public List<Car> getAllByBrandAndModel(String brand, String model) {
        
        return carRepository.findAllByBrandAndModel(brand, model);
    }


    public List<Car> getAllByIsAvailable(Boolean isAvailable) {
        
        return carRepository.findAllByIsAvailable(isAvailable);
    }


    public List<Car> getAllByFuelType(String fuelType) {

        // making fuelType upperCase for comparison
        fuelType = fuelType.toUpperCase();

        // checking if input is an enum constant
        isEnum(fuelType, FuelType.values());

        return carRepository.findAllByFuelType(FuelType.valueOf(fuelType));
    }


    public List<Car> getAllByColor(String color) {

        // making fuelType upperCase for comparison
        color = color.toUpperCase();

        // checking if 'color' is a an enum constant
        isEnum(color, Color.values());

        return carRepository.findAllByColor(Color.valueOf(color));
    }


    public List<Car> getAllBySpecifiaction(Specification specification) {

        return   carRepository.findAllBySpecificationId(specification.getId());
    }


    public Boolean existsByBrand(String brand) {

        return carRepository.existsByBrand(brand);
    }


    public Boolean existsByModel(String model) {
        
        return carRepository.existsByModel(model);
    }


    public void delete(Car car) {

        // getting a single car because the repo may find douplicates
        car = getCar(car.getBrand(), 
                     car.getModel(), 
                     car.getColor().name(), 
                     car.getFuelType().name(), 
                     car.getSpecification());

        carRepository.delete(car);
    }


    public void deleteAll() {

        carRepository.deleteAll();
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


    /**
     * Checks if given enum contains 'str'.
     * 
     * @param <T> the enum.
     * @param str the String that might be included in the enum.
     * @param enumArray array with all enum constants.
     * @return true if array contains the String.
     */
    private<T> boolean isEnum(String str, T[] enumArray) {

        boolean isEnum = Arrays.stream(enumArray)
                               .anyMatch(enumConstant -> enumConstant.toString().equals(str));
        
        if (!isEnum) 
            throw new IllegalStateException("String input is not an enum constant.");

        return true;
    }
}