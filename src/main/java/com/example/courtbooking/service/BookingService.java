package com.example.courtbooking.service;

import com.example.courtbooking.model.impl.Booking;

import java.time.LocalDate;

/**
 * @author Joshua Xing
 */
public interface BookingService {
    long createBooking(long courtId, long customerId, LocalDate bookingDate);

    long addCustomerBooking(long bookingId, long customerId, LocalDate bookingDate);

    Booking getBooking(long id);

    Booking getBooking(long courtId, LocalDate bookingDate);
}
