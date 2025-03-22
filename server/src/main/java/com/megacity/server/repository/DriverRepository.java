package com.megacity.server.repository;

import com.megacity.server.model.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends MongoRepository<Driver, String> {
    Optional<Driver> findByDriverId(String driverId);
    Optional<Driver> findByEmail(String email);
    Optional<Driver> findByNic(String nic);
    Optional<Driver> findByUserName(String userName);
    
    // Add this method to find drivers by registration status
    List<Driver> findByRegistrationStatus(String registrationStatus);
}
