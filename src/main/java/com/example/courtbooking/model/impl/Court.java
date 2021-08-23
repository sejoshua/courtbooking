package com.example.courtbooking.model.impl;

import com.example.courtbooking.model.DataWithId;
import lombok.Data;

/**
 * @author Joshua Xing
 */
@Data
public class Court implements DataWithId {
    private long id;
    private String name;
    // TODO: add other fields
}
