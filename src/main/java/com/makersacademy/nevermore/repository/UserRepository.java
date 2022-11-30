package com.makersacademy.nevermore.repository;

import com.makersacademy.nevermore.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    

    Optional<User> findByUsername(String username);
    
}
