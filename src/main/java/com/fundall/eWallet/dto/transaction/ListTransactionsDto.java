package com.fundall.eWallet.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ListTransactionsDto {
    private List<TransactionDto> transactions;
    private double totalAmount;
    private Integer totalNumber;
}
