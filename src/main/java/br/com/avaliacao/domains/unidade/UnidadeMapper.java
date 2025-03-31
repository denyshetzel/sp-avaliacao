package br.com.avaliacao.domains.unidade;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnidadeMapper {

    UnidadeDTO toDTO(Unidade unidade);

    Unidade toEntity(UnidadeDTO unidadeDTO);

}