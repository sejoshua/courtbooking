package com.example.courtbooking.service.impl;

import com.example.courtbooking.config.ApplicationConfig;
import com.example.courtbooking.model.impl.Customer;
import com.example.courtbooking.repo.DataRepo;
import com.example.courtbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Joshua Xing
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    private final DataRepo<Customer> repo;

    @Autowired
    public CustomerServiceImpl(@Qualifier(ApplicationConfig.CUSTOMER_REPO_NAME) DataRepo<Customer> repo) {
        this.repo = repo;
    }

    @Override
    public synchronized long createCustomer(String firstName, String lastName) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        return repo.addData(customer);
    }

    @Override
    public Customer getCustomer(long id) {
        return repo.getData(id);
    }
}
