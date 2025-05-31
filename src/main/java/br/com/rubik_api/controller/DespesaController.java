package br.com.rubik_api.controller;

import br.com.rubik_api.controller.dto.CreateImovelDespesaDTO;
import br.com.rubik_api.entity.imovel.DespesaParcela;
import br.com.rubik_api.entity.imovel.ImovelDespesa;
import br.com.rubik_api.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/despesa")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @GetMapping("/{cep}")
    public ResponseEntity<Object> getDespesasByImovel(@PathVariable String cep) {
        var despesas = despesaService.getDespesasByImovelCep(cep);

        if (despesas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma despesa encontrada para o imóvel com CEP: " + cep);
        }

        return ResponseEntity.ok().body(despesas);
    }

    @GetMapping("/parcelas/{despesaId}")
    public ResponseEntity<Object> getParcelasByDespesa(@PathVariable UUID despesaId) {
        var parcelas = despesaService.getParcelasByDespesa(despesaId);

        if (parcelas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma parcela encontrada para a despesa com ID: " + despesaId);
        }

        return ResponseEntity.ok().body(parcelas);
    }

    @PostMapping("/{cep}")
    public ResponseEntity<Object> newDespesa(@PathVariable String cep, @RequestBody CreateImovelDespesaDTO createImovelDespesaDTO) {
        var despesa = despesaService.save(createImovelDespesaDTO);

        if(despesa == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar despesa para o imóvel com CEP: " + cep);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(despesa);
    }
}
