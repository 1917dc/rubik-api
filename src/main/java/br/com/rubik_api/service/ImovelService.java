package br.com.rubik_api.service;

import br.com.rubik_api.controller.dto.CreateImovelDTO;
import br.com.rubik_api.controller.dto.EditImovelDTO;
import br.com.rubik_api.entity.User;
import br.com.rubik_api.entity.imovel.Imovel;
import br.com.rubik_api.repository.ImovelRepository;
import br.com.rubik_api.repository.UserRepository;
import br.com.rubik_api.service.exception.ImovelAlreadyExists;
import br.com.rubik_api.service.exception.ImovelNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private UserRepository userRepository;

    public Imovel findImovelByCep(String cep) {
        return imovelRepository.findImovelByCep(cep).orElseThrow(() -> new ImovelNotFoundException());
    }

    public List<Imovel> findAllByEmail(String email) {
        User user = userRepository.findByEmail(email).
                orElseThrow(() -> new EntityNotFoundException("Professor nao encontrado"));

        return user.getImoveis();
    }

    public Imovel save(CreateImovelDTO createImovelDTO) {
        imovelRepository.findImovelByCep(createImovelDTO.cep()).ifPresent(i -> {
            throw new ImovelAlreadyExists();
        });

        var imovel = new Imovel();
        imovel.setUser(userRepository.findByEmail(createImovelDTO.userEmail()).orElseThrow(() -> new ImovelNotFoundException()));
        imovel.setEndereco(createImovelDTO.endereco());
        imovel.setCidade(createImovelDTO.cidade());
        imovel.setEstado(createImovelDTO.estado());
        imovel.setCep(createImovelDTO.cep());
        imovel.setTipo(createImovelDTO.tipo());
        imovel.setQtdQuartos(createImovelDTO.qtdQuartos());
        imovel.setQtdBanheiro(createImovelDTO.qtdBanheiro());
        imovel.setQtdVagasGaragem(createImovelDTO.qtdVagasGaragem());
        imovel.setDataAquisicao(createImovelDTO.dataAquisicao());
        imovel.setRegistroCartorio(createImovelDTO.registroCartorio());
        imovel.setInscricaoIptu(createImovelDTO.inscricaoIptu());
        imovel.setInscricaoCaesb(createImovelDTO.inscricaoCaesb());
        imovel.setInscricaoNeoenergia(createImovelDTO.inscricaoNeoenergia());
        imovel.setValorVenal(createImovelDTO.valorVenal());

        return imovelRepository.save(imovel);
    }

    public void delete(String cep) {
        var imovel = imovelRepository.findImovelByCep(cep)
                .orElseThrow(() -> new ImovelNotFoundException());

        imovelRepository.delete(imovel);
    }

    public void update(EditImovelDTO editImovelDTO) {
        var imovel = imovelRepository.findImovelByCep(editImovelDTO.cep())
                .orElseThrow(() -> new ImovelNotFoundException());

        imovel.setEndereco(editImovelDTO.endereco());
        imovel.setCidade(editImovelDTO.cidade());
        imovel.setEstado(editImovelDTO.estado());
        imovel.setCep(editImovelDTO.cep());
        imovel.setTipo(editImovelDTO.tipo());
        imovel.setQtdQuartos(editImovelDTO.qtdQuartos());
        imovel.setQtdBanheiro(editImovelDTO.qtdBanheiro());
        imovel.setQtdVagasGaragem(editImovelDTO.qtdVagasGaragem());
        imovel.setDataAquisicao(editImovelDTO.dataAquisicao());
        imovel.setRegistroCartorio(editImovelDTO.registroCartorio());
        imovel.setInscricaoIptu(editImovelDTO.inscricaoIptu());
        imovel.setInscricaoCaesb(editImovelDTO.inscricaoCaesb());
        imovel.setInscricaoNeoenergia(editImovelDTO.inscricaoNeoenergia());
        imovel.setValorVenal(editImovelDTO.valorVenal());

        imovelRepository.save(imovel);
    }
}
