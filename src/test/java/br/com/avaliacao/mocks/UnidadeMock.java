package br.com.avaliacao.mocks;

import br.com.avaliacao.domains.unidade.entitys.UnidadeEntity;
import br.com.avaliacao.domains.unidade.dtos.UnidadeRequest;
import br.com.avaliacao.domains.unidade.dtos.UnidadeResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UnidadeMock {

    public static UnidadeEntity unidadeDB(){
        return UnidadeEntity
                .builder()
                .nome("Unidade um")
                .sigla("UU")
                .build();
    }

    public static UnidadeEntity unidade(){
        return UnidadeEntity
                .builder()
                    .id(1)
                    .nome("Unidade um")
                    .sigla("UU")
                .build();
    }

    public static UnidadeResponse unidadeResponse(){
        return UnidadeResponse
                .builder()
                    .id(1)
                    .nome("Unidade um")
                    .sigla("UU")
                .build();
    }

    public static UnidadeRequest unidadeRequest(){
        return UnidadeRequest
                .builder()
                .nome("Unidade um")
                .sigla("UU")
                .build();
    }

}
