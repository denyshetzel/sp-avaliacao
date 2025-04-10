package br.com.avaliacao.domains.pessoa.dtos;

import br.com.avaliacao.domains.endereco.dtos.EnderecoResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PessoaLotacaoEnderecoResponse {

    private EnderecoResponse endereco;

}
