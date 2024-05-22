package com.gloibgroup.urbanswap.controllers;

import com.gloibgroup.urbanswap.dtos.payloads.ApiResponse;
import com.gloibgroup.urbanswap.models.Wallet;
import com.gloibgroup.urbanswap.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/wallets")
public class WalletsController {

    private final WalletService walletService;

    @Autowired
    public WalletsController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Wallet>> createWallet(@RequestBody Wallet wallet) {
        Wallet wallet1 = walletService.createWallet(wallet);
        ApiResponse<Wallet> apiResponse = new ApiResponse<>("success", HttpStatus.OK.value(), wallet1);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{walletId}/balance")
    public BigDecimal checkWalletBalance(@PathVariable String walletId) {
        return walletService.checkWalletBalance(walletId);
    }

    @PatchMapping("/{walletId}/load")
    public void loadWalletFromMobileMoney(@PathVariable String walletId, @RequestParam BigDecimal amount) {
        walletService.loadWalletFromMobileMoney(walletId, amount);
    }

    @PostMapping("/{walletId}/transfer")
    public void transferToMobileMoney(@PathVariable String walletId, @RequestParam BigDecimal amount) {
        walletService.transferToMobileMoney(walletId, amount);
    }
}
