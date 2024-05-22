package com.gloibgroup.urbanswap.repositories;

import com.gloibgroup.urbanswap.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}
