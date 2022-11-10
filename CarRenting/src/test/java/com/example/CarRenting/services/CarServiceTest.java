package com.example.CarRenting.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.CarRenting.enums.Color;
import com.example.CarRenting.enums.FuelType;
import com.example.CarRenting.models.Car;
import com.example.CarRenting.models.Specification;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class CarServiceTest {

    @Autowired
    private CarService carService;

    private Specification specification = new Specification(5, 
                                                            4, 
                                                            90,
                                                            180,
                                                            1100d,
                                                            2300d);

    private Car car = new Car("VW", 
                              "Golf", 
                              Color.RED, 
                              FuelType.BENZINE, 
                              specification);


    @Test
    @Order(1)
    void testAddNew() {

        assertEquals(car, carService.addNew(car));
    }


    @Test
    void testGetCar() {

        assertEquals(car, carService.getCar(car.getBrand(), 
                                            car.getModel(),
                                            car.getColor(),
                                            car.getFuelType(),
                                            car.getSpecification()));
    }


    @Test
    void testGetAllByBrandAndModel() {

        assertFalse(carService.getAllByBrandAndModel(car.getBrand(),
                                                     car.getModel())
                              .isEmpty());
    }


    @Test
    void testGetAllByIsAvailable() {

        assertFalse(carService.getAllByIsAvailable(true).isEmpty());
    }
}
