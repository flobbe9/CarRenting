package com.example.CarRenting.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CarRenting.models.Specification;


@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Long> {
    
    Optional<Specification> findByNumSeatsAndNumDoorsAndHpAndSpeedMaxAndWeightUnladenAndWeightMax(int numSeats,
                                                                                                  int numDoors,
                                                                                                  int hp, 
                                                                                                  int speedMax,
                                                                                                  double weightUnladen,
                                                                                                  double weightMax);
}