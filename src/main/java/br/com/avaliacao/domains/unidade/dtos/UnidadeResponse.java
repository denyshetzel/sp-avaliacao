package br.com.avaliacao.domains.unidade.dtos;

import br.com.avaliacao.domains.endereco.dtos.EnderecoResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnidadeResponse {

    private Integer id;

    private String nome;

    private String sigla;

    private EnderecoResponse endereco;

}
