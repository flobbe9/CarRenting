package com.example.CarRenting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRenting.repositories.CarRepository;


@Service
public class CarService {
    
    @Autowired
    private CarRepository carRepository;
}