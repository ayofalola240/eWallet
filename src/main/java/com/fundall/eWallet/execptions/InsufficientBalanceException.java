package com.fundall.eWallet.execptions;

public class InsufficientBalanceException extends Exception {

    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}
