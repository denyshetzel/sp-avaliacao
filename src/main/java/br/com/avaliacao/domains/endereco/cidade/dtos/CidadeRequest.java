package br.com.avaliacao.domains.endereco.cidade.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CidadeRequest {

    @NotBlank
    @Size(min = 1, max = 200)
    private String nome;

    @NotBlank
    @Size(min = 2, max = 2)
    private String uf;

}