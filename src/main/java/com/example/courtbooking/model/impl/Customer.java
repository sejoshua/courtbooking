package com.example.courtbooking.model.impl;

import com.example.courtbooking.model.DataWithId;
import lombok.Data;

/**
 * @author Joshua Xing
 */
@Data
public class Customer implements DataWithId {
    private long id;
    private String firstName;
    private String lastName;
    // TODO: add other fields
}
