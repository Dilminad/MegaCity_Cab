package com.megacity.server.controller;

import com.megacity.server.model.Vehicle;
import com.megacity.server.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController

@CrossOrigin(origins = "*")
@Validated
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // Get all vehicles
    @GetMapping("/admin/allVehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    // Get vehicle by ID
    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable String vehicleId) {
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }

    // Get vehicle by vehicle number
    @GetMapping("/by-vehicleNo/{vehicleNo}")
    public ResponseEntity<Vehicle> getVehicleByVehicleNo(@PathVariable String vehicleNo) {
        Vehicle vehicle = vehicleService.getVehicleByVehicleNo(vehicleNo);
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }

    // Get vehicle by username
    @GetMapping("/vehicle/{username}")
    public ResponseEntity<Vehicle> getVehicleByUsername(@PathVariable String username) {
        Vehicle vehicle = vehicleService.getVehicleByUserName(username);
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }

    // Get vehicles by model
    @GetMapping("/by-model/{model}")
    public ResponseEntity<List<Vehicle>> getVehiclesByModel(@PathVariable String model) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByModel(model);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    // Get vehicles by category
    @GetMapping("/by-category/{category}")
    public ResponseEntity<List<Vehicle>> getVehiclesByCategory(@PathVariable String category) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByCategory(category);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    // Get vehicles by fuel type
    @GetMapping("/by-fuelType/{fuelType}")
    public ResponseEntity<List<Vehicle>> getVehiclesByFuelType(@PathVariable String fuelType) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByFuelType(fuelType);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    // Get vehicles by transmission type
    @GetMapping("/by-transmission/{transmission}")
    public ResponseEntity<List<Vehicle>> getVehiclesByTransmission(@PathVariable String transmission) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByTransmission(transmission);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    // Get vehicles by color
    @GetMapping("/by-color/{color}")
    public ResponseEntity<List<Vehicle>> getVehiclesByColor(@PathVariable String color) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByColor(color);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    // Get vehicles by year of manufacture
    @GetMapping("/by-year/{year}")
    public ResponseEntity<List<Vehicle>> getVehiclesByYear(@PathVariable int year) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByYear(year);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    // Get vehicles with insurance expiring before a specific date
    @GetMapping("/insurance-expiring-before/{date}")
    public ResponseEntity<List<Vehicle>> getVehiclesWithInsuranceExpiringBefore(@PathVariable String date) {
        List<Vehicle> vehicles = vehicleService.getVehiclesWithInsuranceExpiringBefore(date);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    // Get vehicles with license expiring before a specific date
    @GetMapping("/license-expiring-before/{date}")
    public ResponseEntity<List<Vehicle>> getVehiclesWithLicenseExpiringBefore(@PathVariable String date) {
        List<Vehicle> vehicles = vehicleService.getVehiclesWithLicenseExpiringBefore(date);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    // Create a new vehicle
    @PostMapping("/auth/create")
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle) {
        Vehicle createdVehicle = vehicleService.createVehicle(vehicle);
        return new ResponseEntity<>(createdVehicle, HttpStatus.CREATED);
    }

    // Update an existing vehicle
    @PutMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable String vehicleId, @Valid @RequestBody Vehicle vehicleDetails) {
        Vehicle updatedVehicle = vehicleService.updateVehicle(vehicleId, vehicleDetails);
        return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
    }

    // Delete a vehicle
    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<String> deleteVehicle(@PathVariable String vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return new ResponseEntity<>("Vehicle deleted successfully", HttpStatus.OK);
    }

    // Check if a vehicle with a specific vehicle number exists
    @GetMapping("/exists-by-vehicleNo/{vehicleNo}")
    public ResponseEntity<Boolean> vehicleExistsByVehicleNo(@PathVariable String vehicleNo) {
        boolean exists = vehicleService.vehicleExistsByVehicleNo(vehicleNo);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    // Check if a vehicle with a specific model exists
    @GetMapping("/exists-by-model/{model}")
    public ResponseEntity<Boolean> vehicleExistsByModel(@PathVariable String model) {
        boolean exists = vehicleService.vehicleExistsByModel(model);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }
}