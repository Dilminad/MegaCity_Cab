package com.megacity.server.service;

import com.megacity.server.model.Driver;
import com.megacity.server.model.Vehicle;
import com.megacity.server.repository.DriverRepository;
import com.megacity.server.repository.VehicleRepository;
import com.megacity.server.exception.DriverNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private EmailService emailService;

    // Get all drivers with pagination
    public List<Driver> getAllDrivers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return driverRepository.findAll(pageable).getContent();
    }

    // Get driver by ID
    public Driver getDriverById(String driverId) {
        return driverRepository.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException("Driver not found with ID: " + driverId));
    }

    // Create a new driver (status set to PENDING)
    @Transactional
    public Driver createDriver(@Valid Driver driver) {
        // Check if username, email, or NIC already exists
        if (driverRepository.findByUserName(driver.getUserName()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (driverRepository.findByEmail(driver.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        if (driverRepository.findByNic(driver.getNic()).isPresent()) {
            throw new RuntimeException("NIC already exists");
        }

        // Generate a unique ID if not provided
        if (driver.getDriverId() == null) {
            driver.setDriverId(UUID.randomUUID().toString());
        }

        // Set the initial registration status to "PENDING"
        driver.setRegistrationStatus("PENDING");
        driver.setStatus("PENDING");
        driver.setActive(false);

        // Save the driver
        Driver savedDriver = driverRepository.save(driver);

        // Notify the driver that their registration is in progress
        notifyDriverAboutRegistrationProgress(savedDriver);

        return savedDriver;
    }

    // Create a driver with a vehicle (status set to PENDING)
    @Transactional
    public Driver createDriverWithVehicle(@Valid Driver driver, @Valid Vehicle vehicle) {
        if (vehicle != null) {
            // Check if the vehicle number already exists
            if (vehicleRepository.findByVehicleNo(vehicle.getVehicleNo()).isPresent()) {
                throw new RuntimeException("Vehicle with number " + vehicle.getVehicleNo() + " already exists");
            }

            // Generate a unique ID for the vehicle if not provided
            if (vehicle.getVehicleId() == null) {
                vehicle.setVehicleId(UUID.randomUUID().toString());
            }

            // Save the vehicle first
            Vehicle savedVehicle = vehicleRepository.save(vehicle);
            // Link the saved vehicle to the driver
            driver.setVehicle(savedVehicle);
        }

        // Save the driver and notify about registration progress
        return createDriver(driver);
    }

    // Approve a driver registration
    @Transactional
    public Driver approveDriver(String driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException("Driver not found with ID: " + driverId));

        // Approve the driver
        driver.setRegistrationStatus("APPROVED");
        driver.setStatus("ACTIVE");
        driver.setActive(true);

        // Notify the driver about the approval
        notifyDriverAboutApproval(driver);

        return driverRepository.save(driver);
    }

    // Reject a driver registration
    @Transactional
    public Driver rejectDriver(String driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException("Driver not found with ID: " + driverId));

        // Reject the driver
        driver.setRegistrationStatus("REJECTED");
        driver.setStatus("BLOCKED");
        driver.setActive(false);

        // Notify the driver about the rejection
        notifyDriverAboutRejection(driver);

        return driverRepository.save(driver);
    }

    // Get all pending drivers
    public List<Driver> getPendingDrivers() {
        return driverRepository.findByRegistrationStatus("PENDING");
    }

    // Update an existing driver
    @Transactional
    public Driver updateDriver(String driverId, @Valid Driver driverDetails) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException("Driver not found with ID: " + driverId));

        // Update driver fields
        driver.setFirstName(driverDetails.getFirstName());
        driver.setLastName(driverDetails.getLastName());
        driver.setEmail(driverDetails.getEmail());
        driver.setNic(driverDetails.getNic());
        driver.setPhone(driverDetails.getPhone());
        driver.setEmergencyPhone(driverDetails.getEmergencyPhone());
        driver.setAddress(driverDetails.getAddress());
        driver.setLicenseNumber(driverDetails.getLicenseNumber());
        driver.setLicenseExpiry(driverDetails.getLicenseExpiry());
        driver.setLicenseImage(driverDetails.getLicenseImage()); // Update license image
        driver.setExperienceYears(driverDetails.getExperienceYears());
        driver.setProfilePicture(driverDetails.getProfilePicture());
        driver.setAvailabilityStatus(driverDetails.getAvailabilityStatus());
        driver.setRegistrationStatus(driverDetails.getRegistrationStatus());

        // Update vehicle details if provided
        if (driverDetails.getVehicle() != null) {
            Vehicle vehicle = driverDetails.getVehicle();
            if (vehicle.getVehicleId() != null) {
                // Update existing vehicle
                Vehicle existingVehicle = vehicleRepository.findById(vehicle.getVehicleId())
                        .orElseThrow(() -> new DriverNotFoundException("Vehicle not found with ID: " + vehicle.getVehicleId()));
                existingVehicle.setVehicleImage(vehicle.getVehicleImage());
                existingVehicle.setManufactureYear(vehicle.getManufactureYear());
                existingVehicle.setVehicleNo(vehicle.getVehicleNo());
                existingVehicle.setModel(vehicle.getModel());
                existingVehicle.setCategory(vehicle.getCategory());
                existingVehicle.setYear(vehicle.getYear());
                existingVehicle.setColor(vehicle.getColor());
                existingVehicle.setFuelType(vehicle.getFuelType());
                existingVehicle.setTransmission(vehicle.getTransmission());
                existingVehicle.setLicenseExpireDate(vehicle.getLicenseExpireDate());
                existingVehicle.setLicenseImage(vehicle.getLicenseImage());
                existingVehicle.setInsuranceExpireDate(vehicle.getInsuranceExpireDate());
                existingVehicle.setInsuranceImage(vehicle.getInsuranceImage());
                vehicleRepository.save(existingVehicle);
            } else {
                // Create new vehicle
                Vehicle savedVehicle = vehicleRepository.save(vehicle);
                driver.setVehicle(savedVehicle);
            }
        }

        return driverRepository.save(driver);
    }

    // Delete a driver
    @Transactional
    public void deleteDriver(String driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException("Driver not found with ID: " + driverId));
        driverRepository.delete(driver);
    }

    // Toggle driver's active status
    @Transactional
    public Driver toggleDriverStatus(String driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException("Driver not found with ID: " + driverId));

        // Toggle the isActive status
        boolean newStatus = !driver.isActive();
        driver.setActive(newStatus);

        // Update the status field (e.g., "ACTIVE" or "BLOCKED")
        driver.setStatus(newStatus ? "ACTIVE" : "BLOCKED");

        return driverRepository.save(driver);
    }

    // Notify driver that their registration is in progress
    private void notifyDriverAboutRegistrationProgress(Driver driver) {
        String emailSubject = "Driver Registration in Progress";
        String emailBody = "Dear " + driver.getFirstName() + ",\n\n" +
                "Thank you for registering as a driver with Megacity. Your registration is currently under review.\n\n" +
                "We will notify you once your registration is approved or rejected.\n\n" +
                "Thank you for your patience.\n\n" +
                "Best regards,\nMegacity Team";

        emailService.sendEmail(driver.getEmail(), emailSubject, emailBody);
    }

    // Notify driver about approval
    private void notifyDriverAboutApproval(Driver driver) {
        String emailSubject = "Driver Registration Approved";
        String emailBody = "Dear " + driver.getFirstName() + ",\n\n" +
                "Your driver registration with Megacity has been approved.\n\n" +
                "You can now log in and start using the system.\n\n" +
                "Thank you,\nMegacity Team";

        emailService.sendEmail(driver.getEmail(), emailSubject, emailBody);
    }

    // Notify driver about rejection
    private void notifyDriverAboutRejection(Driver driver) {
        String emailSubject = "Driver Registration Rejected";
        String emailBody = "Dear " + driver.getFirstName() + ",\n\n" +
                "We regret to inform you that your driver registration with Megacity has been rejected.\n\n" +
                "If you have any questions, please contact our support team.\n\n" +
                "Thank you,\nMegacity Team";

        emailService.sendEmail(driver.getEmail(), emailSubject, emailBody);
    }
}