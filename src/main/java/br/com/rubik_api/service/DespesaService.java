package br.com.rubik_api.service;

import br.com.rubik_api.controller.dto.CreateDespesaDTO;
import br.com.rubik_api.entity.Despesa;
import br.com.rubik_api.repository.DespesaRepository;
import br.com.rubik_api.repository.ImovelRepository;
import br.com.rubik_api.repository.UserRepository;
import br.com.rubik_api.service.exception.DespesaNotFoundException;
import br.com.rubik_api.service.exception.ImovelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private ImovelRepository imovelRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Despesa> getDespesasByImovelCep(String cep) {
        return despesaRepository.findByImovel_Cep(cep)
                .orElseThrow(() -> new DespesaNotFoundException());
    }

    public int getParcelasByDespesa(UUID despesaId) {
        return despesaRepository.findById(despesaId)
                .orElseThrow(() -> new DespesaNotFoundException())
                .getParcelas();
    }

    public Despesa save(String cep, CreateDespesaDTO createDespesaDTO) {
        var imovel = imovelRepository.findImovelByCep(cep)
                .orElseThrow(() -> new ImovelNotFoundException());

        var despesa = new Despesa();

        despesa.setImovel(imovel);
        despesa.setTipo(createDespesaDTO.tipo());
        despesa.setValor(createDespesaDTO.valor());
        despesa.setVencimento(createDespesaDTO.vencimento());
        despesa.setParcelas(createDespesaDTO.parcelas());
        despesa.setStatus(Despesa.Status.valueOf(createDespesaDTO.status().toUpperCase()));

        return despesaRepository.save(despesa);
    }

    public List<Despesa> getUserDespesas(String email) {
        return despesaRepository.findByImovel_User_Email(email)
                .orElseThrow(() -> new DespesaNotFoundException());
    }
}