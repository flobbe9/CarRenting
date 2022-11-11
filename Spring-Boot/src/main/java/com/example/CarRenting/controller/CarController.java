package com.example.CarRenting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    

    @PostMapping("/addNew")
    public Car addNew(@RequestBody Car car) {

        return carService.addNew(car);
    }


    @GetMapping("/getCar")
    public Car getCar(@RequestParam("brand") String brand,
                      @RequestParam("model") String model,
                      @RequestParam("color") Color color,
                      @RequestParam("fuelType") FuelType fuelType,
                      @RequestBody Specification specification) {

        return carService.getCar(brand, model, color, fuelType, specification);
    }


    @GetMapping("/getAllByBrandAndModel")
    public List<Car> getAllByBrandAndModel(@RequestParam("brand") String brand,
                                           @RequestParam("model") String model) {

        return carService.getAllByBrandAndModel(brand, model);
    }


    @GetMapping("/getAllByIsAvailable")
    public List<Car> getAllByBrandAndModel(@RequestParam("isAvailable") boolean isAvailable) {

        return carService.getAllByIsAvailable(isAvailable);
    }
}