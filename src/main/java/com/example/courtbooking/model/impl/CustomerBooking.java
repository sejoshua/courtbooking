package com.example.courtbooking.model.impl;

import com.example.courtbooking.model.DataWithId;
import lombok.Data;

/**
 * @author Joshua Xing
 */
@Data
public class CustomerBooking implements DataWithId {
    private long id;
    private Customer customer;
    private Court court;
    // TODO: add other fields
}
