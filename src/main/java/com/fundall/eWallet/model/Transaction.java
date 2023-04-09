package com.fundall.eWallet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Transactions", schema = "transaction_schema")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "transaction_type")
    private TransactionType transactionType;
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private double amount;

    @ManyToOne()
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private Wallet wallet;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Transaction(double amount,String description, User user, Wallet wallet, TransactionType transactionType) {
        this.user = user;
        this.amount = amount;
        this.description = description;
        this.transactionType = transactionType;
        this.wallet = wallet;
        this.createdDate = new Date();
    }
}
