package br.com.avaliacao.domains.unidade.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnidadeRequest {

    @NotBlank
    @Size(min = 1, max = 200)
    private String nome;

    @NotBlank
    @Size(min = 1, max = 20)
    private String sigla;

}
