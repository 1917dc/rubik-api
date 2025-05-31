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
import org.springframework.web.bind.annotation.*;

import br.com.rubik_api.controller.dto.LoginDTO;
import br.com.rubik_api.controller.dto.RegisterDTO;
import br.com.rubik_api.entity.User;
import br.com.rubik_api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Endpoints de autenticação e registro")
public class AuthController {
	@Autowired
	private JwtEncoder jwtEncoder;

	@Autowired
	private UserService userService;

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Operation(summary = "Login", description = "Autentica o usuário e retorna um token JWT")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Login realizado com sucesso, JWT retornado"),
			@ApiResponse(responseCode = "401", description = "Credenciais inválidas")
	})
	@PostMapping("/login")
	public ResponseEntity login(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(
					description = "Credenciais de login",
					required = true,
					content = @Content(schema = @Schema(implementation = LoginDTO.class))
			)
			@RequestBody LoginDTO loginDTO) {
		var user = userService.findByEmail(loginDTO.email());

		if (passwordEncoder.matches(loginDTO.senha(), user.getSenha())) {
			return ResponseEntity.ok(Collections.singletonMap("token", gerarAccessToken(user)));
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(Collections.singletonMap("mensagem", "Credenciais inválidas"));
	}

	@Operation(summary = "Registrar", description = "Registra um novo usuário")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados de registro inválidos")
	})
	@PostMapping("/register")
	public ResponseEntity register(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(
					description = "Dados para registro",
					required = true,
					content = @Content(schema = @Schema(implementation = RegisterDTO.class))
			)
			@RequestBody RegisterDTO registerDTO) {
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