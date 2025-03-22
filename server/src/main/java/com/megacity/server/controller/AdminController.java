package com.megacity.server.controller;

import com.megacity.server.model.Admin;
import com.megacity.server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class AdminController {

    @Autowired
    private AdminService adminService;

    

    // Get all admins
    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    // Get admin by ID
    @GetMapping("/admin/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("id") String id) {
        Optional<Admin> admin = adminService.getAdminById(id);
        return admin.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new admin
    @PostMapping("/admin/createAdmin")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = adminService.saveAdmin(admin);
        return ResponseEntity.ok(savedAdmin);
    }

    // Update an existing admin
    @PutMapping("/admin/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("id") String id, @RequestBody Admin adminDetails) {
        Optional<Admin> optionalAdmin = adminService.getAdminById(id);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            admin.setFirstName(adminDetails.getFirstName());
            admin.setLastName(adminDetails.getLastName());
            admin.setUserName(adminDetails.getUserName());

            // Encrypt password if it's changed
            if (adminDetails.getPassword() != null && !adminDetails.getPassword().isEmpty()) {
                admin.setPassword(adminDetails.getPassword());
            }

            admin.setEmail(adminDetails.getEmail());
            admin.setNic(adminDetails.getNic());
            admin.setPhone(adminDetails.getPhone());
            admin.setProfilePicture(adminDetails.getProfilePicture());
            admin.setActive(adminDetails.isActive());

            Admin updatedAdmin = adminService.saveAdmin(admin);
            return ResponseEntity.ok(updatedAdmin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an admin
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable("id") String id) {
        boolean deleted = adminService.deleteAdmin(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
