package com.example.courtbooking.config;

import com.example.courtbooking.model.impl.Booking;
import com.example.courtbooking.model.impl.Court;
import com.example.courtbooking.model.impl.Customer;
import com.example.courtbooking.model.impl.CustomerBooking;
import com.example.courtbooking.repo.DataRepo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Joshua Xing
 */
@Configuration
public class ApplicationConfig {
    public static final String COURT_REPO_NAME = "courtRepo";
    public static final String CUSTOMER_REPO_NAME = "customerRepo";
    public static final String BOOKING_REPO_NAME = "bookingRepo";
    public static final String CUSTOMER_BOOKING_REPO_NAME = "customerBookingRepo";

    @Value("${booking.maxAllowedCustomer}")
    @Getter
    private int maxAllowedCustomer;

    @Bean(COURT_REPO_NAME)
    public DataRepo<Court> getCourtRepo() {
        return new DataRepo<>();
    }

    @Bean(CUSTOMER_REPO_NAME)
    public DataRepo<Customer> getCustomerRepo() {
        return new DataRepo<>();
    }

    @Bean(BOOKING_REPO_NAME)
    public DataRepo<Booking> getBookingRepo() {
        return new DataRepo<>();
    }

    @Bean(CUSTOMER_BOOKING_REPO_NAME)
    public DataRepo<CustomerBooking> getCustomerBookingRepo() {
        return new DataRepo<>();
    }
}
