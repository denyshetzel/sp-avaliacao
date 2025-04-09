package br.com.avaliacao.domains.lotacao.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class LotacaoRequest {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @NotNull
    private Integer pessoaId;

    @NotNull
    private Integer unidadeId;

    @NotNull
    private LocalDate dataLotacao;

    @NotNull
    private LocalDate dataRemocao;

    @NotNull
    private Integer portaria;

}