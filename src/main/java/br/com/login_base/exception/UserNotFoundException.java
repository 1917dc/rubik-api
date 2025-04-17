package br.com.login_base.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException() {
        super("Usuário não encontrado");
    }
}
