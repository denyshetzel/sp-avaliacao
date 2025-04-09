package br.com.avaliacao.domains.pessoa.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServidorTemporarioResponse extends PessoaResponse {

    @NotNull
    private LocalDate dataAdmissao;

    @NotNull
    private LocalDate dataDemissao;

}
