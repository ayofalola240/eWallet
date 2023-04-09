package com.fundall.eWallet.services;

import com.fundall.eWallet.dto.wallet.WalletDto;
import com.fundall.eWallet.model.Transaction;
import com.fundall.eWallet.model.TransactionType;
import com.fundall.eWallet.model.User;
import com.fundall.eWallet.model.Wallet;
import com.fundall.eWallet.repository.TransactionRepository;
import com.fundall.eWallet.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public WalletService(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }
  public WalletDto showWallet(User user) {
      Wallet wallet = walletRepository.findWalletByUser(user);
      return new WalletDto(wallet);
  }

    public WalletDto fundWallet(User user, Double amount) {
        Wallet wallet = walletRepository.findWalletByUser(user);
        Double newBalance = wallet.getBalance() + amount;
        wallet.setBalance(newBalance);
        walletRepository.save(wallet);

        Transaction transaction = new Transaction();
        transaction.setTransactionType("CREDIT_WALLET");
        transaction.setCreatedDate(new Date());
        transaction.setAmount(amount);
        transaction.setWallet(wallet);
        transaction.setUser(user);
        transaction.setDescription("Wallet credit successfull!");
        transactionRepository.save(transaction);
        return new WalletDto(wallet);
    }
}
