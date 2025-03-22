package com.megacity.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "admin")
public class Admin {

    @Id
    private String adminId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String Nic;
    private String phone;
    private String profilePicture;
    private boolean isActive;  
}
