package br.com.avaliacao.domains.endereco;

import br.com.avaliacao.domains.endereco.cidade.CidadeMapper;
import br.com.avaliacao.domains.endereco.dtos.EnderecoResponse;
import br.com.avaliacao.domains.endereco.entitys.EnderecoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CidadeMapper.class})
public interface EnderecoMapper {

    EnderecoResponse toDTO(EnderecoEntity enderecoEntity);

    EnderecoEntity toEntity(EnderecoEntity enderecoEntity);

    EnderecoEntity toEntity(EnderecoRequest enderecoRequest);

}