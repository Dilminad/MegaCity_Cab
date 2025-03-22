package com.megacity.server.service;

import com.megacity.server.model.Admin;
import com.megacity.server.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private  AdminRepository adminRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    
    

    // Create or update an admin with password hashing
    public Admin saveAdmin(Admin admin) {
        
        String encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);
        return adminRepository.save(admin);
    }

    // Get all admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Get a specific admin by adminId
    public Optional<Admin> getAdminById(String adminId) {
        return adminRepository.findById(adminId);
    }

    // Deactivate an admin account
    public boolean deactivateAdmin(String adminId) {
        Optional<Admin> adminOptional = adminRepository.findById(adminId);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            admin.setActive(false);
            adminRepository.save(admin);
            return true;
        }
        return false;
    }

    // Activate an admin account
    public boolean activateAdmin(String adminId) {
        Optional<Admin> adminOptional = adminRepository.findById(adminId);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            admin.setActive(true);
            adminRepository.save(admin);
            return true;
        }
        return false;
    }

    // Delete an admin by adminId
    public boolean deleteAdmin(String adminId) {
        if (adminRepository.existsById(adminId)) {
            adminRepository.deleteById(adminId);
            return true;
        }
        return false;
    }
}
