package com.br.rubik.service;

import com.br.rubik.dto.LoginDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JwtService jwtService;

    public AuthenticationService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String authenticate(LoginDto user) {
        return jwtService.generateToken(user);
    }
}
