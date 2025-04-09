package br.com.avaliacao.domains.unidade;

import br.com.avaliacao.domains.endereco.EnderecoMapper;
import br.com.avaliacao.domains.endereco.EnderecoRequest;
import br.com.avaliacao.domains.unidade.dtos.UnidadeRequest;
import br.com.avaliacao.domains.unidade.dtos.UnidadeResponse;
import br.com.avaliacao.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;
    private final UnidadeMapper unidadeMapper;
    private final EnderecoMapper enderecoMapper;

    @Transactional(readOnly = true)
    public Page<UnidadeResponse> findAll(Pageable pageable) {
        log.info("Iniciando findAll com pageable: {}", pageable);
        var unidades = unidadeRepository.findAll(pageable).map(unidadeMapper::toDTO);
        log.info("Finalizando findAll");
        return unidades;
    }

    @Transactional(readOnly = true)
    public UnidadeResponse findById(Integer id) {
        log.info("Iniciando findById por id: {}", id);
        var unidade = unidadeRepository
                .findById(id)
                .map(unidadeMapper::toDTO)
                .orElseThrow(() -> new NotFoundException(id));
        log.debug("Recurso encontrado: {}", unidade);
        log.info("Finalizando findById");
        return unidade;
    }

    @Transactional
    public UnidadeResponse save(UnidadeRequest unidadeRequest) {
        log.info("Iniciando save: {}", unidadeRequest);
        var unidade = unidadeMapper.toEntity(unidadeRequest);
        unidadeRepository.save(unidade);
        var unidadeResponse = unidadeMapper.toDTO(unidade);
        log.info("Finalizando save");
        return unidadeResponse;
    }

    @Transactional
    public void deleteById(Integer id) {
        log.info("Iniciando deleteById por id: {}", id);
        var exists = unidadeRepository.existsById(id);
        if(!exists) throw new NotFoundException(id);
        unidadeRepository.deleteById(id);
        log.info("Finalizando deleteById");
    }

    @Transactional
    public void updateUnidade(Integer id, UnidadeRequest unidadeUpdate) {
        log.info("Iniciando updateUnidade por id: {}, {}", id, unidadeUpdate);
        var unidade = unidadeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        unidade.update(unidadeUpdate);
        log.info("Finalizando updateUnidade");
    }

    public UnidadeResponse addEndereco(Integer id, @Valid EnderecoRequest enderecoRequest) {
        log.info("Iniciando addEndereco: {}", enderecoRequest);
        var unidade = unidadeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        var endereco = enderecoMapper.toEntity(enderecoRequest);
        unidade.addEndereco(endereco);
        unidadeRepository.save(unidade);
        var unidadeResponse = unidadeMapper.toDTO(unidade);
        log.info("Finalizando addEndereco");
        return unidadeResponse;
    }

    public void removeEndereco(Integer id) {
        log.info("Iniciando removeEndereco: {}", id);
        var unidade = unidadeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        unidade.removeEndereco();
        unidadeRepository.save(unidade);
        log.info("Finalizando removeEndereco");
    }

}