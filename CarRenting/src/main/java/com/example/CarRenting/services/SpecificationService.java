package com.example.CarRenting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRenting.models.Specification;
import com.example.CarRenting.repositories.SpecificationRepository;


@Service
public class SpecificationService {
    
    @Autowired
    private SpecificationRepository specificationRepository;

}