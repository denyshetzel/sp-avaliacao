package br.com.avaliacao.domains.endereco;

import br.com.avaliacao.domains.endereco.dtos.EnderecoResponse;
import br.com.avaliacao.domains.endereco.entitys.EnderecoEntity;
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
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;

    @Transactional(readOnly = true)
    public Page<EnderecoResponse> findAll(Pageable pageable) {
        log.info("Iniciando findAll com pageable {}", pageable);
        var enderecos = enderecoRepository.findAll(pageable).map(enderecoMapper::toDTO);
        log.info("Finalizando findAll");
        return enderecos;
    }

    @Transactional(readOnly = true)
    public EnderecoResponse findById(Integer id) {
        log.info("Iniciando findById por id {}", id);
        var endereco = enderecoRepository
                .findById(id)
                .map(enderecoMapper::toDTO)
                .orElseThrow(() -> new NotFoundException(id));
        log.debug("Recurso encontrado: {}", endereco);
        log.info("Finalizando findById");
        return endereco;
    }

    @Transactional
    public EnderecoResponse save(EnderecoRequest enderecoRequestDTO) {
        log.info("Iniciando save {}", enderecoRequestDTO);
        var endereco = enderecoMapper.toEntity(enderecoRequestDTO);
        enderecoRepository.save(endereco);
        var enderecoResponseDTO = enderecoMapper.toDTO(endereco);
        log.info("Finalizando save");
        return enderecoResponseDTO;
    }

    @Transactional
    public void deleteById(Integer id) {
        log.info("Iniciando deleteById por id {}", id);
        var exists = enderecoRepository.existsById(id);
        if(!exists) throw new NotFoundException(id);
        enderecoRepository.deleteById(id);
        log.info("Finalizando deleteById");
    }

    @Transactional
    public void update(Integer id, EnderecoRequest enderecoRequestDTO) {
        log.info("Iniciando update por id {}, {}", id, enderecoRequestDTO);
        var endereco = enderecoRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        endereco.update(enderecoRequestDTO);
        log.info("Finalizando update");
    }

}