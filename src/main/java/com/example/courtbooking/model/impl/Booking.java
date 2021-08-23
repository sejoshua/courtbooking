package com.example.courtbooking.model.impl;

import com.example.courtbooking.model.DataWithId;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joshua Xing
 */
@Data
public class Booking implements DataWithId {
    private long id;
    private Court court;
    private LocalDate bookingDate;
    private List<CustomerBooking> customerBookings = new ArrayList<>();
    // TODO: add other fields

    public void addCustomerBooking(CustomerBooking customerBooking) {
        customerBookings.add(customerBooking);
    }
}
