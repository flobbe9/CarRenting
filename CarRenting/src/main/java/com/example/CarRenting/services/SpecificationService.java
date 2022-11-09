package com.example.CarRenting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRenting.models.Specification;
import com.example.CarRenting.repositories.SpecificationRepository;


@Service
public class SpecificationService {
    
    @Autowired
    private SpecificationRepository specificationRepository;


    public Specification getSpecification(int numSeats,
                                          int numDoors, 
                                          int hp,
                                          int speedMax,
                                          double weightUnladen,
                                          double weightMax) {

        return specificationRepository
                .findByNumSeatsAndNumDoorsAndHpAndSpeedMaxAndWeightUnladenAndWeightMax(numSeats, 
                                                                                       numDoors, 
                                                                                       hp, 
                                                                                       speedMax, 
                                                                                       weightUnladen, 
                                                                                       weightMax)
                .orElseThrow(() -> 
                    new IllegalStateException("Could not find a specification with these attributes."));
    }


    public Specification getById(long id) {
        return specificationRepository.findById(id).orElseThrow(() -> 
            new IllegalStateException("Could not find a specification with id " + id + "."));
    }
}