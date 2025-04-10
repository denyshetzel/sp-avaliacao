package br.com.avaliacao.domains.unidade;

import br.com.avaliacao.config.AppConstantes;
import br.com.avaliacao.domains.endereco.EnderecoRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = AppConstantes.PATHS.V1.UNIDADES)
public class UnidadeEnderecoController {

    private final UnidadeService unidadeService;

    @PostMapping("/{id}/endereco")
    public ResponseEntity<Void> create(@PathVariable Integer id, @Valid @RequestBody EnderecoRequest enderecoRequest) {
        var unidadeResponse = unidadeService.addEndereco(id, enderecoRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(unidadeResponse.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}/endereco")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        unidadeService.removeEndereco(id);
        return ResponseEntity.noContent().build();
    }

}