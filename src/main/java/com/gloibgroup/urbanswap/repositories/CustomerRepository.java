package com.gloibgroup.urbanswap.repositories;

import com.gloibgroup.urbanswap.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findCustomerByPhoneNumber(String phoneNumber);
    Optional<Customer> findCustomerByEmail(String email);
}
