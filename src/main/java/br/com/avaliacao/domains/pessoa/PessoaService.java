package br.com.avaliacao.domains.pessoa;

import br.com.avaliacao.domains.endereco.EnderecoMapper;
import br.com.avaliacao.domains.endereco.EnderecoRequest;
import br.com.avaliacao.domains.lotacao.LotacaoMapper;
import br.com.avaliacao.domains.lotacao.dtos.LotacaoRequest;
import br.com.avaliacao.domains.pessoa.dtos.PessoaFotoResponse;
import br.com.avaliacao.domains.pessoa.dtos.PessoaRequest;
import br.com.avaliacao.domains.pessoa.dtos.PessoaResponse;
import br.com.avaliacao.domains.pessoa.entitys.PessoaEntity;
import br.com.avaliacao.domains.pessoa.entitys.PessoaFotoEntity;
import br.com.avaliacao.domains.pessoa.entitys.TipoServidor;
import br.com.avaliacao.exceptions.NotFoundException;
import br.com.avaliacao.infrastructure.MinioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final MinioService minioService;
    private final PessoaMapper pessoaMapper;
    private final EnderecoMapper enderecoMapper;
    private final LotacaoMapper lotacaoMapper;

    @Transactional(readOnly = true)
    public Page<PessoaResponse> findAll(Pageable pageable) {
        log.info("Iniciando findAll com pageable {}", pageable);
        var pessoas = pessoaRepository
                .findAll(pageable)
                .map(pessoaMapper::toDTO);
        log.info("Finalizando findAll");
        return pessoas;
    }

    @Transactional(readOnly = true)
    public Page<PessoaResponse> findByFilter(PessoaFilter pessoaFilter, Pageable pageable) {
        log.info("Iniciando findByFilter com pageable {}", pageable);
        Specification<PessoaEntity> spec = Specification.where(null);

        if (Objects.nonNull(pessoaFilter.getUnidadeId())) {
            spec = spec.and(PessoaSpecification.unidadeId(pessoaFilter.getUnidadeId()));
        }
        if (Objects.nonNull(pessoaFilter.getTipoServidor())) {
            spec = spec.and(PessoaSpecification.tipoServidor(pessoaFilter.getTipoServidor()));
        }

        var pessoas = pessoaRepository.findAll(spec, pageable)
                .map(pessoaMapper::toDTO);
        log.info("Finalizando findByFilter");
        return pessoas;
    }

    @Transactional(readOnly = true)
    public PessoaResponse findById(Integer id) {
        log.info("Iniciando findById por id {}", id);
        PessoaResponse pessoa = pessoaRepository
                .findById(id)
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
        var pessoa = findByIdOrExeception(id);
        pessoa.update(pessoaUpdate);
        pessoaRepository.save(pessoa);
        log.info("Finalizando update");
    }

    @SneakyThrows(Exception.class)
    @Transactional
    public void saveFoto(Integer id, MultipartFile file) {
        log.info("Iniciando saveFoto por id {}", id);
        var pessoa = findByIdOrExeception(id);
        String fileName = file.getOriginalFilename();
        String newFileName = UUID.randomUUID() + "." + StringUtils.substringAfterLast(fileName, ".");
        var uploadFile = minioService.uploadFile(newFileName, file);
        var pessoaFoto = PessoaFotoEntity
                .builder()
                    .pessoa(pessoa)
                    .bucket(uploadFile.bucket())
                    .data(LocalDate.now())
                    .hash(newFileName)
                .build();
        pessoa.addFoto(pessoaFoto);
        pessoaRepository.save(pessoa);
        log.info("Finalizando saveFoto");
    }

    @SneakyThrows(Exception.class)
    @Transactional
    public void removeFoto(Integer pessoaId, Integer fotoId) {
        log.info("Iniciando removeFoto por id {}", pessoaId);
        var pessoa = findByIdOrExeception(pessoaId);
        var pessoaFoto = pessoa.removeFoto(fotoId);
        pessoaRepository.save(pessoa);
        minioService.deleteFile(pessoaFoto.getHash());
        log.info("Finalizando removeFoto");
    }

    @SneakyThrows(Exception.class)
    @Transactional(readOnly = true)
    public String generateUrlFoto(Integer pessoaId, String fileName) {
        log.info("Iniciando generateUrlFoto por id {}, {}", pessoaId, fileName);
        findByIdOrExeception(pessoaId);
        var url = minioService.generateUrl(fileName);
        log.info("Finalizando generateUrlFoto");
        return url;
    }

    @Transactional(readOnly = true)
    public List<PessoaFotoResponse> getAllFotos(Integer id) {
        log.info("Iniciando getAllFotos por id {}", id);
        var fotos = findByIdOrExeception(id)
                .getFotos()
                .stream()
                .map(pessoaMapper::toPessoaFotoResponse)
                .toList();
        log.info("Finalizando getAllFotos");
        return fotos;
    }

    @Transactional
    public PessoaResponse addEndereco(Integer id, @Valid EnderecoRequest enderecoRequest) {
        log.info("Iniciando addEndereco: {}", enderecoRequest);
        var pessoa = findByIdOrExeception(id);
        var endereco = enderecoMapper.toEntity(enderecoRequest);
        pessoa.addEndereco(endereco);
        pessoaRepository.save(pessoa);
        var pessoaResponse = pessoaMapper.toDTO(pessoa);
        log.info("Finalizando addEndereco");
        return pessoaResponse;
    }

    @Transactional
    public void removeEndereco(Integer id) {
        log.info("Iniciando removeEndereco: {}", id);
        var pessoa = findByIdOrExeception(id);
        pessoa.removeEndereco();
        pessoaRepository.save(pessoa);
        log.info("Finalizando removeEndereco");
    }

    @Transactional
    public PessoaResponse addLotacao(Integer id, @Valid LotacaoRequest lotacaoRequest) {
        log.info("Iniciando addLotacao: {}", lotacaoRequest);
        var pessoa = findByIdOrExeception(id);
        var lotacao = lotacaoMapper.toEntity(lotacaoRequest);
        pessoa.addLotacao(lotacao);
        pessoaRepository.save(pessoa);
        var pessoaResponse = pessoaMapper.toDTO(pessoa);
        log.info("Finalizando addLotacao");
        return pessoaResponse;
    }

    @Transactional
    public void removeLotacao(Integer id) {
        log.info("Iniciando removeLotacao: {}", id);
        var pessoa = findByIdOrExeception(id);
        pessoa.removeLotacao();
        pessoaRepository.save(pessoa);
        log.info("Finalizando removeLotacao");
    }

    private PessoaEntity findByIdOrExeception(Integer id) {
        return pessoaRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

}