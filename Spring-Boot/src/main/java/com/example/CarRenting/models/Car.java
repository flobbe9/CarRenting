package com.example.CarRenting.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.CarRenting.enums.Color;
import com.example.CarRenting.enums.FuelType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Class of car Objects that can be rented.
 * 
 * @since 1.0
 * @author Florin Schikarski
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @NotEmpty
    private String brand;

    @NotEmpty
    private String model;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Color color;

    @Enumerated(EnumType.STRING)
    @NotNull
    private FuelType fuelType;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "specifiaction_id", nullable = false)
    @JsonManagedReference
    private Specification specification;

    @NotNull
    @EqualsAndHashCode.Exclude
    private Boolean isAvailable = true;


    public Car(String brand, 
               String model, 
               Color color, 
               FuelType fuelType, 
               Specification specification) {

        this.brand = brand;
        this.model = model;
        this.color = color;
        this.fuelType = fuelType;
        this.specification = specification;
    }
}