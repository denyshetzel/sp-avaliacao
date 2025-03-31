package br.com.avaliacao.domains.unidade;

import br.com.avaliacao.config.AppConstantes;
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
    public Page<UnidadeDTO> getAllUnidades(@ParameterObject Pageable pageable) {
        return unidadeService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeDTO> getUnidadeById(@PathVariable Integer id) {
        var unidade = unidadeService.findById(id);
        return ResponseEntity.ok(unidade);
    }

    @PostMapping
    public ResponseEntity<Void> createUnidade(@Valid @RequestBody UnidadeDTO unidadeDTO) {
        var unidadeResponse = unidadeService.save(unidadeDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(unidadeResponse.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUnidade(@PathVariable Integer id, @Valid @RequestBody UnidadeDTO unidadeUpdate) {
        unidadeService.updateUnidade(id, unidadeUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnidade(@PathVariable Integer id) {
        unidadeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}