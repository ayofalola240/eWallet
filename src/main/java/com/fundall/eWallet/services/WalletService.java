package com.fundall.eWallet.services;

import com.fundall.eWallet.dto.wallet.WalletDto;
import com.fundall.eWallet.model.User;
import com.fundall.eWallet.model.Wallet;
import com.fundall.eWallet.repository.WalletRepository;
import org.springframework.stereotype.Service;


@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
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
        return new WalletDto(wallet);
    }
}
