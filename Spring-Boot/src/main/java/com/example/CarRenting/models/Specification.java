package com.example.CarRenting.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Class describing numbers and data of a car. 
 * <p>
 * All units are assumed to be german. Contains converting methods for speed(km/h to mp/h) and
 * weight(kg to pounds).
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
public class Specification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @NotNull
    @Min(1)
    private Integer numSeats;

    @NotNull
    @Min(1)
    private Integer numDoors;

    @NotNull
    @Min(0)
    private Integer hp;

    @NotNull
    @Min(0)
    private Integer speedMax;

    /** Weight of a car without any charge, fuel etc. */
    @NotNull
    @DecimalMin("0.0")
    private Double weightUnladen;

    /** Allowed total weight of an unladen car plus charge, fuel etc. */
    @NotNull
    @DecimalMin("0.0")
    private Double weightMax;

    @OneToOne
    @JoinColumn(name = "car_id")
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Car car;


    public Specification(Integer numSeats, 
                         Integer numDoors, 
                         Integer hp, 
                         Integer speedMax, 
                         Double weightUnladen,
                         Double weightMax) {
                            
        this.numSeats = numSeats;
        this.numDoors = numDoors;
        this.hp = hp;
        this.speedMax = speedMax;
        this.weightUnladen = weightUnladen;
        this.weightMax = weightMax;
    }
}