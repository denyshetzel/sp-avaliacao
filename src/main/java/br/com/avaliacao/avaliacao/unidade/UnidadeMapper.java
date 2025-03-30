package br.com.avaliacao.avaliacao.unidade;

import org.mapstruct.Mapper;

@Mapper
public interface UnidadeMapper {

    UnidadeDTO toDTO(Unidade unidade);

    Unidade toEntity(UnidadeDTO unidadeDTO);

    Unidade toEntity(Unidade unidade, UnidadeDTO unidadeDTO);

}