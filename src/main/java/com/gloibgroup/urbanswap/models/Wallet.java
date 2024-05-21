package com.gloibgroup.urbanswap.models;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Wallet {

    @Id
    private String id = UUID.randomUUID().toString();
    private String userId;
    private BigDecimal balance;

    // Add any required constructors, if needed
}
