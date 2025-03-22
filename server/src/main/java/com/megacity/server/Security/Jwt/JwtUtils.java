package com.megacity.server.Security.Jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.megacity.server.model.*;
import com.megacity.server.repository.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.util.Date;

@Service
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DriverRepository cabsRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Value("${app.secret:default-secret-key}")
    private String secret;

    @Value("${app.jwt.expiration:86400000}") // Default to 24 hours
    private long expirationTime;

    private Key key() {
        try {
            return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        } catch (Exception e) {
            logger.error("Failed to decode secret key: {}", e.getMessage(), e);
            throw new RuntimeException("Invalid secret key", e);
        }
    }

    public String generateJwtToken(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new IllegalArgumentException("Authentication or principal cannot be null");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String role = getRoleFromUserDetails(userDetails);
        String userId = getUserIdFromDatabase(userDetails, role);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", role)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expirationTime))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private String getRoleFromUserDetails(UserDetails userDetails) {
        return userDetails.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElseThrow(() -> new IllegalStateException("User has no roles assigned"));
    }

    private String getUserIdFromDatabase(UserDetails userDetails, String role) {
        switch (role) {
            case "ROLE_CUSTOMER":
                return customerRepository.findByUserName(userDetails.getUsername())
                        .map(Customer::getCustomerId)
                        .orElseThrow(() -> new RuntimeException("Customer not found"));
            case "ROLE_DRIVER":
                return cabsRepository.findByUserName(userDetails.getUsername())
                        .map(Driver::getDriverId)
                        .orElseThrow(() -> new RuntimeException("Driver not found"));
            case "ROLE_ADMIN":
                return adminRepository.findByUserName(userDetails.getUsername())
                        .map(Admin::getAdminId)
                        .orElseThrow(() -> new RuntimeException("Admin not found"));
            case "ROLE_VEHICLE":
                return vehicleRepository.findByUserName(userDetails.getUsername())
                        .map(Vehicle::getVehicleId)
                        .orElseThrow(() -> new RuntimeException("Vehicle not found"));
            default:
                throw new RuntimeException("Invalid role: " + role);
        }
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
            return true;
        } catch (Exception e) {
            logger.error("Invalid JWT token: {}", e.getMessage(), e);
            return false;
        }
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public String getUserIdFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().get("userId", String.class);
    }
}