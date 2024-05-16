package com.gloibgroup.urbanswap.controllers;

import com.gloibgroup.urbanswap.dtos.payloads.ApiResponse;
import com.gloibgroup.urbanswap.dtos.requests.CustomerSignupDTO;
import com.gloibgroup.urbanswap.models.Customer;
import com.gloibgroup.urbanswap.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Validated
public class CustomersController {
    private final CustomerService customersService;

    public CustomersController(CustomerService customerService) {
        this.customersService = customerService;
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

    @GetMapping("/{customerID}")
    public ResponseEntity<ApiResponse<Customer>> fetchCustomer(@PathVariable String customerID) {
        Customer customer = customersService.findCustomerById(customerID);
        ApiResponse<Customer> apiResponse = new ApiResponse<>("success", HttpStatus.OK.value(), customer);
        return ResponseEntity.ok(apiResponse);
    }
}
