package com.fundall.eWallet.controllers;

import com.fundall.eWallet.dto.transaction.CreateTransactionDto;
import com.fundall.eWallet.dto.transaction.ListTransactionsDto;
import com.fundall.eWallet.dto.transaction.TransactionDto;
import com.fundall.eWallet.dto.wallet.FundWalletDto;
import com.fundall.eWallet.dto.wallet.WalletDto;
import com.fundall.eWallet.execptions.AuthenticationFailException;
import com.fundall.eWallet.execptions.CustomException;
import com.fundall.eWallet.execptions.InsufficientBalanceException;
import com.fundall.eWallet.model.User;
import com.fundall.eWallet.services.AuthenticationService;
import com.fundall.eWallet.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payment")
public class PaymentController {
    private final PaymentService PaymentService;
    private final AuthenticationService authenticationService;

    public PaymentController(PaymentService paymentService, AuthenticationService authenticationService) {
        PaymentService = paymentService;
        this.authenticationService = authenticationService;
    }
    @PostMapping("/cards")
    public ResponseEntity<TransactionDto> BillPayment(@RequestParam("token") String token, @RequestBody CreateTransactionDto request)
            throws AuthenticationFailException, CustomException, InsufficientBalanceException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);

        TransactionDto transactionDto = PaymentService.billPayment(user, request);
        return new ResponseEntity<>(transactionDto, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<ListTransactionsDto> getCartItems(@RequestParam("token") String token)
            throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);

        ListTransactionsDto listTransactionsDto = PaymentService.listTransactions(user);
        return new ResponseEntity<>(listTransactionsDto, HttpStatus.OK);
    }
}
