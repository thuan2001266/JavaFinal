package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.models.VerificationToken;

public interface TokenService {
    VerificationToken createToken(String token, User user);
    VerificationToken findByToken(String token);
}
