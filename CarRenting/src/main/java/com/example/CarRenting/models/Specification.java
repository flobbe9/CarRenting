package com.example.CarRenting.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Class describing numbers and data of a car. 
 * <p>
 * All units are assumed to be german. Contains converting methods for speed(km/h to mp/h) and
 * weight(kg to pounds).
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Specification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(nullable = false)
    private Integer numSeats;

    @Column(nullable = false)
    private Integer numDoors;

    @Column(nullable = false)
    private Integer hp;

    @Column(nullable = false)
    private Integer speedMax;

    /** Weight of a car without any charge, fuel etc. */
    @Column(nullable = false)
    private Double weightUnladen;

    /** Allowed total weight of an unladen car plus charge, fuel etc. */
    @Column(nullable = false)
    private Double weightMax;

    @OneToOne
    @JoinColumn(name = "car_id")
    @JsonBackReference
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


    @Override
    public String toString() {
        return "Num seats: " + this.numSeats + "\n" + 
               "Num doors: " + this.numDoors + "\n" +
               "Hp: " + this.hp + "\n" +
               "Speed max: " + this.speedMax + "\n" +
               "Weight unladen: " + this.weightUnladen + "\n" +
               "Weight max: " + this.weightMax + "\n" +
               "Car: " + this.car;
    }
}