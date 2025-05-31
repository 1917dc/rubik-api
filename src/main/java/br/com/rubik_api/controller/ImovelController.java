package br.com.rubik_api.controller;

import br.com.rubik_api.controller.dto.CreateImovelDTO;
import br.com.rubik_api.controller.dto.EditImovelDTO;
import br.com.rubik_api.entity.User;
import br.com.rubik_api.entity.imovel.Imovel;
import br.com.rubik_api.service.ImovelService;
import br.com.rubik_api.service.UserService;
import br.com.rubik_api.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imovel")
public class ImovelController {

    @Autowired
    private ImovelService imovelService;
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity createImovel(@RequestBody CreateImovelDTO createImovelDTO) {
        var imovel = imovelService.save(createImovelDTO);

        if(imovel == null) {
            return ResponseEntity.badRequest().body("Erro ao cadastrar um imóvel");
        }

        return ResponseEntity.ok().body("Imóvel cadastrado com sucesso");
    }

    @DeleteMapping("/{cep}")
    public ResponseEntity deleteImovel(@PathVariable String cep) {
        imovelService.delete(cep);
        return ResponseEntity.ok().body("Imóvel deletado com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<Imovel>> findAll(@RequestBody String email) {
        return ResponseEntity.ok(imovelService.findAllByEmail(email));
    }

    @GetMapping("/{cep}")
    public ResponseEntity<Imovel> findImovelByCep(@PathVariable String cep) {
        Imovel imovel = imovelService.findImovelByCep(cep);
        if (imovel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(imovel);
    }

    @PutMapping
    public ResponseEntity<Object> updateImovel(@RequestBody EditImovelDTO editImovelDTO) {
        // caso o usuario nao cadastre o cep correto ele tera de cadastrar novamente o imovel
        Imovel imovel = imovelService.findImovelByCep(editImovelDTO.cep());

        if (imovel == null) {
            return ResponseEntity.badRequest().body("Imovel não encontrado");
        }

        imovelService.update(editImovelDTO);
        return ResponseEntity.ok().body("Imóvel atualizado com sucesso");
    }
}
