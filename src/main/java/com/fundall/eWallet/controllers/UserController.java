package com.fundall.eWallet.controllers;

import com.fundall.eWallet.dto.user.SignInDto;
import com.fundall.eWallet.dto.user.SignInResponseDto;
import com.fundall.eWallet.dto.user.SignUpDto;
import com.fundall.eWallet.dto.user.SignupResponseDto;
import com.fundall.eWallet.execptions.AuthenticationFailException;
import com.fundall.eWallet.execptions.CustomException;
import com.fundall.eWallet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public SignupResponseDto signup(@RequestBody SignUpDto signupDto) throws CustomException {
        return userService.signup(signupDto);
    }

    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) throws AuthenticationFailException, CustomException {
        return userService.signIn(signInDto);
    }
}
