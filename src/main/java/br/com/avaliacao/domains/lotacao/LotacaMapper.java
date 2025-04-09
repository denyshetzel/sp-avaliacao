package br.com.avaliacao.domains.lotacao;

import br.com.avaliacao.domains.lotacao.dtos.LotacaoRequest;
import br.com.avaliacao.domains.lotacao.entitys.LotacaoEntity;
import br.com.avaliacao.domains.pessoa.PessoaMapper;
import br.com.avaliacao.domains.unidade.UnidadeMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PessoaMapper.class, UnidadeMapper.class})
public interface LotacaMapper {

    LotacaoRequest toDTO(LotacaoEntity lotacao);

    LotacaoEntity toEntity(LotacaoRequest lotacaoDTO);

}