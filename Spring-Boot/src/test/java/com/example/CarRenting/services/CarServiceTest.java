package com.example.CarRenting.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void testSaveCar() {

        // setting up falsy car attributes and correcting them afterwards
        specification.setWeightMax(1d);
        assertThrows(IllegalStateException.class, () -> carService.saveCar(car));
        specification.setWeightMax(2300d);

        assertEquals(car, carService.saveCar(car));
    }


    @Test
    @Order(2)
    void testGetCar() {

        assertEquals(car, carService.getCar(car.getBrand(), 
                                            car.getModel(),
                                            car.getColor().name(),
                                            car.getFuelType().name(),
                                            car.getSpecification()));
    }


    @Test
    @Order(3)
    void testGetAllByBrandAndModel() {

        assertFalse(carService.getAllByBrandAndModel(car.getBrand(),
                                                     car.getModel())
                              .isEmpty());
    }


    @Test
    @Order(4)
    void testGetAllByIsAvailable() {

        assertFalse(carService.getAllByIsAvailable(true).isEmpty());
    }


    @Test
    @Order(5) 
    void testGetAllBySpecifiaction() {

        specification.setId(1l);

        assertFalse(carService.getAllBySpecifiaction(car.getSpecification()).isEmpty());
    }


    @Test 
    @Order(6)
    void testExistsByBrand() {

        assertTrue(carService.existsByBrand(car.getBrand()));
    }


    @Test
    @Order(7)
    void testDelete() {

        // setting id of car so it can be deleted
        car.setId(1l);

        carService.delete(car);

        assertThrows(IllegalStateException.class, () -> carService.getAll().isEmpty());
    }
}