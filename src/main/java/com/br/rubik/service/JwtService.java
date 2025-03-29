package com.br.rubik.service;

import com.br.rubik.dto.LoginDto;
import com.br.rubik.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private final JwtEncoder encoder;

    public JwtService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(LoginDto user) {
        Instant now = Instant.now();
        long expiration = 3600L;

        var claims = JwtClaimsSet.builder()
                .issuer("rubik")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiration))
                .subject(user.getUsername())
                .build();
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
