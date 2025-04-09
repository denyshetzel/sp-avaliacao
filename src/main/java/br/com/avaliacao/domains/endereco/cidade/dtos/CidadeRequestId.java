package br.com.avaliacao.domains.endereco.cidade.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CidadeRequestId {

    @NotNull
    private Integer id;

}