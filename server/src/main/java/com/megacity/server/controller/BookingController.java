package com.megacity.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.megacity.server.model.Bookings;
import com.megacity.server.service.BookingService;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController

public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Create a new booking
    @PostMapping("/customer/bookings")
    public ResponseEntity<?> createBooking(@RequestBody @Valid Bookings booking) {
        Bookings createdBooking = bookingService.createBooking(booking);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Booking created successfully");
        response.put("data", createdBooking);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all bookings
    @GetMapping("/customer/Allbookings")
    public ResponseEntity<?> getAllBookings() {
        List<Bookings> bookings = bookingService.getAllBookings();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Fetched all bookings");
        response.put("data", bookings);
        return ResponseEntity.ok(response);
    }

    // Get booking by ID
    @GetMapping("/{bookingId}")
    public ResponseEntity<?> getBookingById(@PathVariable String bookingId) {
        Optional<Bookings> booking = bookingService.getBookingById(bookingId);
        Map<String, Object> response = new HashMap<>();
        if (booking.isPresent()) {
            response.put("message", "Booking fetched successfully");
            response.put("data", booking.get());
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Booking not found");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Update existing booking
    @PutMapping("/{bookingId}")
    public ResponseEntity<?> updateBooking(@PathVariable String bookingId, @RequestBody @Valid Bookings updatedBooking) {
        Bookings booking = bookingService.updateBooking(bookingId, updatedBooking);
        Map<String, Object> response = new HashMap<>();
        if (booking != null) {
            response.put("message", "Booking updated successfully");
            response.put("data", booking);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Booking not found");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Driver accepts a booking
    @PutMapping("/{bookingId}/accept")
    public ResponseEntity<?> acceptBooking(@PathVariable String bookingId, @RequestParam String driverId) {
        Bookings booking = bookingService.acceptBooking(bookingId, driverId);
        Map<String, Object> response = new HashMap<>();
        if (booking != null) {
            response.put("message", "Booking accepted successfully");
            response.put("data", booking);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Booking not found");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Driver rejects a booking
    @PutMapping("/{bookingId}/reject")
    public ResponseEntity<?> rejectBooking(@PathVariable String bookingId) {
        Bookings booking = bookingService.rejectBooking(bookingId);
        Map<String, Object> response = new HashMap<>();
        if (booking != null) {
            response.put("message", "Booking rejected successfully");
            response.put("data", booking);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Booking not found");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Delete booking
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<?> deleteBooking(@PathVariable String bookingId) {
        boolean isDeleted = bookingService.deleteBooking(bookingId);
        Map<String, Object> response = new HashMap<>();
        if (isDeleted) {
            response.put("message", "Booking deleted successfully");
            response.put("data", null);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Booking not found");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}