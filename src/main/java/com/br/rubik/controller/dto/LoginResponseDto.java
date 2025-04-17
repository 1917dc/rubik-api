package com.br.rubik.controller.dto;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;

public record LoginResponseDto(JwtClaimsSet token, Long expiresIn) {
}
