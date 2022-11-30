package com.makersacademy.nevermore.repository;

import com.makersacademy.nevermore.model.Cost;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CostRepository extends CrudRepository<Cost, Long> {

  @Query(
  value = "SELECT content FROM costs WHERE user_id = 2 limit 1", 
  nativeQuery = true)
  Optional<Cost> findLatestCostPerUser();
    
}


