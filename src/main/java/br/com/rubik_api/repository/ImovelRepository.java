package br.com.rubik_api.repository;

import br.com.rubik_api.entity.imovel.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {
    Optional<Imovel> findImovelByCep(String cep);
    Optional<List<Imovel>> findImovelByEmail(String email);
}
