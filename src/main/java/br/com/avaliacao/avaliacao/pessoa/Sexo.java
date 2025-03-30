package br.com.avaliacao.avaliacao.pessoa;

public enum Sexo {

    FEMININIO("Feminino"),
    MASCULINO("Masculino");

    private final String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }
    
}
