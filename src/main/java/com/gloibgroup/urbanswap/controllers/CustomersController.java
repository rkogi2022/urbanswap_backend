package com.gloibgroup.urbanswap.controllers;

import com.gloibgroup.urbanswap.dtos.payloads.ApiResponse;
import com.gloibgroup.urbanswap.dtos.requests.CustomerSignupDTO;
import com.gloibgroup.urbanswap.models.Customer;
import com.gloibgroup.urbanswap.repositories.CustomerRepository;
import com.gloibgroup.urbanswap.services.CustomerService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Validated
public class CustomersController {
    private final CustomerService customersService;
    private final CustomerRepository customerRepository;

    public CustomersController(CustomerService customerService, CustomerRepository customerRepository) {
        this.customersService = customerService;
        this.customerRepository = customerRepository;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> signupCustomer(@Valid @RequestBody CustomerSignupDTO customerSignupDTO) {
        customersService.signupCustomer(customerSignupDTO);
        ApiResponse<String> apiResponse = new ApiResponse<>("success", HttpStatus.OK.value(), "Customer signed up successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Customer>>> fetchCustomers() {
        List<Customer> customers = customersService.fetchCustomers();
        ApiResponse<List<Customer>> apiResponse = new ApiResponse<>("success", HttpStatus.OK.value(), customers);
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/{customerId}")
    public String fetchCustomer(Authentication authentication) throws Exception {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        String uid = authentication.getName(); // Firebase uid
//        Customer customer = customersService.findCustomerById(uid);
//
//        return new ResponseEntity<>(customer, HttpStatus.OK);

//        UserRecord userRecord = FirebaseAuth.getInstance().getUser(customerRepository.findCustomerByPhoneNumber("0711223344").get().getId());
//        return new ResponseEntity<>(userRecord, HttpStatus.OK);

//        FirebaseToken token = (FirebaseToken) authentication.getPrincipal();
//        String uid = token.getUid();
//
//        return "User id: " + uid;

        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest()
                .setEmail("andrew@iniesta.com")
                .setPassword("pass123")
                .setUid("fefe")
                .setDisplayName("Dre");

        UserRecord userRecord = FirebaseAuth.getInstance().createUser(createRequest);
        return "User created successfully: " + userRecord.getUid();

//        try {
//            // Revoke the user's authentication token
//            FirebaseAuth.getInstance().deleteUser("rVsCd9iPtNM9dQJrG8MSOuX3Emx2");
//            return "HttpStatus.OK";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "HttpStatus.INTERNAL_SERVER_ERROR";
//        }
    }
}
