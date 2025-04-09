package br.com.avaliacao.domains.pessoa;

import br.com.avaliacao.config.AppConstantes;
import br.com.avaliacao.domains.pessoa.dtos.PessoaFotoResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.InputStream;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = AppConstantes.PATHS.V1.PESSOAS)
public class PessoaFotoController {

    private final PessoaService pessoaService;

    @PostMapping(value = "/{pessoaId}/fotos/{fileName}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createFoto(@NotNull @PathVariable Integer pessoaId,
                                           @PathVariable @NotBlank @Size(max = 50) String fileName,
                                           @RequestPart("file") MultipartFile file) {
        pessoaService.saveFoto(pessoaId, fileName, file);
        System.out.println(ServletUriComponentsBuilder.newInstance());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{pessoaId}/fotos/{fileName}")
                .buildAndExpand(pessoaId, fileName)
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
    public void downloadFoto(@PathVariable Integer pessoaId, @PathVariable("fileName") String fileName, HttpServletResponse response) {
            InputStream fileInputStream = pessoaService.dowloadFoto(pessoaId, fileName);
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("application/force-download");
            response.setCharacterEncoding("UTF-8");
            IOUtils.copy(fileInputStream, response.getOutputStream());
    }

    @DeleteMapping("/{pessoaId}/fotos/{fotoId}")
    public ResponseEntity<Void> deleteFoto(@PathVariable Integer pessoaId, @PathVariable("fotoId") Integer fotoId, HttpServletResponse response) {
        pessoaService.removeFoto(pessoaId, fotoId);
        return ResponseEntity.noContent().build();
    }

}