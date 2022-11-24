package com.example.CarRenting.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
public class SpecificationServiceTest {

    @Autowired
    private SpecificationService specificationService;

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
    void restoreObjects() {

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

        // adding mock data to database if it was deleted
        if (carService.getAll().isEmpty())
            carService.saveCar(car);
    }

    @Test
    @Order(1)
    void testGetSpecification() {

        // adding car so that a specification is in the db
        carService.saveCar(car);

        Specification addedSpecification = specificationService.getSpecification(specification.getNumSeats(), 
                                                                                 specification.getNumDoors(),
                                                                                 specification.getHorsePower(), 
                                                                                 specification.getSpeedMax(), 
                                                                                 specification.getWeightUnladen(),
                                                                                 specification.getWeightMax());

        assertEquals(specification, addedSpecification);
    }


    @Test
    @Order(2)
    void testGetAllByNumSeats() {

        assertFalse(specificationService.getAllByNumSeats(specification.getNumSeats())
                                        .isEmpty());
    }


    @Test 
    void testGetAllByNumSeatsGreaterThanEqual() {

        // spcification with correct number of seats expected
        assertFalse(specificationService.getAllByNumSeatsGreaterThanEqual(specification.getNumSeats())
                                        .isEmpty());

        // spcification with too many seats expected
        assertTrue(specificationService.getAllByNumSeatsGreaterThanEqual(specification.getNumSeats() + 1)
                                       .isEmpty());
    }


    @Test
    void testGetAllByWeightMax() {

        assertFalse(specificationService.getAllByWeightMax(specification.getWeightMax())
                                        .isEmpty());
    }


    @Test
    void testGetAllByWeightUnladenLessThanEqual() {

        // specification with correct weight unladen expected
        assertFalse(specificationService.getAllByWeightUnladenLessThanEqual(specification.getWeightUnladen())
                                        .isEmpty()); 

        // specification with not enough weight unladen
        assertTrue( specificationService.getAllByWeightUnladenLessThanEqual(specification.getWeightUnladen() - 1)
                                        .isEmpty());
    }


    @Test
    void testKmhToMph() {

        // converting 100 km/h to mph
        assertEquals(62.13711922, specificationService.kmhToMph(100));
    }


    @Test
    void testMphToKmh() {

        // converting 100 mph to km/h
        assertEquals(160.9344, specificationService.mphToKmh(100));
    }


    @Test
    void testKgToPounds() {

        // converting 100 kg to pounds
        assertEquals(220.46226218500001, specificationService.kgToPounds(100));
    }


    @Test
    void testPountdsToKg() {

        // converting 100 kg to pounds
        assertEquals(45.359237, specificationService.poundsToKg(100));
    }
}