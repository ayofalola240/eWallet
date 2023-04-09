package com.fundall.eWallet.services;

import com.fundall.eWallet.repository.TransactionRepository;
import com.fundall.eWallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentService {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    @Autowired
    public PaymentService(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

}
