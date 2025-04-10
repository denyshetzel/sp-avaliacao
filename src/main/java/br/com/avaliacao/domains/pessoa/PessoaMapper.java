package br.com.avaliacao.domains.pessoa;

import br.com.avaliacao.domains.pessoa.dtos.*;
import br.com.avaliacao.domains.pessoa.entitys.PessoaEntity;
import br.com.avaliacao.domains.pessoa.entitys.PessoaFotoEntity;
import br.com.avaliacao.domains.pessoa.entitys.ServidorEfetivoEntity;
import br.com.avaliacao.domains.pessoa.entitys.ServidorTemporarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

    default PessoaResponse toResponse(PessoaEntity pessoa){
        if(pessoa instanceof ServidorEfetivoEntity servidorEfetivoEntity){
            return toResponse(servidorEfetivoEntity);
        } else if (pessoa instanceof ServidorTemporarioEntity servidorTemporarioEntity) {
            return toResponse(servidorTemporarioEntity);
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

    ServidorEfetivoResponse toResponse(ServidorEfetivoEntity pessoa);

    ServidorTemporarioResponse toResponse(ServidorTemporarioEntity pessoa);

    ServidorEfetivoEntity toEntity(ServidorEfetivoRequest pessoa);

    ServidorTemporarioEntity toEntity(ServidorTemporarioRequest pessoa);

    PessoaFotoResponse toPessoaFotoResponse(PessoaFotoEntity pessoaFotoEntity);

    PessoaLotacaoResponse toPessoaLotacaoResponse(PessoaEntity pessoa);

    PessoaLotacaoEnderecoResponse toPessoaLotacaoEnderecoResponse(PessoaEntity pessoa);

}