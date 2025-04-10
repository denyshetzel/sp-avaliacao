package br.com.avaliacao.domains.pessoa;

import br.com.avaliacao.config.AppConstantes;
import br.com.avaliacao.domains.pessoa.dtos.PessoaFotoResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = AppConstantes.PATHS.V1.PESSOAS)
public class PessoaFotoController {

    private final PessoaService pessoaService;

    @PostMapping(value = "/{pessoaId}/fotos",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createFoto(@NotNull @PathVariable Integer pessoaId,
                                           @RequestPart("file") MultipartFile file) {
        pessoaService.saveFoto(pessoaId, file);
        System.out.println(ServletUriComponentsBuilder.newInstance());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{pessoaId}/fotos")
                .buildAndExpand(pessoaId)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @SneakyThrows(Exception.class)
    @GetMapping("/{pessoaId}/fotos")
    public ResponseEntity<List<PessoaFotoResponse>> getFotos(@PathVariable Integer pessoaId) {
        var fotos = pessoaService.getAllFotos(pessoaId);
        return ResponseEntity.ok(fotos);
    }

    @SneakyThrows(Exception.class)
    @GetMapping("/{pessoaId}/fotos/{fileName}")
    public ResponseEntity<String> generateUrlFoto(@PathVariable Integer pessoaId, @PathVariable("fileName") String fileName) {
        var url = pessoaService.generateUrlFoto(pessoaId, fileName);
        return ResponseEntity.ok(url);
    }

    @DeleteMapping("/{pessoaId}/fotos/{fotoId}")
    public ResponseEntity<Void> deleteFoto(@PathVariable Integer pessoaId, @PathVariable("fotoId") Integer fotoId) {
        pessoaService.removeFoto(pessoaId, fotoId);
        return ResponseEntity.noContent().build();
    }

}