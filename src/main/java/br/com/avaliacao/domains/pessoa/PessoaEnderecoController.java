package br.com.avaliacao.domains.pessoa;

import br.com.avaliacao.config.AppConstantes;
import br.com.avaliacao.domains.endereco.EnderecoRequest;
import br.com.avaliacao.domains.lotacao.dtos.LotacaoRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = AppConstantes.PATHS.V1.PESSOAS)
public class PessoaEnderecoController {

    private final PessoaService pessoaService;

    @PostMapping("/{id}/endereco")
    public ResponseEntity<Void> create(@PathVariable Integer id, @Valid @RequestBody EnderecoRequest enderecoRequest) {
        var unidadeResponse = pessoaService.addEndereco(id, enderecoRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(unidadeResponse.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}/endereco")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pessoaService.removeEndereco(id);
        return ResponseEntity.noContent().build();
    }

}