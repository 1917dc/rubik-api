package br.com.rubik_api.service.exception;

public class ImovelAlreadyExists extends RuntimeException {
    public ImovelAlreadyExists() {
        super("Imóvel já existe.");
    }
}
