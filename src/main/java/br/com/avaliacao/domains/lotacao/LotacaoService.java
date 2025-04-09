package br.com.avaliacao.domains.lotacao;

import br.com.avaliacao.domains.lotacao.dtos.LotacaoRequest;
import br.com.avaliacao.domains.lotacao.entitys.LotacaoEntity;
import br.com.avaliacao.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LotacaoService {

    private final LotacaoRepository lotacaoRepository;
    private final LotacaMapper lotacaMapper;

    @Transactional(readOnly = true)
    public Page<LotacaoRequest> findAll(Pageable pageable) {
        log.info("Iniciando findAll com pageable {}", pageable);
        var lotacoes = lotacaoRepository.findAll(pageable).map(lotacaMapper::toDTO);
        log.info("Finalizando findAll");
        return lotacoes;
    }

    @Transactional(readOnly = true)
    public LotacaoRequest findById(Integer id) {
        log.info("Iniciando findById por id {}", id);
        Optional<LotacaoEntity> lotacao = lotacaoRepository.findById(id);
        var lotacaoDTO = lotacao.map(lotacaMapper::toDTO)
                .orElseThrow(() -> new NotFoundException(id));
        log.debug("lotacao encontrada: {}", lotacaoDTO);
        log.info("Finalizando findById");
        return lotacaoDTO;
    }

    @Transactional
    public LotacaoRequest save(LotacaoRequest lotacaoDTO) {
        log.info("Iniciando save com lotacao {}", lotacaoDTO);
        var lotacao = lotacaMapper.toEntity(lotacaoDTO);
        lotacaoRepository.save(lotacao);
        lotacaoDTO = lotacaMapper.toDTO(lotacao);
        log.info("Finalizando save");
        return lotacaoDTO;
    }

    @Transactional
    public void deleteById(Integer id) {
        log.info("Iniciando deleteById por id {}", id);
        var exists = lotacaoRepository.existsById(id);
        if(!exists) throw new NotFoundException(id);
        lotacaoRepository.deleteById(id);
        log.info("Finalizando deleteById");
    }

    @Transactional
    public void update(Integer id, LotacaoRequest lotacaUpdate) {
        log.info("Iniciando update por id {}, {}", id, lotacaUpdate);
        var lotacao = lotacaoRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        lotacao.update(lotacaUpdate);
        log.info("Finalizando updateUnidade");
    }

}