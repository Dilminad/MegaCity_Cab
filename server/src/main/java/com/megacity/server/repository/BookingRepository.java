package com.megacity.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.megacity.server.model.Bookings;

import java.util.List;

public interface BookingRepository extends MongoRepository<Bookings, String> {

    // Find all bookings by customer ID
    List<Bookings> findByCustomerId(String customerId);

    // Find all bookings by driver ID
    List<Bookings> findByDriverId(String driverId);

    // Find all bookings by ride status (e.g., "PENDING", "ACCEPTED", "COMPLETED")
    List<Bookings> findByRideStatus(String rideStatus);

    // Find all active bookings
    List<Bookings> findByIsActive(boolean isActive);

    // Find all bookings by payment status (e.g., "PAID", "UNPAID")
    List<Bookings> findByPaymentStatus(String paymentStatus);

    // Find all bookings by driver ID and ride status
    List<Bookings> findByDriverIdAndRideStatus(String driverId, String rideStatus);

    // Find all bookings by customer ID and ride status
    List<Bookings> findByCustomerIdAndRideStatus(String customerId, String rideStatus);

    // Find all bookings by driver ID and payment status
    List<Bookings> findByDriverIdAndPaymentStatus(String driverId, String paymentStatus);

    // Find all bookings by customer ID and payment status
    List<Bookings> findByCustomerIdAndPaymentStatus(String customerId, String paymentStatus);
}