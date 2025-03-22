package com.megacity.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.megacity.server.model.Customer;
import com.megacity.server.repository.CustomerRepository;

import jakarta.validation.Valid;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Create new customer
    public Customer createCustomer(@Valid Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setActive(true); // Ensure customer is active by default
        return customerRepository.save(customer);
    }

    // Get all customers
    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    public Optional<Customer> getCustomerById(String customerId) {
        return customerRepository.findById(customerId);
    }

    // Update existing customer
    public Customer updateCustomer(String customerId, Customer updatedCustomer) {
        Optional<Customer> existingCustomer = customerRepository.findById(customerId);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            customer.setFirstName(updatedCustomer.getFirstName());
            customer.setLastName(updatedCustomer.getLastName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setPhone(updatedCustomer.getPhone());
            customer.setAddress(updatedCustomer.getAddress());
            
            // Update password if changed (optional)
            if (updatedCustomer.getPassword() != null && !updatedCustomer.getPassword().isEmpty()) {
                customer.setPassword(passwordEncoder.encode(updatedCustomer.getPassword()));
            }
            
            return customerRepository.save(customer);
        }
        return null; // Customer not found
    }

    // Update customer status (active or blocked)
    public Customer updateCustomerStatus(String customerId, boolean isActive) {
        Optional<Customer> existingCustomer = customerRepository.findById(customerId);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            customer.setActive(isActive); // Set status (active or blocked)
            return customerRepository.save(customer);
        }
        return null; // Customer not found
    }

    // Delete customer
    public boolean deleteCustomer(String customerId) {
        Optional<Customer> existingCustomer = customerRepository.findById(customerId);
        if (existingCustomer.isPresent()) {
            customerRepository.deleteById(customerId);
            return true; // Customer deleted successfully
        }
        return false; // Customer not found
    }
}
