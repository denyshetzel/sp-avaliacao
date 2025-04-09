package br.com.avaliacao.domains.endereco.cidade;

import br.com.avaliacao.domains.endereco.cidade.entitys.CidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeEntity, Integer> {
}