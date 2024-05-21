package com.gloibgroup.urbanswap.repositories;

import com.gloibgroup.urbanswap.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, String> {
    // You can add custom methods here if needed
}
