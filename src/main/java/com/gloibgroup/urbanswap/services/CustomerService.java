package com.gloibgroup.urbanswap.services;

import com.gloibgroup.urbanswap.dtos.requests.CustomerSignupDTO;
import com.gloibgroup.urbanswap.models.Customer;
import com.gloibgroup.urbanswap.repositories.CustomerRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

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
            saveToFirestore(customer);
            logger.info("Customer id {} signed up successfully: ", customer.getId());
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

    private static void saveToFirestore(Customer customer) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("customers").document(customer.getId());
        ApiFuture<WriteResult> result = docRef.set(customer);

        try {
            WriteResult writeResult = result.get();
            logger.info("Customer id {} saved to Firestore at {}", customer.getId(), writeResult.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Could not save customer id {} to Firestore", customer.getId(), e);
            throw new RuntimeException("Error saving customer to Firestore", e);
        }
    }

    private void saveToPrimaryDatabase(Customer customer) {
        customerRepository.save(customer);
    }

    private Customer createCustomerFromDto(CustomerSignupDTO customerSignupDTO) {
        Customer customer = new Customer();
        customer.setId(String.valueOf(UUID.randomUUID()));
        customer.setEmail(customerSignupDTO.getEmail());
        customer.setPhoneNumber(customerSignupDTO.getPhoneNumber());
        customer.setFirstName(customerSignupDTO.getFirstName());
        customer.setLastName(customerSignupDTO.getLastName());
        return customer;
    }

    public List<Customer> fetchCustomers() {
        return customerRepository.findAll();
    }
}
