package com.megacity.server.service;

import com.megacity.server.model.Vehicle;
import com.megacity.server.repository.VehicleRepository;
import com.megacity.server.exception.VehicleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import java.util.List;

@Service
@Validated // Enables validation for the service methods
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject the PasswordEncoder

    // Get all vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Get vehicle by ID
    public Vehicle getVehicleById(String vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with ID: " + vehicleId));
    }

    // Get vehicle by vehicle number
    public Vehicle getVehicleByVehicleNo(String vehicleNo) {
        return vehicleRepository.findByVehicleNo(vehicleNo)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with Vehicle No: " + vehicleNo));
    }

    // Get vehicle by username
    public Vehicle getVehicleByUserName(String username) {
        return vehicleRepository.findByUserName(username)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with username: " + username));
    }

    // Get vehicles by model
    public List<Vehicle> getVehiclesByModel(String model) {
        return vehicleRepository.findByModel(model);
    }

    // Get vehicles by category
    public List<Vehicle> getVehiclesByCategory(String category) {
        return vehicleRepository.findByCategory(category);
    }

    // Get vehicles by fuel type
    public List<Vehicle> getVehiclesByFuelType(String fuelType) {
        return vehicleRepository.findByFuelType(fuelType);
    }

    // Get vehicles by transmission type
    public List<Vehicle> getVehiclesByTransmission(String transmission) {
        return vehicleRepository.findByTransmission(transmission);
    }

    // Get vehicles by color
    public List<Vehicle> getVehiclesByColor(String color) {
        return vehicleRepository.findByColor(color);
    }

    // Get vehicles by year of manufacture
    public List<Vehicle> getVehiclesByYear(int year) {
        return vehicleRepository.findByYear(year);
    }

    // Get vehicles with insurance expiring before a specific date
    public List<Vehicle> getVehiclesWithInsuranceExpiringBefore(String date) {
        return vehicleRepository.findByInsuranceExpireDateBefore(date);
    }

    // Get vehicles with license expiring before a specific date
    public List<Vehicle> getVehiclesWithLicenseExpiringBefore(String date) {
        return vehicleRepository.findByLicenseExpireDateBefore(date);
    }

    // Create a new vehicle
    @Transactional
    public Vehicle createVehicle(@Valid Vehicle vehicle) {
        // Check if the vehicle number already exists
        if (vehicleRepository.existsByVehicleNo(vehicle.getVehicleNo())) {
            throw new RuntimeException("Vehicle with number " + vehicle.getVehicleNo() + " already exists");
        }

        // Encrypt the password before saving
        String encryptedPassword = passwordEncoder.encode(vehicle.getPassword());
        vehicle.setPassword(encryptedPassword);

        return vehicleRepository.save(vehicle);
    }

    // Update an existing vehicle
    @Transactional
    public Vehicle updateVehicle(String vehicleId, @Valid Vehicle vehicleDetails) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with ID: " + vehicleId));

        // Update vehicle fields
        vehicle.setVehicleImage(vehicleDetails.getVehicleImage());
        vehicle.setManufactureYear(vehicleDetails.getManufactureYear());
        vehicle.setVehicleNo(vehicleDetails.getVehicleNo());
        vehicle.setModel(vehicleDetails.getModel());
        vehicle.setCategory(vehicleDetails.getCategory());
        vehicle.setYear(vehicleDetails.getYear());
        vehicle.setColor(vehicleDetails.getColor());
        vehicle.setFuelType(vehicleDetails.getFuelType());
        vehicle.setTransmission(vehicleDetails.getTransmission());
        vehicle.setLicenseExpireDate(vehicleDetails.getLicenseExpireDate());
        vehicle.setLicenseImage(vehicleDetails.getLicenseImage());
        vehicle.setInsuranceExpireDate(vehicleDetails.getInsuranceExpireDate());
        vehicle.setInsuranceImage(vehicleDetails.getInsuranceImage());

        // Encrypt the password if it is being updated
        if (vehicleDetails.getPassword() != null && !vehicleDetails.getPassword().isEmpty()) {
            String encryptedPassword = passwordEncoder.encode(vehicleDetails.getPassword());
            vehicle.setPassword(encryptedPassword);
        }

        return vehicleRepository.save(vehicle);
    }

    // Delete a vehicle
    @Transactional
    public void deleteVehicle(String vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with ID: " + vehicleId));
        vehicleRepository.delete(vehicle);
    }

    // Check if a vehicle with a specific vehicle number exists
    public boolean vehicleExistsByVehicleNo(String vehicleNo) {
        return vehicleRepository.existsByVehicleNo(vehicleNo);
    }

    // Check if a vehicle with a specific model exists
    public boolean vehicleExistsByModel(String model) {
        return vehicleRepository.existsByModel(model);
    }
}