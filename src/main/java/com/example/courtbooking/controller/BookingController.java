package com.example.courtbooking.controller;

import com.example.courtbooking.model.impl.Booking;
import com.example.courtbooking.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * @author Joshua Xing
 */
@RestController
@Slf4j
public class BookingController {
    private static final String OK_RESPONSE_TEMPLATE = "{ \"id\" : %d }";

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/api/v1/booking")
    public ResponseEntity<String> createBooking(long courtId,
                                                long customerId,
                                                @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                LocalDate date) {
        // TODO: negative check on court/customer id and send failure response if check fails
        // TODO: check if date is a future date and send failure response if check fails
        Booking booking = bookingService.getBooking(courtId, date);
        if (booking == null) {
            long id = bookingService.createBooking(courtId, customerId, date);
            log.info("Created court and customer booking: [customBookingId={}, courtId={}, customerId={}, date={}]",
                    id, courtId, customerId, date);
            return ResponseEntity.ok(String.format(OK_RESPONSE_TEMPLATE, id));
        } else {
            long id = bookingService.addCustomerBooking(booking.getId(), customerId, date);
            if (id == -1L) {
                log.info("Failed to create customer booking due to booking is full: [courtId={}, customerId={}, date={}]",
                        courtId, customerId, date);
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                        .body("{ \"failureMessage\" : \"Booking for this court is full on this date\" }");
            } else {
                log.info("Created customer booking for booking {}: [customerBookingId={}]", booking.getId(), id);
                return ResponseEntity.ok(String.format(OK_RESPONSE_TEMPLATE, id));
            }
        }
    }
}
