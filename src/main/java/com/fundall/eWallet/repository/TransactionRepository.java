package com.fundall.eWallet.repository;

import com.fundall.eWallet.model.Transaction;
import com.fundall.eWallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
     List<Transaction> findAllByUserOrderByCreatedDateDesc(User user);
}