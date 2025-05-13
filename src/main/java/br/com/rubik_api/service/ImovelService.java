package br.com.rubik_api.service;

import br.com.rubik_api.entity.imovel.Imovel;
import br.com.rubik_api.repository.ImovelRepository;
import br.com.rubik_api.service.exception.ImovelAlreadyExists;
import br.com.rubik_api.service.exception.ImovelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    public Imovel findImovelByCep(String cep) {
        return imovelRepository.findImovelByCep(cep).orElseThrow(() -> new ImovelNotFoundException());
    }

    public Imovel save(Imovel imovel) {
        imovelRepository.findImovelByCep(imovel.getCep()).ifPresent(i -> {
            throw new ImovelAlreadyExists();
        });

        return imovelRepository.save(imovel);
    }
}
