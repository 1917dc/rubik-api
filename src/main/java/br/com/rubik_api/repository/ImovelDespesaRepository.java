package br.com.rubik_api.repository;

import br.com.rubik_api.entity.imovel.DespesaParcela;
import br.com.rubik_api.entity.imovel.ImovelDespesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImovelDespesaRepository extends JpaRepository<ImovelDespesa, UUID> {
    Optional<List<ImovelDespesa>> findByImovel_Cep(String cep);
}
