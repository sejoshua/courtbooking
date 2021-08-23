package com.example.courtbooking.service.impl;

import com.example.courtbooking.config.ApplicationConfig;
import com.example.courtbooking.model.impl.Booking;
import com.example.courtbooking.model.impl.Court;
import com.example.courtbooking.model.impl.Customer;
import com.example.courtbooking.model.impl.CustomerBooking;
import com.example.courtbooking.repo.DataRepo;
import com.example.courtbooking.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * @author Joshua Xing
 */
@Service
@Slf4j
public class BookingServiceImpl implements BookingService {
    private final DataRepo<Court> courtRepo;
    private final DataRepo<Customer> customerRepo;
    private final DataRepo<Booking> bookingRepo;
    private final DataRepo<CustomerBooking> customerBookingRepo;
    private final int maxAllowedCustomer;

    @Autowired
    public BookingServiceImpl(ApplicationConfig config,
                              @Qualifier(ApplicationConfig.COURT_REPO_NAME) DataRepo<Court> courtRepo,
                              @Qualifier(ApplicationConfig.CUSTOMER_REPO_NAME) DataRepo<Customer> customerRepo,
                              @Qualifier(ApplicationConfig.BOOKING_REPO_NAME) DataRepo<Booking> bookingRepo,
                              @Qualifier(ApplicationConfig.CUSTOMER_BOOKING_REPO_NAME) DataRepo<CustomerBooking> customerBookingRepo) {
        this.courtRepo = courtRepo;
        this.customerRepo = customerRepo;
        this.bookingRepo = bookingRepo;
        this.customerBookingRepo = customerBookingRepo;
        this.maxAllowedCustomer = config.getMaxAllowedCustomer();
    }

    @Override
    public long createBooking(long courtId, long customerId, LocalDate bookingDate) {
        Court court = courtRepo.getData(courtId);
        Customer customer = customerRepo.getData(customerId);
        // TODO: add null check

        CustomerBooking customerBooking = new CustomerBooking();
        customerBooking.setCustomer(customer);
        customerBooking.setCourt(court);
        long customerBookingId = customerBookingRepo.addData(customerBooking);

        Booking booking = new Booking();
        booking.setCourt(court);
        booking.addCustomerBooking(customerBooking);
        booking.setBookingDate(bookingDate);
        bookingRepo.addData(booking);

        return customerBookingId;
    }

    @Override
    public long addCustomerBooking(long bookingId, long customerId, LocalDate bookingDate) {
        Booking booking = getBooking(bookingId);
        if (booking.getCustomerBookings().size() == maxAllowedCustomer) {
            return -1L;
        }

        Customer customer = customerRepo.getData(customerId);

        CustomerBooking customerBooking = new CustomerBooking();
        customerBooking.setCustomer(customer);
        customerBooking.setCourt(booking.getCourt());
        long customerBookingId = customerBookingRepo.addData(customerBooking);
        // TODO: check if the same customer has already made a booking for the same court on the same date
        booking.addCustomerBooking(customerBooking);

        if (booking.getCustomerBookings().size() == maxAllowedCustomer) {
            log.info("Booking for court {} is full on {}. Customers are {}.",
                    booking.getCourt(),
                    bookingDate,
                    booking.getCustomerBookings().stream().map(CustomerBooking::getCustomer).collect(Collectors.toList()));
        }

        return customerBookingId;
    }

    @Override
    public Booking getBooking(long id) {
        return bookingRepo.getData(id);
    }

    @Override
    public Booking getBooking(long courtId, LocalDate bookingDate) {
        for (Booking booking : bookingRepo.getData()) {
            if (booking.getCourt().getId() == courtId && booking.getBookingDate().isEqual(bookingDate)) {
                return booking;
            }
        }
        return null;
    }
}
