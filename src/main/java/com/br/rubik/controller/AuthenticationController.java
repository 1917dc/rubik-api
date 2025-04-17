package com.br.rubik.controller;

import com.br.rubik.model.User;
import com.br.rubik.model.dto.LoginDto;
import com.br.rubik.model.dto.RegisterDto;
import com.br.rubik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDto registerDto) {
        if(userService.getUserByEmail(registerDto.email()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(registerDto);
        } else {
            User user = new User();
            user.setEmail(registerDto.email());
            user.setPassword(passwordEncoder.encode(registerDto.password()));
            user.setFirstName(registerDto.firstName());
            user.setLastName(registerDto.lastName());
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {
        if(userService.getUserByEmail(loginDto.email()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(loginDto);
        }

        if(!passwordEncoder.matches(loginDto.password(), userService.getUserByEmail(loginDto.email()).get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginDto);
        }

        // TODO: implementar retorno de tokens JWT
        return null;
    }
}
