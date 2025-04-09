package br.com.avaliacao.domains.endereco.cidade;

import br.com.avaliacao.domains.endereco.cidade.dtos.CidadeRequest;
import br.com.avaliacao.domains.endereco.cidade.dtos.CidadeRequestId;
import br.com.avaliacao.domains.endereco.cidade.dtos.CidadeResponse;
import br.com.avaliacao.domains.endereco.cidade.entitys.CidadeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CidadeMapper {

    CidadeResponse toDTO(CidadeEntity cidadeEntity);

    CidadeEntity toEntity(CidadeRequest cidadeDTO);

    CidadeEntity toEntity(CidadeRequestId cidadeDTO);

}