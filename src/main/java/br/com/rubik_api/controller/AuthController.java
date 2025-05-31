package br.com.rubik_api.controller;

import java.time.Instant;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rubik_api.controller.dto.LoginDTO;
import br.com.rubik_api.controller.dto.RegisterDTO;
import br.com.rubik_api.entity.User;
import br.com.rubik_api.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private JwtEncoder jwtEncoder;
	
	@Autowired
	private UserService userService;
	
	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    @Autowired
    private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
		var user = userService.findByEmail(loginDTO.email());

	    if (passwordEncoder.matches(loginDTO.senha(), user.getSenha())) {
	        return ResponseEntity.ok(Collections.singletonMap("token", gerarAccessToken(user)));
	    }
	    

	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	            .body(Collections.singletonMap("mensagem", "Credenciais inválidas"));
	    
    }
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody RegisterDTO registerDTO) {
		var user = new User(registerDTO.email(), registerDTO.nome(), PASSWORD_ENCODER.encode(registerDTO.senha()));
		userService.save(user);
		
		return ResponseEntity.ok(Collections.singletonMap("mensagem", "Usuário cadastrado com sucesso"));
	}
	
	private String gerarAccessToken(User user) {
		var now = Instant.now();
		var expiresIn = 300L;
		
		var claims = JwtClaimsSet.builder()
				.issuer("login_base-api")
				.subject(user.getId().toString())
				.issuedAt(now)
				.expiresAt(now.plusSeconds(expiresIn))
				.build();
				
		var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
		
		return jwtValue;
	}
}
