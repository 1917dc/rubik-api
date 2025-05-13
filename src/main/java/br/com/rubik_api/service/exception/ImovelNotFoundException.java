package br.com.rubik_api.service.exception;

public class ImovelNotFoundException extends RuntimeException {
    public ImovelNotFoundException() {
        super("Imóvel não encontrado");
    }
}
