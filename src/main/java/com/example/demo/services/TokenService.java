package com.example.demo.services;

import com.example.demo.models.AppUser;
import com.example.demo.models.VerificationToken;

public interface TokenService {
    VerificationToken createToken(String token, AppUser appUser);
    VerificationToken findByToken(String token);
}
