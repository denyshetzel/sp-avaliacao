package br.com.avaliacao.domains.pessoa;

import br.com.avaliacao.domains.pessoa.entitys.PessoaEntity;
import br.com.avaliacao.domains.pessoa.entitys.TipoServidor;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class PessoaSpecification {

    public static Specification<PessoaEntity> unidadeId(Integer unidadeId) {
        return (root, query, criteriaBuilder) -> {
            var lotacaoJoin = root.join("lotacao"); // Realiza o join com a entidade Lotacao
            var unidadeJoin = lotacaoJoin.join("unidade"); // Realiza o join com a entidade Unidade
            return criteriaBuilder.equal(unidadeJoin.get("id"), unidadeId);
        };
    }

    public static Specification<PessoaEntity> tipoServidor(TipoServidor tipoServidor) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("tipoServidor"), tipoServidor);
    }

}