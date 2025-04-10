package br.com.avaliacao.domains.pessoa;

import br.com.avaliacao.config.AppConstantes;
import br.com.avaliacao.domains.pessoa.dtos.PessoaRequest;
import br.com.avaliacao.domains.pessoa.dtos.PessoaResponse;
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
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping
    public Page<PessoaResponse> getAll(@ParameterObject Pageable pageable) {
        return pessoaService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> getById(@PathVariable Integer id) {
        var pessoa = pessoaService.findById(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody PessoaRequest pessoaDTO) {
        var pessoaResponse = pessoaService.save(pessoaDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pessoaResponse.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody PessoaRequest pessoaUpdate) {
        pessoaService.update(id, pessoaUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pessoaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}