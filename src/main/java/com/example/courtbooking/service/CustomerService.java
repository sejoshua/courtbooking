package com.example.courtbooking.service;

import com.example.courtbooking.model.impl.Customer;

/**
 * @author Joshua Xing
 */
public interface CustomerService {
   long createCustomer(String firstName, String lastName);

   Customer getCustomer(long id);
}
