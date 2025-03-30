package br.com.avaliacao.avaliacao.unidade;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    private final UnidadeService unidadeService;

    @GetMapping
    public List<Unidade> getAllUnidades() {
        return unidadeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeDTO> getUnidadeById(@PathVariable Integer id) {
        var unidade = unidadeService.findById(id);
        return ResponseEntity.ok(unidade);
    }

    @PostMapping
    public ResponseEntity<UnidadeDTO> createUnidade(@RequestBody Unidade unidade) {
        var unidadeResponse = unidadeService.save(unidade);
        return ResponseEntity.ok(unidadeResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeDTO> updateUnidade(@PathVariable Integer id, @RequestBody UnidadeDTO unidadeUpdate) {
        var unidadeResponse = unidadeService.updateUnidade(id, unidadeUpdate);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnidade(@PathVariable Integer id) {
        unidadeService.deleteById(id);
    }
}