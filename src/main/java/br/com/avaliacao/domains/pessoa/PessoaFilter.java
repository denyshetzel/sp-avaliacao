package br.com.avaliacao.domains.pessoa;

import br.com.avaliacao.domains.pessoa.entitys.TipoServidor;
import lombok.Data;

@Data
public class PessoaFilter {

    private Integer unidadeId;

    private TipoServidor tipoServidor;

    private String nomeServidor;
}
