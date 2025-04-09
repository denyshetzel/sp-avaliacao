package br.com.avaliacao.domains.pessoa;

import br.com.avaliacao.domains.pessoa.entitys.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {
}