package com.example.courtbooking.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Joshua Xing
 */
@Service
@Slf4j
public class DataInitializer implements CommandLineRunner {
    private static final List<String> COURT_NAMES = List.of("Alice", "Bella", "Caroline");
    private static final List<String> CUSTOMER_NAMES = List.of("Jason", "A", "Sally", "B", "Charlie", "C", "Leon", "D", "Nicky", "E");

    private final CourtService courtService;
    private final CustomerService customerService;

    @Autowired
    public DataInitializer(CourtService courtService, CustomerService customerService) {
        this.courtService = courtService;
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Initializing court data");
        for (String name : COURT_NAMES) {
            long id = courtService.createCourt(name);
            log.info("Created court: [id={}, name={}]", id, name);
        }

        for (int i = 0; i < CUSTOMER_NAMES.size(); i += 2) {
            long id = customerService.createCustomer(CUSTOMER_NAMES.get(i), CUSTOMER_NAMES.get(i + 1));
            log.info("Created user: [id={}, firstName={}, lastName={}]", id, CUSTOMER_NAMES.get(i), CUSTOMER_NAMES.get(i + 1));
        }
    }
}
