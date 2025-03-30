package br.com.avaliacao.avaliacao.unidade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnidadeDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    private String nome;

    private String sigla;

}
