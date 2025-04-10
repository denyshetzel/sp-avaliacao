package br.com.avaliacao.mocks;

import br.com.avaliacao.domains.pessoa.entitys.PessoaEntity;
import br.com.avaliacao.domains.pessoa.entitys.ServidorEfetivoEntity;
import br.com.avaliacao.domains.pessoa.entitys.Sexo;
import br.com.avaliacao.domains.pessoa.entitys.TipoServidor;

import java.time.LocalDate;

public class PessoaMock {

    public static PessoaEntity pessoaDB() {
        return ServidorEfetivoEntity
                .builder()
                .id(1)
                .tipoServidor(TipoServidor.EFETIVO)
                .matricula("123")
                .nomePai("Nome pai")
                .nomeMae("Nome mae")
                .sexo(Sexo.MASCULINO)
                .nome("João da Silva")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .build();
    }

    public static PessoaEntity pessoa() {
        return ServidorEfetivoEntity
                .builder()
                .tipoServidor(TipoServidor.EFETIVO)
                .matricula("123")
                .nome("Maria Oliveira")
                .nomePai("Nome pai")
                .sexo(Sexo.MASCULINO)
                .nomeMae("Nome mae")
                .dataNascimento(LocalDate.of(1985, 5, 15))
                .build();
    }

    public static PessoaEntity pessoaResponse() {
        return ServidorEfetivoEntity
                .builder()
                .tipoServidor(TipoServidor.EFETIVO)
                .matricula("123")
                .id(1)
                .nome("João da Silva")
                .nomePai("Nome pai")
                .sexo(Sexo.MASCULINO)
                .nomeMae("Nome mae")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .build();
    }

}