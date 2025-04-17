package com.br.rubik.controller;

import com.br.rubik.controller.dto.RegisterDto;
import com.br.rubik.model.User;
import com.br.rubik.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @PostMapping("/users")
    public ResponseEntity<Void> register(@RequestBody RegisterDto registerDto) {
        if(userRepository.findByEmail(registerDto.email()).isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            User user = new User();
            user.setEmail(registerDto.email());
            user.setPassword(passwordEncoder.encode(registerDto.password()));
            user.setFirstName(registerDto.firstName());
            user.setLastName(registerDto.lastName());
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
