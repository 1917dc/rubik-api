package br.com.rubik_api.controller.dto;

import java.time.LocalDate;

public record ReturnAccountDataDTO(
        String nome,
        int qtdImoveis,
        float valorDespesas,
        int qtdDespesasPendentes,
        LocalDate proximoVencimento
) {
}
