package br.com.avaliacao.domains.pessoa;

import br.com.avaliacao.domains.endereco.EnderecoMapper;
import br.com.avaliacao.domains.endereco.EnderecoRequest;
import br.com.avaliacao.domains.pessoa.dtos.PessoaFotoResponse;
import br.com.avaliacao.domains.pessoa.dtos.PessoaRequest;
import br.com.avaliacao.domains.pessoa.dtos.PessoaResponse;
import br.com.avaliacao.domains.pessoa.entitys.PessoaFotoEntity;
import br.com.avaliacao.exceptions.NotFoundException;
import br.com.avaliacao.infrastructure.MinioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final MinioService minioService;
    private final PessoaMapper pessoaMapper;
    private final EnderecoMapper enderecoMapper;

    @Transactional(readOnly = true)
    public Page<PessoaResponse> findAll(Pageable pageable) {
        log.info("Iniciando findAll com pageable {}", pageable);
        var pessoas = pessoaRepository.findAll(pageable).map(pessoaMapper::toDTO);
        log.info("Finalizando findAll");
        return pessoas;
    }

    @Transactional(readOnly = true)
    public PessoaResponse findById(Integer id) {
        log.info("Iniciando findById por id {}", id);
        PessoaResponse pessoa = pessoaRepository.findById(id)
                .map(pessoaMapper::toDTO)
                .orElseThrow(() -> new NotFoundException(id));
        log.debug("Pessoa encontrada: {}", pessoa);
        log.info("Finalizando findById");
        return pessoa;
    }

    @Transactional
    public PessoaResponse save(PessoaRequest pessoaRequest) {
        log.info("Iniciando save com pessoa {}", pessoaRequest);
        var pessoa = pessoaMapper.toEntity(pessoaRequest);
        pessoaRepository.save(pessoa);
        var pessoaResponse = pessoaMapper.toDTO(pessoa);
        log.info("Finalizando save");
        return pessoaResponse;
    }

    @Transactional
    public void deleteById(Integer id) {
        log.info("Iniciando deleteById por id {}", id);
        var exists = pessoaRepository.existsById(id);
        if(!exists) throw new NotFoundException(id);
        pessoaRepository.deleteById(id);
        log.info("Finalizando deleteById");
    }

    @Transactional
    public void update(Integer id, PessoaRequest pessoaUpdate) {
        log.info("Iniciando update por id {}, {}", id, pessoaUpdate);
        var pessoa = pessoaRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        pessoa.update(pessoaUpdate);
        pessoaRepository.save(pessoa);
        log.info("Finalizando update");
    }

    @SneakyThrows(Exception.class)
    @Transactional
    public void saveFoto(Integer id, String fileName, MultipartFile file) {
        log.info("Iniciando saveFoto por id {}", id);
        var pessoa = pessoaRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        var uploadFile = minioService.uploadFile(fileName, file);
        var pessoaFoto = PessoaFotoEntity
                .builder()
                    .pessoa(pessoa)
                    .bucket(uploadFile.bucket())
                    .data(LocalDate.now())
                    .hash(fileName)
                .build();
        pessoa.addFoto(pessoaFoto);
        pessoaRepository.save(pessoa);
        log.info("Finalizando saveFoto");
    }

    @SneakyThrows(Exception.class)
    @Transactional
    public void removeFoto(Integer pessoaId, Integer fotoId) {
        log.info("Iniciando removeFoto por id {}", pessoaId);
        var pessoa = pessoaRepository
                .findById(pessoaId)
                .orElseThrow(() -> new NotFoundException(pessoaId));
        var pessoaFoto = pessoa.removeFoto(fotoId);
        pessoaRepository.save(pessoa);
        minioService.deleteFile(pessoaFoto.getHash());
        log.info("Finalizando removeFoto");
    }

    @SneakyThrows(Exception.class)
    @Transactional(readOnly = true)
    public InputStream dowloadFoto(Integer pessoaId, String fileName) {
        log.info("Iniciando dowloadFoto por id {}, {}", pessoaId, fileName);
        pessoaRepository
                .findById(pessoaId)
                .orElseThrow(() -> new NotFoundException(pessoaId));
        var inputStream = minioService.downloadFile(fileName);
        log.info("Finalizando dowloadFoto");
        return inputStream;
    }

    @Transactional(readOnly = true)
    public List<PessoaFotoResponse> getAllFotos(Integer id) {
        log.info("Iniciando getAllFotos por id {}", id);
        var fotos = pessoaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id))
                .getFotos()
                .stream()
                .map(pessoaMapper::toPessoaFotoResponse)
                .toList();
        log.info("Finalizando getAllFotos");
        return fotos;
    }

    public PessoaResponse addEndereco(Integer id, @Valid EnderecoRequest enderecoRequest) {
        log.info("Iniciando addEndereco: {}", enderecoRequest);
        var pessoa = pessoaRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        var endereco = enderecoMapper.toEntity(enderecoRequest);
        pessoa.addEndereco(endereco);
        pessoaRepository.save(pessoa);
        var pessoaResponse = pessoaMapper.toDTO(pessoa);
        log.info("Finalizando addEndereco");
        return pessoaResponse;
    }

    public void removeEndereco(Integer id) {
        log.info("Iniciando removeEndereco: {}", id);
        var pessoa = pessoaRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        pessoa.removeEndereco();
        pessoaRepository.save(pessoa);
        log.info("Finalizando removeEndereco");
    }

}