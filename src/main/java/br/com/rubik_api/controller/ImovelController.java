package br.com.rubik_api.controller;

import br.com.rubik_api.controller.dto.CreateImovelDTO;
import br.com.rubik_api.entity.imovel.Imovel;
import br.com.rubik_api.service.ImovelService;
import br.com.rubik_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imovel")
public class ImovelController {

    @Autowired
    private ImovelService imovelService;
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity createImovel(@RequestBody CreateImovelDTO createImovelDTO) {
        try{
            var imovel = new Imovel();
            var user = userService.findByUsername(createImovelDTO.userEmail());
            imovel.setUser(user);
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

        } catch (Exception e){
            e.printStackTrace();
            ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
