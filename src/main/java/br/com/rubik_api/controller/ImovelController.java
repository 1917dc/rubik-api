package br.com.rubik_api.controller;

import br.com.rubik_api.controller.dto.CreateImovelDTO;
import br.com.rubik_api.entity.User;
import br.com.rubik_api.entity.imovel.Imovel;
import br.com.rubik_api.repository.UserRepository;
import br.com.rubik_api.service.ImovelService;
import br.com.rubik_api.service.UserService;
import br.com.rubik_api.service.exception.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
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
            return ResponseEntity.badRequest().body("Erro ao cadastrar um imovel");
        }

        return ResponseEntity.ok(imovel);
    }

    @DeleteMapping
    public ResponseEntity deleteImovel(@RequestBody String cep) {
        imovelService.delete(cep);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Imovel>> findAll(@RequestBody String email) {
        return ResponseEntity.ok(imovelService.findAllByEmail(email));
    }
}
