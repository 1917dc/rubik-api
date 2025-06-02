package br.com.rubik_api.repository;

import br.com.rubik_api.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DespesaRepository extends JpaRepository<Despesa, UUID> {
    Optional<Despesa> findById(UUID id);
    Optional<List<Despesa>> findByImovel_User_Email(String email);
    Optional<List<Despesa>> findByImovel_Cep(String cep);
}
