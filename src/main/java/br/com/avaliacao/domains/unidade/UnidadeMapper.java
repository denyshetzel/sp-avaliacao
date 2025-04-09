package br.com.avaliacao.domains.unidade;

import br.com.avaliacao.domains.endereco.EnderecoMapper;
import br.com.avaliacao.domains.unidade.dtos.UnidadeRequest;
import br.com.avaliacao.domains.unidade.dtos.UnidadeResponse;
import br.com.avaliacao.domains.unidade.entitys.UnidadeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EnderecoMapper.class})
public interface UnidadeMapper {

    UnidadeResponse toDTO(UnidadeEntity unidade);

    UnidadeEntity toEntity(UnidadeRequest unidade);

}