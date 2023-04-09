package com.fundall.eWallet.services;

import com.fundall.eWallet.dto.wallet.WalletDto;
import com.fundall.eWallet.execptions.AuthenticationFailException;
import com.fundall.eWallet.execptions.CustomException;
import com.fundall.eWallet.model.Transaction;
import com.fundall.eWallet.model.User;
import com.fundall.eWallet.model.Wallet;
import com.fundall.eWallet.repository.TransactionRepository;
import com.fundall.eWallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;


@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    @Autowired
    public WalletService(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }
  public WalletDto showWallet(User user)throws CustomException {
        try{
            Wallet wallet = walletRepository.findWalletByUser(user);
            return new WalletDto(wallet);
        }catch (Exception e){ System.out.println("hashing password failed {}" + e.getMessage());
            throw new CustomException(e.getMessage());
        }

  }

    public WalletDto fundWallet(User user, Double amount) throws CustomException {
        try{
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
        }catch (Exception e){ e.printStackTrace();
            System.out.println("hashing password failed {}" + e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }
}
