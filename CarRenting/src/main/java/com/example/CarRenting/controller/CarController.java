package com.example.CarRenting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CarRenting.models.Car;
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
}