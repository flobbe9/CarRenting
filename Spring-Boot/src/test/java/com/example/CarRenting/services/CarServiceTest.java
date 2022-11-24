package com.example.CarRenting.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void resetMockDataAndDB() {

        // deleting all cars added by data.sql
        carService.deleteAll();

        // resetting mock data
        specification = new Specification(5, 
                                          4, 
                                          90,
                                          180,
                                          1100d,
                                          2300d);

        car = new Car("VW", 
                      "Golf", 
                      Color.RED, 
                      FuelType.BENZINE, 
                      specification);

        // adding mock data to database
        carService.saveCar(car);
    }

                            
    @Test
    @Order(1)
    void saveCar_shouldThrowException_ifWeightMaxLessThanWeightUnladen() {

        car.getSpecification().setWeightMax(1d);

        assertThrows(IllegalStateException.class, () -> carService.saveCar(car));
    }


    @Test
    @Order(2)
    void saveCar_shouldBeEqual_ifReturnsSavedCar() {

        assertEquals(car, carService.saveCar(car));
    }
    

    @Test
    @Order(3)
    void getCar_shouldBeEqual_ifFindsCar() {

        assertEquals(car, carService.getCar(car.getBrand(), 
                                            car.getModel(),
                                            car.getColor().name().toLowerCase(),
                                            car.getFuelType().name().toLowerCase(),
                                            car.getSpecification()));
    }


    @Test
    void getAllByBrandAndModel_carListShouldNotBeEmpty() {

        assertFalse(carService.getAllByBrandAndModel(car.getBrand(),
                                                     car.getModel())
                              .isEmpty());
    }


    @Test
    void getAllByIsAvailable_carListShouldNotBeEmpty() {

        assertFalse(carService.getAllByIsAvailable(true)
                              .isEmpty());
    }


    @Test
    void getAllBySpecifiaction_carListShouldNotBeEmpty() {
        
        assertFalse(carService.getAllBySpecifiaction(car.getSpecification())
                              .isEmpty());
    }


    @Test 
    void existsByBrand_shouldBeTrue_ifExists() {

        assertTrue(carService.existsByBrand(car.getBrand()));
    }


    @Test
    void delete_carListShouldBeEmpty() {

        carService.delete(car);

        assertTrue(carService.getAll().isEmpty());
    }
}