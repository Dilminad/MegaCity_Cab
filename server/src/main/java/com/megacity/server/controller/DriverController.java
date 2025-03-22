package com.megacity.server.controller;

import com.megacity.server.exception.DriverNotFoundException;
import com.megacity.server.model.Driver;
import com.megacity.server.model.Vehicle;
import com.megacity.server.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Validated
public class DriverController {

    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);

    @Autowired
    private DriverService driverService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Get all drivers with pagination
    @GetMapping("/admin/allDrivers")
    public ResponseEntity<List<Driver>> getAllDrivers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("Fetching all drivers - page: {}, size: {}", page, size);
        List<Driver> drivers = driverService.getAllDrivers(page, size);
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    // Get driver by ID
    @GetMapping("/{driverId}")
    public ResponseEntity<Driver> getDriverById(
            @PathVariable @NotBlank(message = "Driver ID cannot be blank") String driverId) {
        logger.info("Fetching driver with ID: {}", driverId);
        Driver driver = driverService.getDriverById(driverId);
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    // Create a new driver without a vehicle (status set to PENDING)
    @PostMapping("/auth/driverregister")
    public ResponseEntity<Driver> createDriver(@Valid @RequestBody Driver driver) {
        logger.info("Creating a new driver with username: {}", driver.getUserName());
        driver.setPassword(passwordEncoder.encode(driver.getPassword()));
        Driver createdDriver = driverService.createDriver(driver);
        return new ResponseEntity<>(createdDriver, HttpStatus.CREATED);
    }

    // Create a new driver with a vehicle (status set to PENDING)
    @PostMapping("/auth/register-with-vehicle")
    public ResponseEntity<Driver> createDriverWithVehicle(@Valid @RequestBody DriverWithVehicleRequest request) {
        logger.info("Creating a new driver with vehicle");
        request.getDriver().setPassword(passwordEncoder.encode(request.getDriver().getPassword()));
        Driver createdDriver = driverService.createDriverWithVehicle(request.getDriver(), request.getVehicle());
        return new ResponseEntity<>(createdDriver, HttpStatus.CREATED);
    }

    // Admin approves a driver registration
    @PutMapping("/admin/approve-driver/{driverId}")
    public ResponseEntity<Driver> approveDriver(@PathVariable String driverId) {
        logger.info("Approving driver with ID: {}", driverId);
        Driver approvedDriver = driverService.approveDriver(driverId);
        return new ResponseEntity<>(approvedDriver, HttpStatus.OK);
    }

    // Admin rejects a driver registration
    @PutMapping("/admin/reject-driver/{driverId}")
    public ResponseEntity<Driver> rejectDriver(@PathVariable String driverId) {
        logger.info("Rejecting driver with ID: {}", driverId);
        Driver rejectedDriver = driverService.rejectDriver(driverId);
        return new ResponseEntity<>(rejectedDriver, HttpStatus.OK);
    }

    // Get all pending drivers
    @GetMapping("/admin/pending-drivers")
    public ResponseEntity<List<Driver>> getPendingDrivers() {
        logger.info("Fetching all pending drivers");
        List<Driver> pendingDrivers = driverService.getPendingDrivers();
        return new ResponseEntity<>(pendingDrivers, HttpStatus.OK);
    }

    // Update an existing driver
    @PutMapping("/{driverId}")
    public ResponseEntity<Driver> updateDriver(
            @PathVariable String driverId,
            @Valid @RequestBody Driver driverDetails) {
        logger.info("Updating driver with ID: {}", driverId);
        Driver updatedDriver = driverService.updateDriver(driverId, driverDetails);
        return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
    }

    // Toggle driver's active status (active or blocked)
    @PutMapping("/{driverId}/toggle-status")
    public ResponseEntity<Driver> toggleDriverStatus(@PathVariable String driverId) {
        logger.info("Toggling status for driver with ID: {}", driverId);
        Driver updatedDriver = driverService.toggleDriverStatus(driverId);
        return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
    }

    // Delete a driver
    @DeleteMapping("/admin/deletedriver/{driverId}")
    public ResponseEntity<String> deleteDriver(@PathVariable String driverId) {
        logger.info("Deleting driver with ID: {}", driverId);
        driverService.deleteDriver(driverId);
        return new ResponseEntity<>("Driver deleted successfully", HttpStatus.OK);
    }

    // Inner class to represent the request body for creating a driver with a vehicle
    @Data
    static class DriverWithVehicleRequest {
        private Driver driver;
        private Vehicle vehicle;
    }

    // Exception handling
    @ExceptionHandler(DriverNotFoundException.class)
    public ResponseEntity<String> handleDriverNotFoundException(DriverNotFoundException ex) {
        logger.error("Driver not found: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        logger.error("Runtime error: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}