package com.gloibgroup.urbanswap.services;

import com.gloibgroup.urbanswap.dtos.requests.CustomerSignupDTO;
import com.gloibgroup.urbanswap.models.Customer;
import com.gloibgroup.urbanswap.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void signupCustomer(CustomerSignupDTO customerSignupDTO) {
        checkIfCustomerExists(customerSignupDTO);

        Customer customer = createCustomerFromDto(customerSignupDTO);

        try {
            saveToPrimaryDatabase(customer);
            logger.info("Customer ID {} signed up successfully: ", customer.getId());
        } catch (Exception e) {
            logger.error("Could not save customer record: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to sign up customer");
        }
    }

    private void checkIfCustomerExists(CustomerSignupDTO customerSignupDTO) {
        if (customerRepository.findCustomerByPhoneNumber(customerSignupDTO.getPhoneNumber()).isPresent() ||
                customerRepository.findCustomerByEmail(customerSignupDTO.getEmail()).isPresent()) {
            logger.error("Customer phone number {} or email {} is not unique", customerSignupDTO.getPhoneNumber(), customerSignupDTO.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer with entered credentials already exists");
        }
    }

    private void saveToPrimaryDatabase(Customer customer) {
        customerRepository.save(customer);
    }

    private Customer createCustomerFromDto(CustomerSignupDTO customerSignupDTO) {
        Customer customer = new Customer();
        customer.setEmail(customerSignupDTO.getEmail());
        customer.setPhoneNumber(customerSignupDTO.getPhoneNumber());
        customer.setFirstName(customerSignupDTO.getFirstName());
        customer.setLastName(customerSignupDTO.getLastName());
        customer.setFirebaseUID(customerSignupDTO.getFirebaseUID());
        return customer;
    }

    public List<Customer> fetchCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomerById(UUID customerID) {
        Optional<Customer> customerOptional = customerRepository.findById(customerID);
        if (customerOptional.isPresent()) {
            logger.info("Found customer with ID {}", customerID);
            return customerOptional.get();
        } else {
            logger.warn("Customer with ID {} not found", customerID);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer record not found");
        }
    }
}
