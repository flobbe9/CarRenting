package com.example.CarRenting.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRenting.models.Specification;
import com.example.CarRenting.repositories.SpecificationRepository;


@Service
public class SpecificationService {
    
    @Autowired
    private SpecificationRepository specificationRepository;

    /** 1 km/h in mph */
    public static final double ONE_KMH_IN_MPH = 0.6213711922;

    /** 1 mph in km/h */
    public static final double ONE_MPH_IN_KMH = 1.609344;

    /** 1 kg in pounds */
    public static final double ONE_KG_IN_POUNDS = 2.20462262185;
    
    /** 1 pound in kg */
    public static final double ONE_POUND_IN_KG = 0.45359237;


    public Specification getSpecification(int numSeats,
                                          int numDoors, 
                                          int hp,
                                          int speedMax,
                                          double weightUnladen,
                                          double weightMax) {

        // may contain douplicates         
        List<Specification> specifications = specificationRepository
                                                .findByNumSeatsAndNumDoorsAndHpAndSpeedMaxAndWeightUnladenAndWeightMax(numSeats, 
                                                                                                                       numDoors, 
                                                                                                                       hp, 
                                                                                                                       speedMax, 
                                                                                                                       weightUnladen, 
                                                                                                                       weightMax);
        
        if (specifications.isEmpty())
            throw new IllegalStateException("Could not find a specification with these attributes.");
        
        return specifications.get(0);
    }
    

    public Specification getById(long id) {

        return specificationRepository.findById(id).orElseThrow(() -> 
            new IllegalStateException("Could not find a specification with id " + id + "."));
    }
                                                                                    

    public List<Specification> getAllByNumSeats(int numSeats) {

        return specificationRepository.findAllByNumSeats(numSeats);
    }


    public List<Specification> getAllByNumSeatsGreaterThanEqual(int numSeats) {
        
        return specificationRepository.findAllByNumSeatsGreaterThanEqual(numSeats);
    }

    
    public List<Specification> getAllByNumDoors(int numDoors) {

        return specificationRepository.findAllByNumDoors(numDoors);
    }


    public List<Specification> getAllByHp(int hp) {

        return specificationRepository.findAllByHp(hp);
    }


    public List<Specification> getAllByHpGreaterThanEqual(int hp) {

        return specificationRepository.findAllByHpGreaterThanEqual(hp);
    }


    public List<Specification> getAllBySpeedMax(int speedMax) {

        return specificationRepository.findAllBySpeedMax(speedMax);
    }


    public List<Specification> getAllBySpeedMaxGreaterThanEqual(int speedMax) {

        return specificationRepository.findAllBySpeedMaxGreaterThanEqual(speedMax);
    }


    public List<Specification> getAllByWeightUnladen(double weightUnladen) {

        return specificationRepository.findAllByWeightUnladen(weightUnladen);
    }


    public List<Specification> getAllByWeightUnladenLessThanEqual(double weightUnladen) {

        return specificationRepository.findAllByWeightUnladenLessThanEqual(weightUnladen);
    }


    public List<Specification> getAllByWeightMax(double weightMax) {

        return specificationRepository.findAllByWeightMax(weightMax);
    }


    public List<Specification> getAllByWeightMaxGreaterThanEqual(double weightMax) {

        return specificationRepository.findAllByWeightMaxGreaterThanEqual(weightMax);
    }


    /**
     * Checks attributes of a Specification. 
     * 
     * @param specification to check.
     * @return true if all checks were successful.
     * @throws IllegalStateException if one attribute is invalid.
     */
    public boolean isValid(Specification specification) {
        
        // weightMax has to be greater than weightUnladen
        if (specification.getWeightMax() <= specification.getWeightUnladen()) 
            throw new IllegalStateException("Maximum weight has to be greater than weight unladen.");
        
        return true;
    }


    /**
     * Converts km/h to mph.
     * <p>
     * 1 km/h = 0.6213711922 mph
     * <p>
     * 15 km/h = 15 * 0.6213711922 mph = 9.3205678836 mph
     * 
     * @param speed in km/h.
     * @return speed in mph.
     */
    public double kmhToMph(double speed) {

        return speed * ONE_KMH_IN_MPH;
    }


    /**
     * Converts mph to km/h.
     * <p>
     * 1 mph = 1.609344 km/h
     * <p>
     * 15 mph = 15 * 1.609344 km/h = 24.14016 mph
     * 
     * @param speed in mph.
     * @return speed in km/h.
     */
    public double mphToKmh(double speed) {

        return speed * ONE_MPH_IN_KMH;
    }


    /**
     * Converts kg to pounds.
     * <p>
     * 1 kg = 2.20462262185 lb
     * <p>
     * 15 kg = 15 * 2.20462262185 lb = 33.0693393278 kg
     * 
     * @param weight in kg.
     * @return weight in pounds.
     */
    public double kgToPounds(double weight) {
        
        return weight * ONE_KG_IN_POUNDS;
    }

    /**
     * Converts pounds to kg.
     * <p>
     * 1 pound = 0.45359237 kg
     * <p>
     * 15 pounds = 15 * 0.45359237 kg = 6.80388555 kg
     * 
     * @param weight in kg.
     * @return weight in pounds.
     */
    public double poundsToKg(double weight) {
        
        return weight * ONE_POUND_IN_KG;
    }
}