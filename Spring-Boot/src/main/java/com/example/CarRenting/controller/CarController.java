package com.example.CarRenting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.CarRenting.enums.Color;
import com.example.CarRenting.enums.FuelType;
import com.example.CarRenting.models.Car;
import com.example.CarRenting.models.Specification;
import com.example.CarRenting.services.CarService;


@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;
    

    @PostMapping("/saveCar")
    public Car saveCar(@RequestBody Car car) {

        return carService.saveCar(car);
    }


    @GetMapping("/getCar")
    public Car getCar(@RequestParam("brand") String brand,
                      @RequestParam("model") String model,
                      @RequestParam("color") Color color,
                      @RequestParam("fuelType") FuelType fuelType,
                      @RequestBody Specification specification) {

        return carService.getCar(brand, model, color, fuelType, specification);
    }


    @GetMapping("/getAll")
    public List<Car> getAll() {

        return carService.getAll();
    }
    
    
    @GetMapping("/getAllByBrandAndModel")
    public List<Car> getAllByBrandAndModel(@RequestParam("brand") String brand,
    @RequestParam("model") String model) {
        
        return carService.getAllByBrandAndModel(brand, model);
    }
    
    
    @GetMapping("/getAllByIsAvailable")
    public List<Car> getAllByIsAvailable(@RequestParam("isAvailable") boolean isAvailable) {
        
        return carService.getAllByIsAvailable(isAvailable);
    }


    @GetMapping("/getAllByFuelType")
    public List<Car> getAllByFuelType(@RequestParam("fuelType") FuelType fuelType) {

        return carService.getAllByFuelType(fuelType);
    }


    @GetMapping("/getAllByColor")
    public List<Car> getAllByColor(@RequestParam("color") Color color) {

        return carService.getAllByColor(color);
    }


    @GetMapping("/getAllBySpecification")
    public List<Car> getAllBySpecification(@RequestParam("specification") Specification specification) {

        return carService.getAllBySpecifiaction(specification);
    }


    @GetMapping("/existsByBrand")
    public boolean existsByBrand(@RequestParam("brand") String brand) {

        return carService.existsByBrand(brand);
    }


    @GetMapping("/existsByModel")
    public boolean existsByModel(@RequestParam("model") String model) {

        return carService.existsByModel(model);
    }

    
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody Car car) {

        carService.delete(car);
    }
}