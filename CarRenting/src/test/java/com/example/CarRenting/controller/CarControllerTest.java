package com.example.CarRenting.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.CarRenting.enums.Color;
import com.example.CarRenting.enums.FuelType;
import com.example.CarRenting.models.Car;
import com.example.CarRenting.models.Specification;
import com.example.CarRenting.services.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
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
    void testAddNew() throws JsonProcessingException, Exception {

        when(carService.addNew(car))
            .thenReturn(car);

        mockMvc.perform(post("/car/addNew")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectToJson(car)))      
               .andExpect(status().isOk())
               .andReturn();              
    }


    @Test
    void testGetAllByBrandAndModel() throws Exception {

        when(carService.getCar(car.getBrand(), 
                               car.getModel(), 
                               car.getColor(), 
                               car.getFuelType(), 
                               car.getSpecification()))
            .thenReturn(car);
        
        mockMvc.perform(get("/getByBrandAndModel?brand=VW&model=Golf&color=RED&fuelType=BENZINE"))
               .andExpect(status().isOk())
               .andReturn();
    }


    @Test
    void testGetAllByBrandAndModel2() {

    }


    @Test
    void testGetCar() {

    }


////////


    /**
     * Formats any object to a json String.
     * 
     * @param obj to convert.
     * @return json representation.
     * @throws JsonProcessingException
     */
    private String objectToJson(Object obj) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper.writeValueAsString(obj);
    }
}