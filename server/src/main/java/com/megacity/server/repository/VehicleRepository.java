package com.megacity.server.repository;

import com.megacity.server.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {

    // Find a vehicle by its number (unique identifier)
    Optional<Vehicle> findByVehicleNo(String vehicleNo);

    // Find vehicles by model (returns a list since multiple vehicles can have the same model)
    List<Vehicle> findByModel(String model);

    // Find vehicles by category (e.g., Sedan, SUV, etc.)
    List<Vehicle> findByCategory(String category);

    // Find vehicles by fuel type (e.g., Petrol, Diesel, Electric)
    List<Vehicle> findByFuelType(String fuelType);

    // Find vehicles by transmission type (e.g., Automatic, Manual)
    List<Vehicle> findByTransmission(String transmission);

    // Find vehicles by color
    List<Vehicle> findByColor(String color);

    // Find vehicles by year of manufacture
    List<Vehicle> findByYear(int year);

    // Find vehicles by insurance expiration date (before a specific date)
    List<Vehicle> findByInsuranceExpireDateBefore(String date);

    // Find vehicles by license expiration date (before a specific date)
    List<Vehicle> findByLicenseExpireDateBefore(String date);

    // Check if a vehicle with a specific vehicle number already exists
    boolean existsByVehicleNo(String vehicleNo);

    // Check if a vehicle with a specific model already exists
    boolean existsByModel(String model);

    // Find a vehicle by username (if vehicles are associated with users)
    Optional<Vehicle> findByUserName(String username);

}