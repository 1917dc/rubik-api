package br.com.rubik_api.service.exception;

public class ParcelaNotFoundException extends RuntimeException {
    public ParcelaNotFoundException() {
        super("Não foi possível encontrar a parcela");
    }
}
