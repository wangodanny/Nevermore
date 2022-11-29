package com.makersacademy.nevermore.repository;

import com.makersacademy.nevermore.model.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthoritiesRepository extends CrudRepository<Authority, Long> {
}
