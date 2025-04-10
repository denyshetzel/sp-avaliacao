package br.com.avaliacao.domains.pessoa;

import br.com.avaliacao.config.AppConstantes;
import br.com.avaliacao.domains.lotacao.dtos.LotacaoRequest;
import br.com.avaliacao.domains.pessoa.dtos.PessoaLotacaoEnderecoResponse;
import br.com.avaliacao.domains.pessoa.dtos.PessoaLotacaoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = AppConstantes.PATHS.V1.PESSOAS)
public class PessoaLotacaoController {

    private final PessoaService pessoaService;
    private final PessoaMapper pessoaMapper;

    @PostMapping("/{id}/lotacao")
    public ResponseEntity<Void> create(@PathVariable Integer id, @Valid @RequestBody LotacaoRequest lotacaoRequest) {
        var unidadeResponse = pessoaService.addLotacao(id, lotacaoRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(unidadeResponse.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}/lotacao")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pessoaService.removeLotacao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public Page<PessoaLotacaoResponse> getByFilter(@ParameterObject Pageable pageable, PessoaFilter pessoaFilter) {
        return pessoaService.findByFilter(pessoaFilter, pageable)
                .map(pessoaMapper::toPessoaLotacaoResponse);
    }

    @GetMapping("/endereco")
    public Page<PessoaLotacaoEnderecoResponse> getEnderecoByFilter(@ParameterObject Pageable pageable, PessoaFilter pessoaFilter) {
        return pessoaService.findByFilter(pessoaFilter, pageable)
                .map(pessoaMapper::toPessoaLotacaoEnderecoResponse);
    }

}