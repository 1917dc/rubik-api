package br.com.rubik_api.controller;

import br.com.rubik_api.controller.dto.CreateDespesaDTO;
import br.com.rubik_api.entity.Despesa;
import br.com.rubik_api.service.DespesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/despesa")
@Tag(name = "Despesa", description = "Operações relacionadas a despesas de imóveis")
@SecurityRequirement(name = "bearerAuth")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @Operation(summary = "Listar despesas por imóvel", description = "Retorna todas as despesas cadastradas para o imóvel com o CEP informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Despesas retornadas com sucesso", content = @Content(schema = @Schema(implementation = Despesa.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma despesa encontrada para o imóvel", content = @Content)
    })
    @GetMapping("/{cep}")
    public ResponseEntity<Object> getDespesasByImovel(
            @Parameter(description = "CEP do imóvel", required = true) @PathVariable String cep) {
        var despesas = despesaService.getDespesasByImovelCep(cep);

        if (despesas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma despesa encontrada para o imóvel com CEP: " + cep);
        }

        return ResponseEntity.ok().body(despesas);
    }

    @Operation(summary = "Listar parcelas de uma despesa", description = "Retorna todas as parcelas de uma despesa pelo ID da despesa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Número de parcelas retornado com sucesso", content = @Content(schema = @Schema(implementation = Integer.class))),
            @ApiResponse(responseCode = "404", description = "Despesa não encontrada", content = @Content)
    })
    @GetMapping("/parcelas/{despesaId}")
    public ResponseEntity<Object> getParcelasByDespesa(
            @Parameter(description = "ID da despesa", required = true) @PathVariable UUID despesaId) {
        var parcelas = despesaService.getParcelasByDespesa(despesaId);

        if (parcelas == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma parcela encontrada para a despesa com ID: " + despesaId);
        }

        return ResponseEntity.ok().body(parcelas);
    }

    @Operation(summary = "Cadastrar nova despesa", description = "Cadastra uma nova despesa para o imóvel com o CEP informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Despesa criada com sucesso", content = @Content(schema = @Schema(implementation = Despesa.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao criar despesa", content = @Content)
    })
    @PostMapping("/{cep}")
    public ResponseEntity<Object> newDespesa(
            @Parameter(description = "CEP do imóvel", required = true) @PathVariable String cep,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados para criação da despesa",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CreateDespesaDTO.class))
            )
            @RequestBody CreateDespesaDTO createDespesaDTO) {
        var despesa = despesaService.save(cep, createDespesaDTO);

        if(despesa == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar despesa para o imóvel com CEP: " + cep);
        }

        return ResponseEntity.ok().body(despesa);
    }

    @Operation(
            summary = "Listar despesas por usuário",
            description = "Retorna todas as despesas cadastradas para o usuário com o e-mail informado"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Despesas retornadas com sucesso",
                    content = @Content(schema = @Schema(implementation = Despesa.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Nenhuma despesa encontrada para o usuário",
                    content = @Content
            )
    })
    @GetMapping("/user/{email}")
    public ResponseEntity<Object> getUserDespesas(
            @Parameter(description = "E-mail do usuário", required = true)
            @PathVariable String email
    ) {
        var despesas = despesaService.getUserDespesas(email);

        if (despesas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhuma despesa encontrada para o usuário com email: " + email);
        }

        return ResponseEntity.ok().body(despesas);
    }
}