package br.com.avaliacao.domains.lotacao;

import br.com.avaliacao.domains.lotacao.entitys.LotacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotacaoRepository extends JpaRepository<LotacaoEntity, Integer> {
}