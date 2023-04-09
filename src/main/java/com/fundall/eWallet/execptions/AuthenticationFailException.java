package com.fundall.eWallet.execptions;

public class AuthenticationFailException extends Exception {
    public AuthenticationFailException(String msg) {
        super(msg);
    }
}
