package com.fundall.eWallet.services;

import com.fundall.eWallet.Config.MessageStrings;
import com.fundall.eWallet.dto.transaction.CreateTransactionDto;
import com.fundall.eWallet.dto.transaction.TransactionDto;
import com.fundall.eWallet.dto.wallet.WalletDto;
import com.fundall.eWallet.execptions.CustomException;
import com.fundall.eWallet.execptions.InsufficientBalanceException;
import com.fundall.eWallet.model.Transaction;
import com.fundall.eWallet.model.User;
import com.fundall.eWallet.model.Wallet;
import com.fundall.eWallet.repository.TransactionRepository;
import com.fundall.eWallet.repository.WalletRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentService {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    @Autowired
    public PaymentService(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    public TransactionDto billPayment(User user, CreateTransactionDto transactionDto) throws CustomException, InsufficientBalanceException{
        try{
            Wallet wallet = walletRepository.findWalletByUser(user);
            if (wallet.getBalance() < transactionDto.getAmount()) {
                throw new InsufficientBalanceException("Insufficient balance in wallet, Add money to your wallet");
            }
            wallet.setBalance(wallet.getBalance() - transactionDto.getAmount());
            walletRepository.save(wallet);

            Transaction transaction = new Transaction();
            transaction.setStatus(MessageStrings.SUCCESS);
            transaction.setTransactionType(transactionDto.getTransactionType());
            transaction.setAmount(transactionDto.getAmount());
            transaction.setDescription(transactionDto.getDescription());
            transaction.setUser(user);
            transaction.setWallet(wallet);
            transaction.setCreatedDate(new Date());
            transactionRepository.save(transaction);
            return new TransactionDto(transaction);
        }catch (Exception e){
            System.out.println("hashing password failed {}" + e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

}
