package com.megacity.server.model;

import java.time.LocalDateTime;
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
@Document(collection = "bookings")
public class Bookings {

    @Id
    private String bookingId;
    private String customerId;
    private String driverId; // Driver who accepted the booking
    private LocalDateTime pickupDate;
    private LocalDateTime pickupTime;
    private String pickupLocation;
    private String dropOffLocation;
    private String rideStatus; // e.g., "PENDING", "ACCEPTED", "IN_PROGRESS", "COMPLETED", "CANCELLED"
    private double Totalfare;
    private boolean isActive;
    private String Destination;
    private boolean isDriverAccepted; // Track if the driver has accepted the booking
    private String paymentStatus; // Add this field to track payment status (e.g., "PAID", "UNPAID")
}