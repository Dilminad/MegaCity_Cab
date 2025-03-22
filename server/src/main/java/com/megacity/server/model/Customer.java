package com.megacity.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer")
public class Customer {

    @Id
    private String customerId;

    private String firstName;
    private String lastName;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phone;
    
    private String address;

    @NotBlank(message = "Username is required")
    private String userName;
    
    @NotBlank(message = "Password is required")
    private String password;
    private boolean isActive;
}