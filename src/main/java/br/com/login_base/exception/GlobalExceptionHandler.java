package br.com.login_base.exception;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleUserNotFound(UserNotFoundException e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(Collections.singletonMap("mensagem", e.getMessage()));
	}

	@ExceptionHandler(UserAlreadyExists.class)
	public ResponseEntity<Map<String, String>> handleUserAlreadyExists(UserAlreadyExists e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("mensagem", e.getMessage()));
	}
}