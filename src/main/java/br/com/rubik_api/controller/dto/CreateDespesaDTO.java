package br.com.rubik_api.controller.dto;

import java.time.LocalDate;

public record CreateDespesaDTO(
        String tipo,
        float valor,
        int parcelas,
        LocalDate vencimento,
        String status
) {}

