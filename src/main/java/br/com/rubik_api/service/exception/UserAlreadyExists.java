package br.com.rubik_api.service.exception;

public class UserAlreadyExists extends RuntimeException {
	public UserAlreadyExists() {
		super("Usuário já cadastrado");
	}

}
