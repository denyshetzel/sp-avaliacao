package br.com.avaliacao.domains.pessoa.entitys;

import lombok.Getter;

public enum Sexo {

    FEMININIO("Feminino"),
    MASCULINO("Masculino");

    @Getter
    private final String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }
    
}
