package br.com.avaliacao.domains.pessoa;

import br.com.avaliacao.domains.pessoa.dtos.*;
import br.com.avaliacao.domains.pessoa.entitys.PessoaEntity;
import br.com.avaliacao.domains.pessoa.entitys.PessoaFotoEntity;
import br.com.avaliacao.domains.pessoa.entitys.ServidorEfetivoEntity;
import br.com.avaliacao.domains.pessoa.entitys.ServidorTemporarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PessoaMapper.class})
public interface PessoaMapper {

    default PessoaResponse toDTO(PessoaEntity pessoa){
        if(pessoa instanceof ServidorEfetivoEntity servidorEfetivoEntity){
            return toDTO(servidorEfetivoEntity);
        } else if (pessoa instanceof ServidorTemporarioEntity servidorTemporarioEntity) {
            return toDTO(servidorTemporarioEntity);
        }
        throw new IllegalAccessError("Tipo de pessoa inválido.");
    }

    default PessoaEntity toEntity(PessoaRequest pessoaDTO){
        if(pessoaDTO instanceof ServidorEfetivoRequest servidorEfetivoDTO){
            return toEntity(servidorEfetivoDTO);
        } else if (pessoaDTO instanceof ServidorTemporarioRequest servidorTemporarioDTO) {
            return toEntity(servidorTemporarioDTO);
        }
        throw new IllegalAccessError("Tipo de pessoa inválido.");
    }

    ServidorEfetivoResponse toDTO(ServidorEfetivoEntity pessoa);

    ServidorTemporarioResponse toDTO(ServidorTemporarioEntity pessoa);

    ServidorEfetivoEntity toEntity(ServidorEfetivoRequest pessoa);

    ServidorTemporarioEntity toEntity(ServidorTemporarioRequest pessoa);

    PessoaFotoResponse toPessoaFotoResponse(PessoaFotoEntity pessoaFotoEntity);

}