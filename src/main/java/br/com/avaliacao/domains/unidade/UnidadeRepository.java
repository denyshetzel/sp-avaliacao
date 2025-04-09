package br.com.avaliacao.domains.unidade;

import br.com.avaliacao.domains.unidade.entitys.UnidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends JpaRepository<UnidadeEntity, Integer> {
}