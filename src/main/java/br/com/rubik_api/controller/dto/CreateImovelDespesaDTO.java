package br.com.rubik_api.controller.dto;

import java.time.LocalDate;

public record CreateImovelDespesaDTO(
        String imovelCep,
        String tipo,
        float valor,
        LocalDate vencimento,
        String status
) {}

