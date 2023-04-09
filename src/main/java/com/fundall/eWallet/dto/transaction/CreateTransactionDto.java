package com.fundall.eWallet.dto.transaction;

import com.fundall.eWallet.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CreateTransactionDto {
    private Integer id;
    private Double amount;
    private User user;
    private String description;
    private Date updateDate;
}
