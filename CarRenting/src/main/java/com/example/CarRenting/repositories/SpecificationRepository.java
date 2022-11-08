package com.example.CarRenting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CarRenting.models.Specification;


@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Long> {
    
}
