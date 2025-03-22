package com.megacity.server.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megacity.server.model.Admin;
import com.megacity.server.model.Customer;
import com.megacity.server.model.Driver;
import com.megacity.server.model.Vehicle; // Add this import
import com.megacity.server.repository.AdminRepository;
import com.megacity.server.repository.CustomerRepository;
import com.megacity.server.repository.DriverRepository;
import com.megacity.server.repository.VehicleRepository; // Add this import

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DriverRepository cabRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private VehicleRepository vehicleRepository; // Add this repository

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // First, try to load the Customer entity
        Customer customer = customerRepository.findByUserName(username).orElse(null);
        if (customer != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(customer.getUserName())
                    .password(customer.getPassword())
                    .authorities("ROLE_CUSTOMER")
                    .build();
        }

        // If Customer is not found, try to load the Driver entity
        Driver cabs = cabRepository.findByUserName(username).orElse(null);
        if (cabs != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(cabs.getUserName())
                    .password(cabs.getPassword())
                    .authorities("ROLE_DRIVER")
                    .build();
        }

        // If Driver is not found, try to load the Admin entity
        Admin admin = adminRepository.findByUserName(username).orElse(null);
        if (admin != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(admin.getUserName())
                    .password(admin.getPassword())
                    .authorities("ROLE_ADMIN")
                    .build();
        }

        // If Admin is not found, try to load the Vehicle entity
        Vehicle vehicle = vehicleRepository.findByUserName(username).orElse(null);
        if (vehicle != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(vehicle.getUserName())
                    .password(vehicle.getPassword())
                    .authorities("ROLE_VEHICLE")
                    .build();
        }

        // If none of the entities are found, throw an exception
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}