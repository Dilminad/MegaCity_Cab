package com.megacity.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
import com.megacity.server.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    
    Optional<Customer> findByUserName(String userName);


    boolean existsByUserName(String userName);
}
