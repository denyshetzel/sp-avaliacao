package br.com.avaliacao.mocks;

import br.com.avaliacao.domains.unidade.Unidade;
import br.com.avaliacao.domains.unidade.UnidadeDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UnidadeMock {

    public static Unidade unidadeDB(){
        return Unidade
                .builder()
                .nome("Unidade um")
                .sigla("UU")
                .build();
    }

    public static Unidade unidade(){
        return Unidade
                .builder()
                    .id(1)
                    .nome("Unidade um")
                    .sigla("UU")
                .build();
    }

    public static UnidadeDTO unidadeDTO(){
        return UnidadeDTO
                .builder()
                    .id(1)
                    .nome("Unidade um")
                    .sigla("UU")
                .build();
    }

}
