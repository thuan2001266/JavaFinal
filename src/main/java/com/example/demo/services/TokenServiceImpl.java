package com.example.demo.services;

import com.example.demo.models.AppUser;
import com.example.demo.models.VerificationToken;
import com.example.demo.repositories.TokenRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TokenServiceImpl implements TokenService{

    @Autowired
    final TokenRepository tokenRepository;

    @Autowired
    final UserRepository userRepository;

    public TokenServiceImpl(TokenRepository tokenRepository, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    public VerificationToken createToken(String token, AppUser appUser) {
        VerificationToken verificationToken = new VerificationToken(token);
        verificationToken.setUser(appUser);
        return tokenRepository.save(verificationToken);
    }

    @Override
    public VerificationToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
