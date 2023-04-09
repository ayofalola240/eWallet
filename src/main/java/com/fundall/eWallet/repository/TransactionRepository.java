package com.fundall.eWallet.repository;

import com.fundall.eWallet.model.Transaction;
import com.fundall.eWallet.model.TransactionType;
import com.fundall.eWallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
     List<Transaction> findAllByUserOrderByCreatedDateDesc(User user);
     List<Transaction> findAllByTransactionTypeOrderByCreatedDateDesc(TransactionType type);
}