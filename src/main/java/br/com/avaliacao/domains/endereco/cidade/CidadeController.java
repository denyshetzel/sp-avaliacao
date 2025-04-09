package br.com.avaliacao.domains.endereco.cidade;

import br.com.avaliacao.config.AppConstantes;
import br.com.avaliacao.domains.endereco.cidade.dtos.CidadeRequest;
import br.com.avaliacao.domains.endereco.cidade.dtos.CidadeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = AppConstantes.PATHS.V1.CIDADES)
public class CidadeController {

    private final CidadeService cidadeService;

    @GetMapping
    public Page<CidadeResponse> getAll(@ParameterObject Pageable pageable) {
        return cidadeService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CidadeResponse> getById(@PathVariable Integer id) {
        var cidadeResponse = cidadeService.findById(id);
        return ResponseEntity.ok(cidadeResponse);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CidadeRequest cidadeRequestDTO) {
        var cidadeResponse = cidadeService.save(cidadeRequestDTO);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cidadeResponse.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody CidadeRequest cidadeRequestUpdate) {
        cidadeService.update(id, cidadeRequestUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        cidadeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}