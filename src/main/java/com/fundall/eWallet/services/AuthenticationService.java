package com.fundall.eWallet.services;

import com.fundall.eWallet.Config.MessageStrings;
import com.fundall.eWallet.execptions.AuthenticationFailException;
import com.fundall.eWallet.model.AuthenticationToken;
import com.fundall.eWallet.model.User;
import com.fundall.eWallet.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {
    private final TokenRepository tokenRepository;

    @Autowired
    public AuthenticationService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    // Save the confirmation token
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    // Get token of the user
    public AuthenticationToken getToken(User user) {
        return tokenRepository.findTokenByUser(user);
    }

    // Get user from the token
    public User getUser(String token) {
        AuthenticationToken authenticationToken = tokenRepository.findTokenByToken(token);
        if (Objects.nonNull(authenticationToken)) {
            if (Objects.nonNull(authenticationToken.getUser())) {
                return authenticationToken.getUser();
            }
        }
        return null;
    }

    // check if token is valid
    public void authenticate(String token) throws AuthenticationFailException {
        if (!Objects.nonNull(token)) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_PRESENT);
        }
        if (!Objects.nonNull(getUser(token))) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_VALID);
        }
    }

}
