package com.fundall.eWallet.dto.transaction;

import com.fundall.eWallet.model.Transaction;
import com.fundall.eWallet.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionDto {
    private Integer id;
    private Double amount;
    private Date createdDate;
    private String transactionType;
    private String description;

    public TransactionDto(Transaction transaction) {
        this.setId(transaction.getId());
        this.setTransactionType(transaction.getTransactionType());
        this.setAmount(transaction.getAmount());
        this.setDescription(transaction.getDescription());
        this.setCreatedDate(transaction.getCreatedDate());
    }
}
