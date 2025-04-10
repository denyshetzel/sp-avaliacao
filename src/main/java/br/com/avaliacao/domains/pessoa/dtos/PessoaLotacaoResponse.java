package br.com.avaliacao.domains.pessoa.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
public class PessoaLotacaoResponse {

    private Integer id;

    private String nome;

    @JsonIgnore
    private LocalDate dataNascimento;

    private Set<PessoaFotoResponse> fotos;

    public Integer getIdade(){
        if(Objects.isNull(dataNascimento)) return null;
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

}
