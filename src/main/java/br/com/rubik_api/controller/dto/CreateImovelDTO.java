package br.com.rubik_api.controller.dto;

import java.util.Date;

public record CreateImovelDTO(
        String userEmail,
        String endereco,
        String cidade,
        String estado,
        String cep,
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
