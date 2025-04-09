package br.com.avaliacao.domains.endereco.dtos;

import br.com.avaliacao.domains.endereco.cidade.dtos.CidadeResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoResponse {

    private Integer id;

    private String tipoLogradouro;

    private String logradouro;

    private Integer numero;

    private String bairro;

    private CidadeResponse cidade;

}
