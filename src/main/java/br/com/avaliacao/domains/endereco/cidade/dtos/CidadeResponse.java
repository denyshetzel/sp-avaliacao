package br.com.avaliacao.domains.endereco.cidade.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CidadeResponse {

    private Integer id;

    private String nome;

    private String uf;

}