package com.example.courtbooking.controller;

import com.example.courtbooking.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Joshua Xing
 */
@RestController
@Slf4j
public class CustomerController {
    private static final String OK_RESPONSE_TEMPLATE = "{ \"id\" : %d }";

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/api/v1/customer")
    public ResponseEntity<String> createCustomer(String firstName, String lastName) {
        // TODO: empty/null check on first/last name and send failure response if check fails
        long id = customerService.createCustomer(firstName, lastName);
        log.info("Created customer: [id={}, firstName={}, lastName={}]", id, firstName, lastName);

        return ResponseEntity.ok(String.format(OK_RESPONSE_TEMPLATE, id));
    }
}
