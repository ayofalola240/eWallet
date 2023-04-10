package com.fundall.eWallet.controllers;

import com.fundall.eWallet.dto.wallet.FundWalletDto;
import com.fundall.eWallet.dto.wallet.WalletDto;
import com.fundall.eWallet.execptions.AuthenticationFailException;
import com.fundall.eWallet.execptions.CustomException;
import com.fundall.eWallet.model.User;
import com.fundall.eWallet.services.AuthenticationService;
import com.fundall.eWallet.services.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("wallet")
public class WalletController {

    private static final Logger logger = LoggerFactory.getLogger(WalletController.class);


    private final WalletService walletService;
    private final AuthenticationService authenticationService;

    public WalletController(WalletService walletService, AuthenticationService authenticationService) {
        this.walletService = walletService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/balance")
    public ResponseEntity<WalletDto> showWallet(@RequestParam("token") String token)
            throws AuthenticationFailException, CustomException{
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);

        WalletDto walletDto = walletService.showWallet(user);
        return new ResponseEntity<>(walletDto, HttpStatus.OK);
    }

    @PostMapping("/fund")
    public ResponseEntity<WalletDto> fundWallet(@RequestParam("token") String token, @RequestBody FundWalletDto request)
            throws AuthenticationFailException, CustomException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);

        WalletDto walletDto = walletService.fundWallet(user, request.getAmount());
        return new ResponseEntity<>(walletDto, HttpStatus.OK);
    }

}
