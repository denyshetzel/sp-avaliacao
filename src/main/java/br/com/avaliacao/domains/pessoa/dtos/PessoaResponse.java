package br.com.avaliacao.domains.pessoa.dtos;

import br.com.avaliacao.domains.endereco.dtos.EnderecoResponse;
import br.com.avaliacao.domains.lotacao.dtos.LotacaoResponse;
import br.com.avaliacao.domains.pessoa.entitys.Sexo;
import br.com.avaliacao.domains.pessoa.entitys.TipoServidor;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class PessoaResponse {

    private Integer id;

    private String nome;

    private LocalDate dataNascimento;

    private Sexo sexo;

    private String nomeMae;

    private String nomePai;

    public TipoServidor tipoServidor;

    private Set<PessoaFotoResponse> fotos;

    private EnderecoResponse endereco;

    private LotacaoResponse lotacao;

}
