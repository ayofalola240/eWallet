package com.fundall.eWallet.dto.wallet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fundall.eWallet.model.User;
import com.fundall.eWallet.model.Wallet;
import lombok.Data;

import java.util.Date;


@Data
public class WalletDto {
    private Integer id;
    private Double balance;
    private Date updateDate;

    public WalletDto(Wallet wallet) {
        this.setId(wallet.getId());
        this.setBalance(wallet.getBalance());
        this.setUpdateDate(wallet.getUpdateDate());
    }
}
