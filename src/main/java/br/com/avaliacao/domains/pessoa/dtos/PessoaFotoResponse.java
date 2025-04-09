package br.com.avaliacao.domains.pessoa.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PessoaFotoResponse {

    private Integer id;

    private LocalDate data;

    private String hash;

}
