package br.com.avaliacao.domains.unidade.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnidadeRequestID {

    @NotNull
    private Integer id;

}
