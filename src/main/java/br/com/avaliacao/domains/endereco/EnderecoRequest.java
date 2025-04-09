package br.com.avaliacao.domains.endereco;

import br.com.avaliacao.domains.endereco.cidade.dtos.CidadeRequestId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoRequest {

    @NotBlank
    @Size(min = 1, max = 200)
    private String tipoLogradouro;

    @NotBlank
    @Size(min = 1, max = 200)
    private String logradouro;

    @NotNull
    private Integer numero;

    @NotBlank
    @Size(min = 1, max = 100)
    private String bairro;

    @NotNull
    private CidadeRequestId cidade;

}
