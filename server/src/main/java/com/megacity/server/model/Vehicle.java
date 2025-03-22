package com.megacity.server.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "vehicle")
public class Vehicle {

    @Id
    private String vehicleId;

    private String vehicleImage;

    @NotBlank(message = "Username cannot be blank")
    private String userName;

    @NotBlank(message = "Password cannot be blank")

    private String password;

    private String manufactureYear;

    @NotBlank(message = "Vehicle number cannot be blank")
    private String vehicleNo;

    @NotBlank(message = "Model cannot be blank")
    private String model;

    private String category;
    private int year;

    private String color;

    @NotBlank(message = "Fuel type cannot be blank")
    private String fuelType;

    @NotBlank(message = "Transmission type cannot be blank")
    private String transmission;

    private String licenseExpireDate;
    private String licenseImage; 
    private String insuranceExpireDate;
    private String insuranceImage;
}