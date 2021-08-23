package com.example.courtbooking.service;

import com.example.courtbooking.model.impl.Court;

/**
 * @author Joshua Xing
 */
public interface CourtService {
    long createCourt(String name);

    Court getCourt(long id);
}
