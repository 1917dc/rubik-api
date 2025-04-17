package br.com.rubik_api.service.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException() {
        super("Usuário não encontrado");
    }
}
