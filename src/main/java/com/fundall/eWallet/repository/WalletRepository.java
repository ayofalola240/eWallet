package com.fundall.eWallet.repository;

import com.fundall.eWallet.model.User;
import com.fundall.eWallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
     Wallet findWalletByUser(User user);
}