package br.com.rubik_api.repository;

import br.com.rubik_api.entity.imovel.DespesaParcela;
import br.com.rubik_api.entity.imovel.Imovel;
import br.com.rubik_api.entity.imovel.ImovelDespesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {
    Optional<Imovel> findImovelByCep(String cep);
    Optional<List<Imovel>> findByUser_Email(String email);
}