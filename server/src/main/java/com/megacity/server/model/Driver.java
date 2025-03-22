package com.megacity.server.model;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "driver")
public class Driver {

    @Id
    private String driverId;

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "NIC cannot be blank")
    private String nic;

    @NotBlank(message = "Phone number cannot be blank")
    private String phone;

    private String emergencyPhone;

    private String address;

    @NotBlank(message = "License number cannot be blank")
    private String licenseNumber;

    @NotNull(message = "License expiry date cannot be null")
    private LocalDate licenseExpiry;
    private String licenseImage;
    private int experienceYears;
    private String profilePicture;
    private String status;  // "ACTIVE", "BLOCKED", etc.
    private boolean isActive;  // true if the driver is active, false if blocked
    private String availabilityStatus;


    @NotBlank(message = "Username cannot be blank")
    private String userName;

    @NotBlank(message = "Password cannot be blank")
    private String password;
    private String registrationStatus;

    @DBRef
    private Vehicle vehicle;
}