package br.com.rubik_api.service.exception;

public class DespesaNotFoundException extends RuntimeException {
    public DespesaNotFoundException() {
        super("Imóvel não possui despesas cadastradas");
    }
}
