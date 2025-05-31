package br.com.rubik_api.repository;

import br.com.rubik_api.entity.imovel.DespesaParcela;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DespesaParcelaRepository extends JpaRepository<DespesaParcela, UUID> {
    Optional<List<DespesaParcela>> findByDespesa_Id(UUID despesaId);
}
