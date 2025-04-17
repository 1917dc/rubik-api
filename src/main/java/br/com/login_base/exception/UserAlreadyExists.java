package br.com.login_base.exception;

public class UserAlreadyExists extends RuntimeException {
	public UserAlreadyExists() {
		super("Usuário já cadastrado");
	}

}
