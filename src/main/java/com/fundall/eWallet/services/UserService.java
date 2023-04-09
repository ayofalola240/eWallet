package com.fundall.eWallet.services;

import com.fundall.eWallet.Config.MessageStrings;
import com.fundall.eWallet.dto.user.SignInDto;
import com.fundall.eWallet.dto.user.SignInResponseDto;
import com.fundall.eWallet.dto.user.SignUpDto;
import com.fundall.eWallet.dto.user.SignupResponseDto;
import com.fundall.eWallet.execptions.AuthenticationFailException;
import com.fundall.eWallet.execptions.CustomException;
import com.fundall.eWallet.model.AuthenticationToken;
import com.fundall.eWallet.model.User;
import com.fundall.eWallet.model.Wallet;
import com.fundall.eWallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final AuthenticationService authenticationService;

    @Autowired
    public UserService(UserRepository userRepository,  AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    public SignupResponseDto signup(SignUpDto signupDto) throws CustomException {

        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
            // throw exception if registered
            throw new CustomException("User already exists");
        }
        // encrypting the password
        String encryptedPassword = signupDto.getPassword();

        try {
            encryptedPassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("hashing password failed {}" + e.getMessage());
        }
        User user = new User(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(),signupDto.getPhoneNumber(), encryptedPassword);
        try {
            userRepository.save(user);
            // Create wallet
            Wallet wallet = new Wallet();
            wallet.setBalance(0.0);
            wallet.setUpdateDate(new Date());
            user.setWallet(wallet);
            wallet.setUser(user);
            // generate token for user
            final AuthenticationToken authenticationToken = new AuthenticationToken(user);
            // save token in database
            authenticationService.saveConfirmationToken(authenticationToken);
            return new SignupResponseDto("success", "user created successfully");
        } catch (Exception e) {
            // handle signup error
            throw new CustomException(e.getMessage());
        }
    }

    String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(password.getBytes());
        byte[] digest = md5.digest();
        String hashes = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hashes;
    }

    public SignInResponseDto signIn(SignInDto signInDto) throws AuthenticationFailException, CustomException {
        // find user by email
        User user = userRepository.findByEmail(signInDto.getEmail());
        if (!Objects.nonNull(user)) {
            throw new AuthenticationFailException("User does not exist");
        }
        // check if the password is right
        try {
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
                // password do not match
                throw new AuthenticationFailException(MessageStrings.WRONG_PASSWORD);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("hashing password failed {}" + e.getMessage());
            throw new CustomException(e.getMessage());
        }
        AuthenticationToken token = authenticationService.getToken(user);

        if (!Objects.nonNull(token)) {
            throw new CustomException(MessageStrings.AUTH_TOKEN_NOT_PRESENT);
        }

        return new SignInResponseDto("success", token.getToken());
    }
}
