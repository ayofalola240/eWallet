package com.fundall.eWallet.dto.transaction;

import com.fundall.eWallet.model.Transaction;
import com.fundall.eWallet.model.User;
import com.fundall.eWallet.model.Wallet;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionDto {
    private Integer id;
    private Double amount;
    private User user;
    private String description;
    private Date updateDate;

    public TransactionDto(Transaction transaction) {
        this.setId(transaction.getId());
        this.setAmount(transaction.getAmount());
        this.setUser(transaction.getUser());
        this.setDescription(transaction.getDescription());
    }
}
