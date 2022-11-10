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
                                                                                    

    public List<Specification> getAllByNumSeats(int numSeats) {

        return specificationRepository.findAllByNumSeats(numSeats);
    }

    
    public List<Specification> getAllByNumDoors(int numDoors) {

        return specificationRepository.findAllByNumDoors(numDoors);
    }


    public List<Specification> getAllByHp(int hp) {

        return specificationRepository.findAllByHp(hp);
    }


    public List<Specification> getAllBySpeedMax(int speedMax) {

        return specificationRepository.findAllBySpeedMax(speedMax);
    }


    public List<Specification> getAllByWeightUnladen(double weightUnladen) {

        return specificationRepository.findAllByWeightUnladen(weightUnladen);
    }


    public List<Specification> getAllByWeightMax(double weightMax) {

        return specificationRepository.findAllByWeightMax(weightMax);
    }


//////// helper methods:


    private boolean isValid(Specification specification) {
        
        // TODO: implement
        return true;
    }
}