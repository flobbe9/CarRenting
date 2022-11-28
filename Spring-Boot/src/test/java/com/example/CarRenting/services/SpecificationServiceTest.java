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

        // adding mock data to database if it was deleted
        carService.saveCar(car);
    }


    @Test
    @Order(1)
    void getSpecification_shouldBeEqual_ifExists() {

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
    void getAllByNumSeats_listShouldNotBeEmpty() {

        assertFalse(specificationService.getAllByNumSeats(specification.getNumSeats())
                                        .isEmpty());
    }


    @Test 
    void getAllByNumSeatsGreaterThanEqual_listShouldNotBeEmpty_ifNumSeatsIsSmallEnough() {

        assertFalse(specificationService.getAllByNumSeatsGreaterThanEqual(specification.getNumSeats())
                                        .isEmpty());
    }


    @Test 
    void getAllByNumSeatsGreaterThanEqual_listShouldBeEmpty_ifNumSeatsIsTooBig() {

        assertTrue(specificationService.getAllByNumSeatsGreaterThanEqual(specification.getNumSeats() + 1)
                                       .isEmpty());
    }


    @Test
    void getAllByWeightMax_listShouldNotBeEmpty() {

        assertFalse(specificationService.getAllByWeightMax(specification.getWeightMax())
                                        .isEmpty());
    }


    @Test
    void getAllByWeightUnladenLessThanEqual_listShouldNotBeEmtpy_ifWeightUnladenIsBigEnough() {

        assertFalse(specificationService.getAllByWeightUnladenLessThanEqual(specification.getWeightUnladen())
                                        .isEmpty()); 
    }


    @Test
    void getAllByWeightUnladenLessThanEqual_listShouldBeEmpty_ifWeightUnladenIsTooSmall() {

        assertTrue( specificationService.getAllByWeightUnladenLessThanEqual(specification.getWeightUnladen() - 1)
                                        .isEmpty());
    }


    @Test
    void calculate_kmhToMph() {

        // converting 100 km/h to mph
        assertEquals(62.13711922, specificationService.kmhToMph(100));
    }


    @Test
    void calculate_mphToKmh() {

        // converting 100 mph to km/h
        assertEquals(160.9344, specificationService.mphToKmh(100));
    }


    @Test
    void calculate_kgToPounds() {

        // converting 100 kg to pounds
        assertEquals(220.46226218500001, specificationService.kgToPounds(100));
    }


    @Test
    void calculate_pountdsToKg() {

        // converting 100 kg to pounds
        assertEquals(45.359237, specificationService.poundsToKg(100));
    }
}