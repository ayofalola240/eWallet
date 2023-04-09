package com.fundall.eWallet.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInResponseDto {
    private String status;
    private String token;
}
