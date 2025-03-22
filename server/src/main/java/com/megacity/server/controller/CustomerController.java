package com.megacity.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.megacity.server.model.Customer;
import com.megacity.server.service.CustomerService;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Register a new customer (this route will remain under /auth)
    @PostMapping("/auth/register")
    public ResponseEntity<?> registerCustomer(@RequestBody @Valid Customer customer) {
        Customer newCustomer = customerService.createCustomer(customer);
        
        // Prepare response map
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Customer created successfully");
        response.put("data", newCustomer);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all customers
    @GetMapping("/admin/allCustomers")
    public ResponseEntity<?> getAllCustomers() {
        Iterable<Customer> customers = customerService.getAllCustomers();
        
        // Prepare response map
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Fetched all customers");
        response.put("data", customers);
        
        return ResponseEntity.ok(response);
    }

    // Get customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        
        Map<String, Object> response = new HashMap<>();
        if (customer.isPresent()) {
            response.put("message", "Customer fetched successfully");
            response.put("data", customer.get());
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Customer not found");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Update an existing customer
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable String id, @RequestBody @Valid Customer updatedCustomer) {
        Customer customer = customerService.updateCustomer(id, updatedCustomer);
        
        Map<String, Object> response = new HashMap<>();
        if (customer != null) {
            response.put("message", "Customer updated successfully");
            response.put("data", customer);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Customer not found");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Update customer status (active or blocked)
    @PutMapping("/status/{id}")
    public ResponseEntity<?> updateCustomerStatus(@PathVariable String id, @RequestParam boolean isActive) {
        Customer customer = customerService.updateCustomerStatus(id, isActive);
        
        Map<String, Object> response = new HashMap<>();
        if (customer != null) {
            response.put("message", "Customer status updated successfully");
            response.put("data", customer);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Customer not found");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Delete a customer
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        boolean isDeleted = customerService.deleteCustomer(id);
        
        Map<String, Object> response = new HashMap<>();
        if (isDeleted) {
            response.put("message", "Customer deleted successfully");
            response.put("data", null);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Customer not found");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
