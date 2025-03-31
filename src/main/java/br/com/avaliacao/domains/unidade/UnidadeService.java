package br.com.avaliacao.domains.unidade;

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
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;
    private final UnidadeMapper unidadeMapper;

    @Transactional(readOnly = true)
    public Page<UnidadeDTO> findAll(Pageable pageable) {
        log.info("Iniciando findAll com pageable {}", pageable);
        var unidades = unidadeRepository.findAll(pageable).map(unidadeMapper::toDTO);
        log.info("Finalizando findAll");
        return unidades;
    }

    @Transactional(readOnly = true)
    public UnidadeDTO findById(Integer id) {
        log.info("Iniciando findById por id {}", id);
        Optional<Unidade> unidade = unidadeRepository.findById(id);

        UnidadeDTO unidadeDTO = unidade.map(unidadeMapper::toDTO)
                .orElseThrow(() -> new NotFoundException(id));
        log.debug("Unidade encontrada: {}", unidadeDTO);

        log.info("Iniciando findById");
        return unidadeDTO;
    }

    @Transactional
    public UnidadeDTO save(UnidadeDTO unidadeDTO) {
        log.info("Iniciando save com unidade {}", unidadeDTO);
        var unidade = unidadeMapper.toEntity(unidadeDTO);
        unidadeRepository.save(unidade);
        unidadeDTO = unidadeMapper.toDTO(unidade);
        log.info("Finalizando save");
        return unidadeDTO;
    }

    @Transactional
    public void deleteById(Integer id) {
        log.info("Iniciando deleteById por id {}", id);
        var exists = unidadeRepository.existsById(id);
        if(!exists) throw new NotFoundException(id);
        unidadeRepository.deleteById(id);
        log.info("Finalizando deleteById");
    }

    @Transactional
    public void updateUnidade(Integer id, UnidadeDTO unidadeUpdate) {
        log.info("Iniciando updateUnidade por id {}, {}", id, unidadeUpdate);
        Optional<Unidade> unidade = unidadeRepository.findById(id);
        if(unidade.isEmpty()) throw new NotFoundException(id);
        unidade.get().update(unidadeUpdate);
        log.info("Finalizando updateUnidade");
    }

}