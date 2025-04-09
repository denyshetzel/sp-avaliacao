package br.com.avaliacao.domains.unidade;

import br.com.avaliacao.config.AppConstantes;
import br.com.avaliacao.domains.endereco.EnderecoRequest;
import br.com.avaliacao.domains.unidade.dtos.UnidadeRequest;
import br.com.avaliacao.domains.unidade.dtos.UnidadeResponse;
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
@RequestMapping(value = AppConstantes.PATHS.V1.UNIDADES)
public class UnidadeController {

    private final UnidadeService unidadeService;

    @GetMapping
    public Page<UnidadeResponse> getAll(@ParameterObject Pageable pageable) {
        return unidadeService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeResponse> getById(@PathVariable Integer id) {
        var unidade = unidadeService.findById(id);
        return ResponseEntity.ok(unidade);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody UnidadeRequest unidadeRequest) {
        var unidadeResponse = unidadeService.save(unidadeRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(unidadeResponse.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody UnidadeRequest unidadeRequest) {
        unidadeService.updateUnidade(id, unidadeRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        unidadeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/endereco")
    public ResponseEntity<Void> addEndereco(@PathVariable Integer id, @Valid @RequestBody EnderecoRequest enderecoRequest) {
        var unidadeResponse = unidadeService.addEndereco(id, enderecoRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(unidadeResponse.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}/endereco")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Integer id) {
        unidadeService.removeEndereco(id);
        return ResponseEntity.noContent().build();
    }

}