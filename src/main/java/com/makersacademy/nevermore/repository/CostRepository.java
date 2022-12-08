package com.makersacademy.nevermore.repository;

import com.makersacademy.nevermore.model.Cost;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CostRepository extends CrudRepository<Cost, Long> {

    Optional<Cost> findByContent(String Content);
    
}


