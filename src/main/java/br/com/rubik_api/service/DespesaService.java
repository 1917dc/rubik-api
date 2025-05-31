package br.com.rubik_api.service;

import br.com.rubik_api.controller.dto.CreateImovelDespesaDTO;
import br.com.rubik_api.entity.imovel.DespesaParcela;
import br.com.rubik_api.entity.imovel.ImovelDespesa;
import br.com.rubik_api.repository.DespesaParcelaRepository;
import br.com.rubik_api.repository.ImovelDespesaRepository;
import br.com.rubik_api.repository.ImovelRepository;
import br.com.rubik_api.service.exception.DespesaNotFoundException;
import br.com.rubik_api.service.exception.ImovelNotFoundException;
import br.com.rubik_api.service.exception.ParcelaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DespesaService {

    @Autowired
    private ImovelDespesaRepository imovelDespesaRepository;

    @Autowired
    private DespesaParcelaRepository despesaParcelaRepository;

    @Autowired
    private ImovelRepository imovelRepository;

    public List<ImovelDespesa> getDespesasByImovelCep(String cep) {
        return imovelDespesaRepository.findByImovel_Cep(cep)
                .orElseThrow(() -> new DespesaNotFoundException(cep));
    }

    public List<DespesaParcela> getParcelasByDespesa(UUID despesaId) {
        return despesaParcelaRepository.findByDespesa_Id(despesaId)
                .orElseThrow(ParcelaNotFoundException::new);
    }

    public ImovelDespesa save(CreateImovelDespesaDTO createImovelDespesaDTO) {
        var imovel = imovelRepository.findImovelByCep(createImovelDespesaDTO.imovelCep())
                .orElseThrow(() -> new ImovelNotFoundException());

        var despesa = new ImovelDespesa();
        despesa.setImovel(imovel);
        despesa.setTipo(createImovelDespesaDTO.tipo());
        despesa.setValor(createImovelDespesaDTO.valor());
        despesa.setVencimento(java.sql.Date.valueOf(createImovelDespesaDTO.vencimento()));
        despesa.setStatus(ImovelDespesa.Status.valueOf(createImovelDespesaDTO.status().toUpperCase()));

        return imovelDespesaRepository.save(despesa);
    }
}