package br.com.avaliacao.domains.pessoa.dtos;

import br.com.avaliacao.domains.pessoa.entitys.Sexo;
import br.com.avaliacao.domains.pessoa.entitys.TipoServidor;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Schema(
        type = "object",
        title = "PessoaRequestDTO",
        subTypes = {ServidorEfetivoRequest.class, ServidorTemporarioRequest.class}
)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "tipoServidor", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ServidorEfetivoRequest.class, name = "EFETIVO"),
        @JsonSubTypes.Type(value = ServidorTemporarioRequest.class, name = "TEMPORARIO")
})
@Data
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class PessoaRequest {

    @NotBlank
    @Size(min = 1, max = 200)
    private String nome;

    @NotNull
    private LocalDate dataNascimento;

    @NotNull
    private Sexo sexo;

    @NotBlank
    @Size(min = 1, max = 200)
    private String nomeMae;

    @NotBlank
    @Size(min = 1, max = 200)
    private String nomePai;

    @NotNull
    public TipoServidor tipoServidor;

}
