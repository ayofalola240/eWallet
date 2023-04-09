package com.fundall.eWallet.dto.wallet;

import lombok.Data;

@Data
public class FundWalletDto {
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
