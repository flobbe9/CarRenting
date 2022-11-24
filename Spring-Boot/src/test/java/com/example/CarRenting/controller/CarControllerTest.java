package com.example.CarRenting.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.Arrays;

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
    void addNew_expectHttp200() throws JsonProcessingException, Exception {

        when(carService.saveCar(car))
            .thenReturn(car);

        mockMvc.perform(post("/car/saveCar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(car)))      
               .andExpect(status().isOk())
               .andDo(print())
               .andReturn();              
    }


    @Test
    void getCar_expectHttp200() throws Exception {

        when(carService.getCar(car.getBrand(), 
                               car.getModel(), 
                               car.getColor().name(), 
                               car.getFuelType().name(), 
                               car.getSpecification()))
                       .thenReturn(car);
        
        mockMvc.perform(post("/car/getCar?brand=VW&model=Golf&color=rED&fuelType=BENzINE")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(specification)))
               .andExpect(status().isOk())
               .andDo(print())
               .andReturn();
    }


    @Test
    void getAllByBrandAndModel_expectHttp200() throws Exception {
        
        when(carService.getAllByBrandAndModel(car.getBrand(), car.getModel()))
            .thenReturn(Arrays.asList(car));
        
        mockMvc.perform(get("/car/getAllByBrandAndModel?brand=VW&model=Golf"))
               .andExpect(status().isOk())
               .andDo(print())
               .andReturn();
    }


    @Test
    void existsByModel_expectHttp200() throws Exception {
        
        when(carService.existsByModel(car.getModel()))
            .thenReturn(true);
        
        mockMvc.perform(get("/car/existsByModel?model=Golf"))
               .andExpect(status().isOk())
               .andDo(print())
               .andReturn();
    }


    @Test 
    void delete_expectHttp200() throws Exception {

        mockMvc.perform(delete("/car/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(car)))
               .andExpect(status().isOk())
               .andDo(print());
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