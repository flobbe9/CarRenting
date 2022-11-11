package com.example.CarRenting.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CarRenting.models.Specification;


@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Long> {
    
    Optional<Specification> findByNumSeatsAndNumDoorsAndHpAndSpeedMaxAndWeightUnladenAndWeightMax(Integer numSeats,
                                                                                                  Integer numDoors,
                                                                                                  Integer hp, 
                                                                                                  Integer speedMax,
                                                                                                  double weightUnladen,
                                                                                                  double weightMax);

    List<Specification> findAllByNumSeats(Integer numSeats);

    List<Specification> findAllByNumSeatsGreaterThanEqual(Integer numSeats);

    List<Specification> findAllByNumDoors(Integer numDoors);

    List<Specification> findAllByHp(Integer hp);

    List<Specification> findAllByHpGreaterThanEqual(Integer hp);

    List<Specification> findAllBySpeedMax(Integer speedMax);

    List<Specification> findAllBySpeedMaxGreaterThanEqual(Integer speedMax);

    List<Specification> findAllByWeightUnladen(Double weightUnladen);

    List<Specification> findAllByWeightUnladenLessThanEqual(Double weightUnladen);

    List<Specification> findAllByWeightMax(Double weightMax);

    List<Specification> findAllByWeightMaxGreaterThanEqual(Double weightMax);
}