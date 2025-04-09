package br.com.avaliacao.domains.lotacao.dtos;

import br.com.avaliacao.domains.unidade.dtos.UnidadeResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class LotacaoResponse {

    private Integer id;

    private UnidadeResponse unidade;

    private LocalDate dataLotacao;

    private LocalDate dataRemocao;

    private Integer portaria;

}