package br.com.rubik_api.controller;

import br.com.rubik_api.controller.dto.CreateImovelDTO;
import br.com.rubik_api.controller.dto.EditImovelDTO;
import br.com.rubik_api.entity.imovel.Imovel;
import br.com.rubik_api.service.ImovelService;
import br.com.rubik_api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imovel")
@Tag(name = "Imóvel", description = "Operações relacionadas a imóveis")
public class ImovelController {

    @Autowired
    private ImovelService imovelService;
    @Autowired
    UserService userService;

    @Operation(summary = "Cadastrar imóvel", description = "Cadastra um novo imóvel com as informações fornecidas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imóvel cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar um imóvel")
    })
    @PostMapping
    public ResponseEntity createImovel(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados para cadastro do imóvel",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CreateImovelDTO.class))
            ) CreateImovelDTO createImovelDTO) {
        var imovel = imovelService.save(createImovelDTO);

        if(imovel == null) {
            return ResponseEntity.badRequest().body("Erro ao cadastrar um imóvel");
        }

        return ResponseEntity.ok().body("Imóvel cadastrado com sucesso");
    }

    @Operation(summary = "Deletar imóvel", description = "Remove um imóvel pelo CEP")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imóvel deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Imóvel não encontrado")
    })
    @DeleteMapping("/{cep}")
    public ResponseEntity deleteImovel(
            @Parameter(description = "CEP do imóvel a ser deletado", required = true)
            @PathVariable String cep) {
        imovelService.delete(cep);
        return ResponseEntity.ok().body("Imóvel deletado com sucesso");
    }

    @Operation(summary = "Listar imóveis do usuário", description = "Retorna todos os imóveis cadastrados para o e-mail informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de imóveis retornada com sucesso", content = @Content(schema = @Schema(implementation = Imovel.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping
    public ResponseEntity<List<Imovel>> findAll(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "E-mail do usuário",
                    required = true,
                    content = @Content(schema = @Schema(implementation = String.class))
            ) String email) {
        return ResponseEntity.ok(imovelService.findAllByEmail(email));
    }

    @Operation(summary = "Buscar imóvel por CEP", description = "Retorna as informações de um imóvel pelo CEP")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imóvel encontrado", content = @Content(schema = @Schema(implementation = Imovel.class))),
            @ApiResponse(responseCode = "404", description = "Imóvel não encontrado")
    })
    @GetMapping("/{cep}")
    public ResponseEntity<Imovel> findImovelByCep(
            @Parameter(description = "CEP do imóvel", required = true)
            @PathVariable String cep) {
        Imovel imovel = imovelService.findImovelByCep(cep);
        if (imovel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(imovel);
    }

    @Operation(summary = "Atualizar imóvel", description = "Atualiza as informações de um imóvel pelo CEP")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imóvel atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Imóvel não encontrado")
    })
    @PutMapping("/{cep}")
    public ResponseEntity<Object> updateImovel(
            @Parameter(description = "CEP do imóvel", required = true)
            @PathVariable String cep,
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados para atualização do imóvel",
                    required = true,
                    content = @Content(schema = @Schema(implementation = EditImovelDTO.class))
            ) EditImovelDTO editImovelDTO) {
        Imovel imovel = imovelService.findImovelByCep(cep);

        if (imovel == null) {
            return ResponseEntity.badRequest().body("Imovel não encontrado");
        }

        imovelService.update(cep, editImovelDTO);
        return ResponseEntity.ok().body("Imóvel atualizado com sucesso");
    }
}