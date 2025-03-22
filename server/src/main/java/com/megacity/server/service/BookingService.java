package com.megacity.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.megacity.server.model.Bookings;
import com.megacity.server.repository.BookingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Create a new booking
    public Bookings createBooking(Bookings booking) {
        booking.setActive(true);
        booking.setRideStatus("PENDING"); // Default status
        booking.setDriverAccepted(false); // Default to false
        return bookingRepository.save(booking);
    }

    // Get all bookings
    public List<Bookings> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get booking by ID
    public Optional<Bookings> getBookingById(String bookingId) {
        return bookingRepository.findById(bookingId);
    }

    // Update existing booking
    public Bookings updateBooking(String bookingId, Bookings updatedBooking) {
        Optional<Bookings> existingBooking = bookingRepository.findById(bookingId);
        if (existingBooking.isPresent()) {
            Bookings booking = existingBooking.get();
            booking.setCustomerId(updatedBooking.getCustomerId());
            booking.setDriverId(updatedBooking.getDriverId());
            booking.setPickupDate(updatedBooking.getPickupDate());
            booking.setPickupTime(updatedBooking.getPickupTime());
            booking.setPickupLocation(updatedBooking.getPickupLocation());
            booking.setDropOffLocation(updatedBooking.getDropOffLocation());
            booking.setRideStatus(updatedBooking.getRideStatus());
            booking.setTotalfare(updatedBooking.getTotalfare());
            booking.setDestination(updatedBooking.getDestination());
            booking.setDriverAccepted(updatedBooking.isDriverAccepted());
            booking.setPaymentStatus(updatedBooking.getPaymentStatus());
            return bookingRepository.save(booking);
        }
        return null; // Booking not found
    }

    // Update booking status (active or inactive)
    public Bookings updateBookingStatus(String bookingId, boolean isActive) {
        Optional<Bookings> existingBooking = bookingRepository.findById(bookingId);
        if (existingBooking.isPresent()) {
            Bookings booking = existingBooking.get();
            booking.setActive(isActive);
            return bookingRepository.save(booking);
        }
        return null; // Booking not found
    }

    // Delete booking
    public boolean deleteBooking(String bookingId) {
        Optional<Bookings> existingBooking = bookingRepository.findById(bookingId);
        if (existingBooking.isPresent()) {
            bookingRepository.deleteById(bookingId);
            return true; // Booking deleted successfully
        }
        return false; // Booking not found
    }

    // Driver accepts a booking
    public Bookings acceptBooking(String bookingId, String driverId) {
        Optional<Bookings> existingBooking = bookingRepository.findById(bookingId);
        if (existingBooking.isPresent()) {
            Bookings booking = existingBooking.get();
            booking.setDriverId(driverId);
            booking.setDriverAccepted(true);
            booking.setRideStatus("ACCEPTED");
            return bookingRepository.save(booking);
        }
        return null; // Booking not found
    }

    // Driver rejects a booking
    public Bookings rejectBooking(String bookingId) {
        Optional<Bookings> existingBooking = bookingRepository.findById(bookingId);
        if (existingBooking.isPresent()) {
            Bookings booking = existingBooking.get();
            booking.setDriverId(null);
            booking.setDriverAccepted(false);
            booking.setRideStatus("REJECTED");
            return bookingRepository.save(booking);
        }
        return null; // Booking not found
    }
}