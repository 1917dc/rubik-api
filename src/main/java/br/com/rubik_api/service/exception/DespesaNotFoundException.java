package br.com.rubik_api.service.exception;

public class DespesaNotFoundException extends RuntimeException {
    public DespesaNotFoundException(String cep) {
        super("Imóvel não possui despesas cadastradas: " + cep);
    }
}
