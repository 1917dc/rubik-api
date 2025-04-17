package com.br.rubik.controller;

import com.br.rubik.controller.dto.LoginResponseDto;
import com.br.rubik.controller.dto.LoginDto;
import com.br.rubik.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtEncoder jwtEncoder;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {
        var user = userRepository.findByEmail(loginDto.email());

        if(user.isEmpty() || !passwordEncoder.matches(loginDto.password(), user.get().getPassword())) {
            throw new BadCredentialsException("Usuário ou senha inválidos.");
        }

        var now = Instant.now();
        var expiresIn = 300L;

        var scopes = "ROLE_USER";

        var claims = JwtClaimsSet.builder()
                .issuer("rubik-api")
                .subject(user.get().getEmail())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDto(claims, expiresIn));
    }
}
