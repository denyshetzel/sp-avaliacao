package br.com.avaliacao.domains.endereco;

import br.com.avaliacao.config.AppConstantes;
import br.com.avaliacao.domains.endereco.dtos.EnderecoResponse;
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
@RequestMapping(value = AppConstantes.PATHS.V1.ENDERECOS)
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping
    public Page<EnderecoResponse> getAll(@ParameterObject Pageable pageable) {
        return enderecoService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponse> getById(@PathVariable Integer id) {
        var enderecoResponse = enderecoService.findById(id);
        return ResponseEntity.ok(enderecoResponse);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody EnderecoRequest enderecoRequestDTO) {
        var enderecoResponse = enderecoService.save(enderecoRequestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(enderecoResponse.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody EnderecoRequest enderecoRequestDTO) {
        enderecoService.update(id, enderecoRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        enderecoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}