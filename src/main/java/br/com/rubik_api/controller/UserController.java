package br.com.rubik_api.controller;

import br.com.rubik_api.controller.dto.ReturnAccountDataDTO;
import br.com.rubik_api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "Usuário", description = "Operações relacionadas a usuários")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Obter dados do usuário",
            description = "Retorna os dados da conta do usuário pelo e-mail informado"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Dados do usuário retornados com sucesso",
                    content = @Content(schema = @Schema(implementation = ReturnAccountDataDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dados de usuário não encontrados",
                    content = @Content
            )
    })
    @GetMapping("/{email}/data")
    public ResponseEntity<Object> getData(
            @Parameter(description = "E-mail do usuário", required = true)
            @PathVariable String email
    ) {
        var data = userService.accountData(email);

        if (data == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dados de usuário não encontrados.");
        }

        return ResponseEntity.ok().body(data);
    }
}