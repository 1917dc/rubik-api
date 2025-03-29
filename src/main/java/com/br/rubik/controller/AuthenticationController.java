package com.br.rubik.controller;

import com.br.rubik.dto.LoginDto;
import com.br.rubik.dto.RegisterDto;
import com.br.rubik.service.AuthenticationService;
import com.br.rubik.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation. PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto user) {
        try {
            if(userService.getUserByEmail(user.email()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado!");
            }
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao cadastrar usuário.");
        }

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto user) {
        return null;
    }
}
