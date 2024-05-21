package com.gloibgroup.urbanswap.models;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallets")
public class Wallet {

    @Id
    private String id = UUID.randomUUID().toString();
    @Column(name = "user_id")
    private String userId;
    private BigDecimal balance;

    // Add any required constructors, if needed
}
