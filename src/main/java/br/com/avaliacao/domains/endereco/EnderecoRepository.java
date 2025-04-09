package br.com.avaliacao.domains.endereco;

import br.com.avaliacao.domains.endereco.entitys.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Integer> {
}