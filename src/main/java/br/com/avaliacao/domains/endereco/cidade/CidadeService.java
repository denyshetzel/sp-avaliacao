package br.com.avaliacao.domains.endereco.cidade;

import br.com.avaliacao.domains.endereco.cidade.dtos.CidadeRequest;
import br.com.avaliacao.domains.endereco.cidade.dtos.CidadeResponse;
import br.com.avaliacao.domains.endereco.cidade.entitys.CidadeEntity;
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
public class CidadeService {

    private final CidadeRepository cidadeRepository;
    private final CidadeMapper cidadeMapper;

    @Transactional(readOnly = true)
    public Page<CidadeResponse> findAll(Pageable pageable) {
        log.info("Iniciando findAll com pageable {}", pageable);
        var cidades = cidadeRepository
                .findAll(pageable)
                .map(cidadeMapper::toDTO);
        log.info("Finalizando findAll");
        return cidades;
    }

    @Transactional(readOnly = true)
    public CidadeResponse findById(Integer id) {
        log.info("Iniciando findById por id {}", id);
        var cidade = cidadeRepository
                .findById(id)
                .map(cidadeMapper::toDTO)
                .orElseThrow(() -> new NotFoundException(id));
        log.debug("Recurso encontrado: {}", cidade);
        log.info("Finalizando findById");
        return cidade;
    }

    @Transactional
    public CidadeResponse save(CidadeRequest cidadeRequestDTO) {
        log.info("Iniciando save {}", cidadeRequestDTO);
        var cidade = cidadeMapper.toEntity(cidadeRequestDTO);
        cidadeRepository.save(cidade);
        var cidadeResponseDTO = cidadeMapper.toDTO(cidade);
        log.info("Finalizando save");
        return cidadeResponseDTO;
    }

    @Transactional
    public void deleteById(Integer id) {
        log.info("Iniciando deleteById por id {}", id);
        var exists = cidadeRepository.existsById(id);
        if(!exists) throw new NotFoundException(id);
        cidadeRepository.deleteById(id);
        log.info("Finalizando deleteById");
    }

    @Transactional
    public void update(Integer id, CidadeRequest cidadeUpdate) {
        log.info("Iniciando update por id {}, {}", id, cidadeUpdate);
        var cidade = cidadeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        cidade.update(cidadeUpdate);
        log.info("Finalizando update");
    }

}