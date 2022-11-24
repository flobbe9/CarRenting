package com.example.CarRenting.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CarRenting.models.Specification;


/**
 * Interface that implements JpaRepository. Contains query methods for the Specification entity.
 * 
 * @see Specification
 * @since 1.0
 * @author Florin Schikarski
 */
@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Long> {
    
    List<Specification> findByNumSeatsAndNumDoorsAndHorsePowerAndSpeedMaxAndWeightUnladenAndWeightMax(Integer numSeats,
                                                                                              Integer numDoors,
                                                                                              Integer horsePower, 
                                                                                              Integer speedMax,
                                                                                              Double weightUnladen,
                                                                                              Double weightMax);

    List<Specification> findAllByNumSeats(Integer numSeats);

    List<Specification> findAllByNumSeatsGreaterThanEqual(Integer numSeats);

    List<Specification> findAllByNumDoors(Integer numDoors);

    List<Specification> findAllByHorsePower(Integer horsePower);

    List<Specification> findAllByHorsePowerGreaterThanEqual(Integer horsePower);

    List<Specification> findAllBySpeedMax(Integer speedMax);

    List<Specification> findAllBySpeedMaxGreaterThanEqual(Integer speedMax);

    List<Specification> findAllByWeightUnladen(Double weightUnladen);

    List<Specification> findAllByWeightUnladenLessThanEqual(Double weightUnladen);

    List<Specification> findAllByWeightMax(Double weightMax);

    List<Specification> findAllByWeightMaxGreaterThanEqual(Double weightMax);
}