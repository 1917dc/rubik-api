package br.com.rubik_api.controller.dto;

import java.util.Date;

public record EditImovelDTO(
        String cep,
        String endereco,
        String cidade,
        String estado,
        String tipo,
        int qtdQuartos,
        int qtdBanheiro,
        int qtdVagasGaragem,
        Date dataAquisicao,
        String registroCartorio,
        String inscricaoIptu,
        String inscricaoCaesb,
        String inscricaoNeoenergia,
        float valorVenal
) {}
