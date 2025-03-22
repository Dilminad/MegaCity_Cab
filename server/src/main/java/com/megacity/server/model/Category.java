package com.megacity.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "category") 
public class Category {

    @Id
    
    private String categoryId;   
    private String name;
    private String model;          
    private String description;
    private double price;   
    private boolean isActive;     

}
