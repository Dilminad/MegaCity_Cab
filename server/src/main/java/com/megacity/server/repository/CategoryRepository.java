package com.megacity.server.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.megacity.server.model.Category;

@Repository
public interface CategoryRepository extends MongoRepository <Category, String>{
    Optional<Category> findByName(String name);
}
