package br.com.avaliacao.domains.pessoa.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServidorEfetivoRequest extends PessoaRequest {

    @NotBlank
    @Size(min = 1, max = 20)
    private String matricula;

}
