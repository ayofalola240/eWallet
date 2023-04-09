package com.fundall.eWallet.dto.transaction;

import com.fundall.eWallet.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionDto {
    private Double amount;
    private String transactionType;
    private String description;
}
